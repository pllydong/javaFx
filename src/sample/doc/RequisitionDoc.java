package sample.doc;

import org.apache.poi.xwpf.usermodel.*;
import sample.pojo.UserInformation;
import sample.utils.DocUtil;
import sample.utils.MyFileUtil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 申请单
 */
public class RequisitionDoc {


    static ArrayDeque<String> deque= new ArrayDeque<>();

    public static void initQueue(UserInformation userInformation) {
        deque.addLast(userInformation.getEnglishLastName());
        deque.addLast(userInformation.getChineseLastName());
        deque.addLast(userInformation.getEnglishFirstName());
        deque.addLast(userInformation.getChineseFirstName());
        deque.addLast(userInformation.getDateOfBirth());
        deque.addLast(userInformation.getPlaceOfBirth());
        deque.addLast(userInformation.getGender());
        deque.addLast(userInformation.getMaritalStatus());
        deque.addLast(userInformation.getNationality());
        deque.addLast(userInformation.getForeignerRegistrationNumber());
        deque.addLast(userInformation.getContactNumber());
        deque.addLast(userInformation.getEmail());
        deque.addLast(userInformation.getAddress());
        deque.addLast(userInformation.getCurrentOccupationAndPosition());
        deque.addLast(userInformation.getCompanyOrSchoolName());
        deque.addLast(userInformation.getCompanyOrSchoolPhoneNumber());
        deque.addLast(userInformation.getCompanyOrSchoolAddress());
        deque.addLast(userInformation.getPlannedCityOfEntryInJapan());
        deque.addLast(userInformation.getEntryPortOrFlightNumber());
        deque.addLast(userInformation.getPlannedDurationOfStayInJapan());
        deque.addLast(userInformation.getLastStayInJapanDuration());
        deque.addLast(userInformation.getTotalStayDurationInJapanLastYear());
    }

    public static void handle(UserInformation userInformation, String filePath, String cusName) {
        initQueue(userInformation);
        filePath = MyFileUtil.apendEndSeperator(filePath) + cusName + "申请表.docx";
        MyFileUtil.createAllDirectoriesIfNotExist(filePath);
        try (FileInputStream fis = new FileInputStream("files/doc/7郑禹龙申请表.docx");
             XWPFDocument doc = new XWPFDocument(fis)) {
            DocUtil.setDoc(doc,deque);
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                doc.write(fos);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
