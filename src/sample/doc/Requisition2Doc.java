package sample.doc;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import sample.utils.DocUtil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Requisition2Doc {


    public static void handle(String filePath){

        try (FileInputStream fis = new FileInputStream("files/doc/提取自申请表.docx");
             XWPFDocument doc = new XWPFDocument(fis)) {

            for (XWPFParagraph paragraph : doc.getParagraphs()) {
                for (XWPFRun run : paragraph.getRuns()) {
                    System.out.println(run.getText(0));
                }
            }


            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                doc.write(fos);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        handle("D:/export/");
    }
}
