package sample.doc;

import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlCursor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

import java.io.*;
import java.math.BigInteger;

public class ItineraryDoc {
    public static void main(String[] args) throws FileNotFoundException {
        handle("files/doc/6徐晗行程.docx");
    }

    public static void handle(String filePath) throws FileNotFoundException {
        try (FileInputStream fis = new FileInputStream(filePath);
             XWPFDocument doc = new XWPFDocument(fis)) {
            XWPFTable table = doc.createTable(4, 4);

            // 设置表格宽度为100%
            CTTblWidth tblWidth = table.getCTTbl().getTblPr().addNewTblW();
            tblWidth.setType(STTblWidth.DXA);
            tblWidth.setW(BigInteger.valueOf(10500)); // 100%的宽度

            // 获取表格的第一行
            XWPFTableRow headerRow = table.getRow(0);
            headerRow.setHeight(600);
            // 创建一个方法设置单元格内容和换行
            setCellText(headerRow.getCell(0), "Date\n날짜");
            setCellText(headerRow.getCell(1), "Activity plan\n여행 계획");
            setCellText(headerRow.getCell(2), "Contact number\n연락처");
            setCellText(headerRow.getCell(3), "Accommodations address\n숙소 명 및 주소");

            // 可以继续设置第二行的内容（示例数据）
            XWPFTableRow secondRow = table.getRow(1);
            secondRow.setHeight(600);
            setCellText(secondRow.getCell(0), "2024-06-20");
            setCellText(secondRow.getCell(1), "Seoul City Tour");
            setCellText(secondRow.getCell(2), "+82-10-1234-5678");
            setCellText(secondRow.getCell(3), "123 Seoul Street, Seoul, South Korea");

            try (FileOutputStream out = new FileOutputStream("6.行程单.doc")) {
                doc.write(out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void setCellText(XWPFTableCell cell, String text) {
        // 清除单元格内容
        cell.removeParagraph(0);

        // 添加段落并设置文本
        XWPFParagraph paragraph = cell.addParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER); // 居中对齐
        paragraph.setSpacingAfter(0);
        paragraph.setSpacingBefore(0);
        paragraph.setSpacingBetween(1.0);

        // 根据换行符拆分文本
        String[] lines = text.split("\n");
        for (int i = 0; i < lines.length; i++) {
            XWPFRun run = paragraph.createRun();
            run.setText(lines[i]);
            run.setFontSize(10);
            run.setFontFamily("SimSun"); // 设置字体为宋体
            if (i < lines.length - 1) {
                run.addBreak(); // 仅在不是最后一行时添加换行符
            }
        }
    }
}
