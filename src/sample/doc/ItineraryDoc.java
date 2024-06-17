package sample.doc;

import org.apache.poi.xwpf.usermodel.*;
import sample.pojo.AirlineBooking;
import sample.pojo.FlightInfo;
import sample.utils.DocUtil;

import java.io.IOException;

public class ItineraryDoc {


    public static void handle(AirlineBooking airlineBooking, FlightInfo flightInfo) throws IOException {
        String filePath = "G:\\Code\\JavaCode\\javaFx\\files\\doc\\ITINERARY-中_英.docx";
        XWPFDocument xwpfDocument = DocUtil.readXWPDF(filePath);
        if(xwpfDocument == null) {
            return;
        }
        for (XWPFTable table : xwpfDocument.getTables()) {
            for (XWPFTableRow row : table.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    String cellText = cell.getText();
                    XWPFParagraph paragraph = cell.getParagraphs().get(0);
                    System.out.println("Original cell text: " + cellText);
//                    if(paragraph == null) {
//                        continue;
//                    }else if(paragraph.getText().contains("航空公司记录编号/AIRLINE PNR:")){
//                        paragraph.getRuns().get(0).setText("航空公司记录编号/AIRLINE PNR: " + airlineBooking.getAirlinePnr(), 0);
//                    }else if(paragraph.getText().contains("订座记录编号/1E PNR:")){
//                        paragraph.getRuns().get(0).setText("订座记录编号/1E PNR: " + airlineBooking.getBookingPnr(), 0);
//                    }else if(paragraph.getText().contains("旅客姓名/NAME:")){
//                        paragraph.getRuns().get(0).setText("旅客姓名/NAME:" + airlineBooking.getPassengerName(), 0);
//                    }else if(paragraph.getText().contains("电子票号/ETKT NBR:")){
//                        paragraph.getRuns().get(0).setText("航班号/FLIGHT NO: " + airlineBooking.getETicketNumber(), 0);
//                    }else if (paragraph.getText().contains("身份识别代码/ID NUMBER:")) {
//                        paragraph.getRuns().get(0).setText("航班号/FLIGHT NO: " + airlineBooking.getIdNumber(), 0);
//                    }else if(paragraph.getText().contains("联票票号/CONJ NBR:")){
//                        paragraph.getRuns().get(0).setText("联票票号/CONJ NBR: " + airlineBooking.getConjNumber(), 0);
//                    }else if(paragraph.getText().contains("出票航空公司/ISSUING AIRLINE:")){
//                        paragraph.getRuns().get(0).setText("出票航空公司/ISSUING AIRLINE: " + airlineBooking.getIssuingAirline(), 0);
//                    }else if(paragraph.getText().contains("出票日期/DATE OF ISSUE:")){
//                        paragraph.getRuns().get(0).setText("出票日期/DATE OF ISSUE: " + airlineBooking.getDateOfIssue(), 0);
//                    }else if(paragraph.getText().contains("出票代理/ISSUING AGENT:")){
//                        paragraph.getRuns().get(0).setText("出票代理/ISSUING AGENT: " + airlineBooking.getIssuingAgent(), 0);
//                    }else if(paragraph.getText().contains("出票地点/PLACE OF ISSUE:")){
//                       paragraph.getRuns().get(0).setText("出票地点/PLACE OF ISSUE: " + airlineBooking.getIataCode(), 0);
//                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        AirlineBooking airlineBooking = new AirlineBooking();
        airlineBooking.setConjNumber("1125");
        try {
            handle(null, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
