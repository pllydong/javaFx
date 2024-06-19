package sample.doc;

import org.apache.poi.xwpf.usermodel.*;
import sample.pojo.AirlineBooking;
import sample.pojo.FlightInfo;
import sample.pojo.UserInformation;
import sample.utils.DocUtil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ItineraryDoc {



    public static void handle(AirlineBooking airlineBooking,FlightInfo flightInfo,UserInformation userInformation, String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath);
             XWPFDocument doc = new XWPFDocument(fis)) {


            try (FileOutputStream fos = new FileOutputStream("7."+userInformation.getChineseLastName()+userInformation.getChineseFirstName()+"机票.docx")) {
                doc.write(fos);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AirlineBooking airlineBooking = new AirlineBooking();
        airlineBooking.setConjNumber("1125");
        handle(airlineBooking, null,null,"G:\\Code\\JavaCode\\javaFx\\files\\doc\\ITINERARY-中_英.docx");
    }
}
