package sample.doc;

import com.aspose.pdf.*;
import com.aspose.pdf.facades.FieldType;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import sample.pojo.JapanVisaApplication;
import sample.utils.DocUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Map;

public class Requisition2Doc {


    private static Deque<String> deque=new ArrayDeque<>();
    public static void handle(JapanVisaApplication japanVisaApplication,String filePath) {
        init(japanVisaApplication);
        String pdfFilePath = "files/doc/申请表.pdf";
        int i = 0;
        try {
            // 读取 PDF 文件
            PdfReader reader = new PdfReader(pdfFilePath);

            // 创建输出流和 PDFStamper
            FileOutputStream outputStream = new FileOutputStream(filePath+"申请表.pdf");
            PdfStamper stamper = new PdfStamper(reader, outputStream);

            // 获取表单字段
            AcroFields form = stamper.getAcroFields();

            // 获取所有表单域信息的 Map
            Map<String, AcroFields.Item> fields = form.getFields();

            // 遍历所有表单域
            for (Map.Entry<String, AcroFields.Item> entry : fields.entrySet()) {
                String fieldName = entry.getKey();
                AcroFields.Item item = entry.getValue();

                // 输出字段名称和类型
                System.out.println("Field name: " + fieldName);
                System.out.println("Field type: " + item.getMerged(0).get(PdfName.FT));
                System.out.println(item.getValue(0));

//                String poll = deque.poll();
//                if(poll==null){
//                    continue;
//                }
//                if(poll.isEmpty()){
//                    continue;
//                }
//                try{
//                    if(Integer.parseInt(poll)<0){
//                        continue;
//                    }
//                }catch (NumberFormatException e){
//                    System.out.println("数字格式错误");
//                }
//                System.out.println(poll);
//
//                switch (fieldName){
//                    case "topmostSubform[0].Page1[0].T2[0]":
//                        System.out.println("----------------------------------");
//                        form.setField(fieldName, poll);
//                        break;
//                }
//                if(fieldName.contains("topmostSubform[0].Page1[0].T2[0]")){
//                    System.out.println("____________________________");
//                    continue;
//                }
                form.setField(fieldName, fieldName);

                System.out.println("------");
            }

            // 关闭 PDFStamper 和输出流
            stamper.close();
            outputStream.close();

            // 关闭 PDF 读取器
            reader.close();

            System.out.println("PDF 表单填写完成。");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (com.itextpdf.text.DocumentException e) {
            e.printStackTrace();
        }
    }

        public static void main(String[] args) {
        handle(new JapanVisaApplication(),"D:/export/");
    }



    private static void  init(JapanVisaApplication japanVisaApplication) {
        deque.addLast(japanVisaApplication.getSurname());

    }

}
