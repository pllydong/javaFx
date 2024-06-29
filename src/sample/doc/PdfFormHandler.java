package sample.doc;

import com.aspose.pdf.internal.imaging.internal.Exceptions.IO.IOException;
import com.itextpdf.forms.fields.PdfButtonFormField;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;
import sample.pojo.JapanVisaApplication;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class PdfFormHandler {
    public static void handle(JapanVisaApplication japanVisaApplication, String filePath) {
        String pdfFilePath = "files/doc/申请表.pdf";
        try {

            // 读取 PDF 文件
            PdfReader reader = null;
            try {
                reader = new PdfReader(pdfFilePath);
            } catch (java.io.IOException e) {
                throw new RuntimeException(e);
            }


            // 创建输出流和 PDFStamper
            FileOutputStream outputStream = new FileOutputStream(filePath + "申请表.pdf");
            PdfStamper stamper = new PdfStamper(reader, outputStream);


            BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
            ArrayList<BaseFont> fontList = new ArrayList<BaseFont>();
            fontList.add(bf);




            // 获取表单字段
            AcroFields form = stamper.getAcroFields();



            AcroFields.FieldPosition rb1 = form.getFieldPositions("rb-1").get(0);
            if (japanVisaApplication.getSex() == 1) {
                form.setField("rb-1", "M",true);
            } else {
                form.setField("rb-1", "F",true);
            }
            stamper.getOverContent(rb1.page).setLiteral("1 0 0 1 0 0 cm");
            stamper.getOverContent(rb1.page).stroke();
            stamper.getOverContent(rb1.page).resetRGBColorStroke();


            AcroFields.FieldPosition rb2 = form.getFieldPositions("rb-2").get(0);
            switch (japanVisaApplication.getMaritalStatus()) {
                case 1:
                    form.setField("rb-2", "1",true);
                    break;
                case 2:
                    form.setField("rb-2", "2",true);
                    break;
                case 3:
                    form.setField("rb-2", "3",true);
                    break;
                case 4:
                    form.setField("rb-2", "4",true);
                    break;
            }
            stamper.getOverContent(rb2.page).setLiteral("1 0 0 1 0 0 cm");
            stamper.getOverContent(rb2.page).stroke();
            stamper.getOverContent(rb2.page).resetRGBColorStroke();



            AcroFields.FieldPosition rb3 = form.getFieldPositions("rb-3").get(0);
            switch (japanVisaApplication.getPassportType()) {
                case 1:
                    form.setField("rb-3", "1",true);
                    break;
                case 2:
                    form.setField("rb-3", "2",true);
                    break;
                case 3:
                    form.setField("rb-3", "3",true);
                    break;
                case 4:
                    form.setField("rb-3", "4",true);
                    break;
            }
            stamper.getOverContent(rb3.page).setLiteral("1 0 0 1 0 0 cm");
            stamper.getOverContent(rb3.page).stroke();
            stamper.getOverContent(rb3.page).resetRGBColorStroke();




            // 按顺序获取字段位置信息
            String[] fieldNames = new String[42];
            for (int i = 1; i <= 42; i++) {
                fieldNames[i - 1] = "form-" + i;
            }


            // 遍历所有表单域，按顺序填写
            for (String fieldName : fieldNames) {
               try{

                   // 获取字段位置信息
                   AcroFields.FieldPosition position = form.getFieldPositions(fieldName).get(0);

                   // 设置字段值
                   String fieldValue = getFieldFromApplication(japanVisaApplication, fieldName);
                   form.setField(fieldName, fieldValue);

                   // 标记字段位置为已填写
                   stamper.getOverContent(position.page).setLiteral("1 0 0 1 0 0 cm");
                   stamper.getOverContent(position.page).stroke();
                   stamper.getOverContent(position.page).resetRGBColorStroke();
               }catch (NullPointerException e){
                   System.out.println("没有找到"+fieldName);
               }
            }

            stamper.setFormFlattening(true);
            // 关闭 PDFStamper 和输出流
            stamper.close();
            outputStream.close();

            // 关闭 PDF 读取器
            reader.close();

            System.out.println("PDF 表单填写完成。");

        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getFieldFromApplication(JapanVisaApplication application, String fieldName) {
        switch (fieldName) {
            case "form-1":
                return application.getSurname();
            case "form-2":
                return application.getGivenAndMiddleNames();
            case "form-3":
                return application.getOtherNames();
            case "form-4":
                return application.getDateOfBirth();
            case "form-5":
                return application.getPlaceOfBirthCountry();
            case "form-12":
                return application.getNationalityOrCitizenship();
            case "form-13":
                return application.getFormerNationalitiesOrCitizenships();
            case "form-14":
                return application.getGovernmentIdNumber();
            case "form-19":
                return application.getPassportNumber();
            case "form-20":
                return application.getPlaceOfIssue();
            case "form-21":
                return application.getDateOfIssue();
            case "form-22":
                return application.getIssuingAuthority();
            case "form-23":
                return application.getDateOfExpiry();
            case "form-24":
                return application.getCertificateOfEligibilityNumber();
            case "form-25":
                return application.getPurposeOfVisitOrResidenceStatus();
            case "form-26":
                return application.getIntendedLengthOfStay();
            case "form-27":
                return application.getDateOfArrivalInJapan();
            case "form-28":
                return application.getPortOfEntryIntoJapan();
            case "form-29":
                return application.getNameOfShipOrAirline();
            case "form-30":
                return application.getNamesAndAddressesOfIntendedStays();
            case "form-31":
                return application.getTelOfIntendedStays();
            case "form-32":
                return application.getAddressOfIntendedStays();
            case "form-33":
                return application.getPreviousStaysInJapan();
            case "form-34":
                return application.getCurrentResidentialAddress();
            case "form-35":
                return application.getTelephone();
            case "form-36":
                return application.getMobileNumber();
            case "form-37":
                return application.getEmail();
            case "form-38":
                return application.getProfessionOrOccupation();
            case "form-39":
                return application.getEmployerName();
            case "form-40":
                return application.getEmployerTelephone();
            case "form-41":
                return application.getEmployerAddress();
            case "form-42":
                return application.getPartnersProfessionOrOccupation();
            default:
                return "";
        }
    }


    public static void main(String[] args) {
        JapanVisaApplication application = new JapanVisaApplication();
        // 假设这里初始化了 application 对象的各个字段

        handle(application, "D:/export/");
    }
}
