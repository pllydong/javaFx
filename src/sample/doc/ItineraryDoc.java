package sample.doc;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

public class ItineraryDoc {


    public static void handle(String filePath) throws FileNotFoundException {
        try (FileInputStream fis = new FileInputStream(filePath);
             XWPFDocument doc = new XWPFDocument(fis)) {
            XWPFTable table = doc.createTable();

            createRowWithContent(table, "英文 - 姓", "ZHENG", "汉字 - 姓", "郑");


            try (FileOutputStream out = new FileOutputStream("6.行程单.doc")) {
                doc.write(out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createRowWithContent(XWPFTable table, String... contents) {
        // 创建一个新的表格行（row），并添加到传入的table中
        XWPFTableRow row = table.createRow();

        // 计算这一行有多少列（colWidth）。可变参数contents接收多个字符串，每个字符串代表一列的内容
        int colWidth = contents.length;
        System.out.println(colWidth);

        // 开始遍历每一列的内容
        for (int i=0; i < contents.length; i++) {

            // 在当前行中创建一列（cell）
            XWPFTableCell cell = row.createCell();

            // 将contents数组中对应位置的字符串设置为该列的内容
            cell.setText(contents[i]);

            // 获取单元格的底层XML对象，用于设置单元格的属性
            CTTcPr cellProps = cell.getCTTc().addNewTcPr();

            // 创建一个CTTblWidth对象，用于设置单元格的宽度
            CTTblWidth cellWidth = cellProps.addNewTcW();

            // 将单元格宽度的类型设置为DXA（缇，twentieth of a point）
            cellWidth.setType(STTblWidth.DXA);

            // 计算每个单元格的宽度，并设置为表格总宽度（9000缇）除以列数
            cellWidth.setW(BigInteger.valueOf(9000 / colWidth));
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        handle("files/doc/6徐晗行程.docx");
    }
}
