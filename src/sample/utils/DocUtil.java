package sample.utils;

import org.apache.poi.xwpf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DocUtil {

    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("G:\\Code\\JavaCode\\untitled13\\7郑禹龙申请表.docx");
             XWPFDocument doc = new XWPFDocument(fis)) {

            for (XWPFTable table : doc.getTables()) {
                int i = 0;
                for (XWPFTableRow row : table.getRows()) {
                    int j = 1;
                    for (XWPFTableCell cell : row.getTableCells()) {

                        if (j == 4) {
                            // 获取单元格中的第一个段落
                            XWPFParagraph paragraph = cell.getParagraphs().get(0);

                            // 遍历段落中的所有文本节点 (XWPFRun)
                            for (XWPFRun run : paragraph.getRuns()) {
                                // 清空原有文本内容
                                run.setText("", 0);
                                // 设置新文本内容
                                run.setText("--");
                            }
                        }
                        j++;
                    }
                    i++;
                }
            }

            try (FileOutputStream fos = new FileOutputStream("modified_word_document.docx")) {
                doc.write(fos);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static XWPFDocument readXWPDF(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath);
             XWPFDocument doc = new XWPFDocument(fis)) {
            return doc;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
