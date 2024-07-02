package sample.doc;

import cn.hutool.core.date.DateUtil;
import com.aspose.pdf.internal.imaging.internal.Exceptions.IO.IOException;
import com.itextpdf.forms.fields.PdfButtonFormField;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.*;
import sample.pojo.JapanVisaApplication;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class PdfFormHandler {
    public static void handle(JapanVisaApplication japanVisaApplication, String filePath, String cusName) {
        String pdfFilePath = "files/doc/申请表.pdf";
        try {

            // 读取 PDF 文件
            PdfReader reader = null;
            try {
                reader = new PdfReader(pdfFilePath);
            } catch (java.io.IOException e) {
                throw new RuntimeException(e);
            }

            BaseFont bf = BaseFont.createFont("C:\\Windows\\Fonts\\msyh.ttc,0", BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
            Font font = new Font(bf, 8, Font.BOLD);
            // 创建输出流和 PDFStamper
            FileOutputStream outputStream = new FileOutputStream(filePath + cusName + "申请表2.pdf");
            PdfStamper stamper = new PdfStamper(reader, outputStream);

            // 获取表单字段
            AcroFields form = stamper.getAcroFields();

            // 在适当的位置调用重构后的方法
            setRadioButtonField(stamper, form, "rb-1", japanVisaApplication.getSex());
            setRadioButtonField(stamper, form, "rb-2", japanVisaApplication.getMaritalStatus());
            setRadioButtonField(stamper, form, "rb-3", japanVisaApplication.getPassportType());



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
                   form.setFieldProperty(fieldName, "textfont", bf, null);
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

    private static void setRadioButtonField(PdfStamper stamper, AcroFields form, String fieldName, int option) throws IOException, DocumentException, java.io.IOException {
        AcroFields.FieldPosition fieldPosition = form.getFieldPositions(fieldName).get(0);

        switch (option) {
            case 1:
                form.setField(fieldName, "1", true);
                break;
            case 2:
                form.setField(fieldName, "2", true);
                break;
            case 3:
                form.setField(fieldName, "3", true);
                break;
            case 4:
                form.setField(fieldName, "4", true);
                break;
            default:
                // Handle default case if necessary
                break;
        }

        stamper.getOverContent(fieldPosition.page).setLiteral("1 0 0 1 0 0 cm");
        stamper.getOverContent(fieldPosition.page).stroke();
        stamper.getOverContent(fieldPosition.page).resetRGBColorStroke();
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

        handle(application, "D:/export/", "test");
    }
}
