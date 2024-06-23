package sample.doc;

import cn.hutool.core.util.StrUtil;
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
        deque.addLast(StrUtil.emptyIfNull(userInformation.getEnglishLastName()));
        deque.addLast(StrUtil.emptyIfNull(userInformation.getChineseLastName()));
        deque.addLast(StrUtil.emptyIfNull(userInformation.getEnglishFirstName()));
        deque.addLast(StrUtil.emptyIfNull(userInformation.getChineseFirstName()));
        deque.addLast(StrUtil.emptyIfNull(userInformation.getDateOfBirth()));
        deque.addLast(StrUtil.emptyIfNull(userInformation.getPlaceOfBirth()));
        deque.addLast(StrUtil.emptyIfNull(userInformation.getGender()));
        deque.addLast(StrUtil.emptyIfNull(userInformation.getMaritalStatus()));
        deque.addLast(StrUtil.emptyIfNull(userInformation.getNationality()));
        deque.addLast(StrUtil.emptyIfNull(userInformation.getForeignerRegistrationNumber()));
        deque.addLast(StrUtil.emptyIfNull(userInformation.getContactNumber()));
        deque.addLast(StrUtil.emptyIfNull(userInformation.getEmail()));
        deque.addLast(StrUtil.emptyIfNull(userInformation.getAddress()));
        deque.addLast(StrUtil.emptyIfNull(userInformation.getCurrentOccupationAndPosition()));
        deque.addLast(StrUtil.emptyIfNull(userInformation.getCompanyOrSchoolName()));
        deque.addLast(StrUtil.emptyIfNull(userInformation.getCompanyOrSchoolPhoneNumber()));
        deque.addLast(StrUtil.emptyIfNull(userInformation.getCompanyOrSchoolAddress()));
        deque.addLast(StrUtil.emptyIfNull(userInformation.getPlannedCityOfEntryInJapan()));
        deque.addLast(StrUtil.emptyIfNull(userInformation.getEntryPortOrFlightNumber()));
        deque.addLast(StrUtil.emptyIfNull(userInformation.getPlannedDurationOfStayInJapan()));
        deque.addLast(StrUtil.emptyIfNull(userInformation.getLastStayInJapanDuration()));
        deque.addLast(StrUtil.emptyIfNull(userInformation.getTotalStayDurationInJapanLastYear()));
    }

    public static void handle(UserInformation userInformation, String filePath, String cusName) {
        initQueue(userInformation);
        filePath = MyFileUtil.apendEndSeperator(filePath) +"7"+ cusName + "申请表.docx";
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
