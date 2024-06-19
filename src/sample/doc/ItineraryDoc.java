package sample.doc;

import org.apache.poi.xwpf.usermodel.*;
import sample.pojo.FlightInfo;
import sample.pojo.Itinerary;
import sample.pojo.UserInformation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayDeque;

/**
 * 机票
 */
public class ItineraryDoc {

    static ArrayDeque<String> deque = new ArrayDeque<>();
    static ArrayDeque<String> deque2 = new ArrayDeque<>();

    public static void initQueue(Itinerary itinerary) {
        deque.addLast(itinerary.getAirlinePnr());
        deque.addLast(itinerary.getBookingPnr());
        deque.addLast(itinerary.getPassengerName());
        deque.addLast(itinerary.getETicketNumber());
        deque.addLast(itinerary.getIdNumber());
        deque.addLast(itinerary.getConjunctionTicketNumber());
        deque.addLast(itinerary.getIssuingAirline());
        deque.addLast(itinerary.getDateOfIssue());
        deque.addLast(itinerary.getIssuingAgent());
        deque.addLast(itinerary.getIataCode());

    }

    public static void handle(Itinerary itinerary, UserInformation userInformation, String filePath) {
        initQueue(itinerary);
        try (FileInputStream fis = new FileInputStream(filePath);
             XWPFDocument doc = new XWPFDocument(fis)) {
            int i = 0;
            for (XWPFTable table : doc.getTables()) {
                int j = 0;
                for (XWPFTableRow row : table.getRows()) {
                    int k = 0;
                    for (XWPFTableCell cell : row.getTableCells()) {
                        String cellText = cell.getText();
                        if (i == 0) {
                            if (cellText.contains("ITINERARY")) {
                                continue;
                            }
                            if (!cellText.isEmpty()) {
                                XWPFParagraph paragraph = cell.getParagraphs().get(0);
                                for (int m = paragraph.getRuns().size() - 1; m >= 0; m--) {
                                    paragraph.removeRun(i);
                                }
                                if (!deque.isEmpty()) {
                                    String s = deque.pollFirst();
                                    XWPFRun newRun = paragraph.createRun();
                                    newRun.setText(s);
                                }

                            }
                        }
                        else if (i == 1) {
                            if (j != 0) {
                                XWPFParagraph paragraph = cell.getParagraphs().get(0);
                                for (int m = paragraph.getRuns().size() - 1; m >= 0; m--) {
                                    paragraph.removeRun(m);
                                }
                                System.out.println(paragraph.getText());
                                if (!deque2.isEmpty()) {
                                    String s = deque2.pollFirst();
                                    XWPFRun newRun = paragraph.createRun();
                                    newRun.setText(s);
                                }
                            }
                        }
//                        System.out.println("Original cell text: " + i + "-" + j + "-" + k + ": " + cellText);
//                        System.out.println(cellText.length());
                        k++;
                    }
                    j++;
                }
                i++;
            }
            boolean tableFound = false;
            for (IBodyElement element : doc.getBodyElements()) {
                if (element.getElementType() == BodyElementType.TABLE) {
                    tableFound = true;
                } else if (tableFound && element.getElementType() == BodyElementType.PARAGRAPH) {
                    XWPFParagraph paragraph = (XWPFParagraph) element;
                    System.out.println("Paragraph after table: " + paragraph.getText());
                    break;
                }
            }
            try (FileOutputStream fos = new FileOutputStream("5." + userInformation.getChineseLastName() + userInformation.getChineseFirstName() + "机票.docx")) {
                doc.write(fos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FlightInfo flight1 = new FlightInfo();
        flight1.setOriginDes("ICN/SHE");
        flight1.setFlight("KE0831");
        flight1.setFlightClass("Economy");
        flight1.setDate("2024-06-13");
        flight1.setDepartureTime("08:00");
        flight1.setArrivalTime("08:55");
        flight1.setStatus("OK");
        flight1.setDepartureTerminal("T2");
        flight1.setArrivalTerminal("");

        FlightInfo flight2 = new FlightInfo();
        flight2.setOriginDes("SHE/ICN");
        flight2.setFlight("KE0832");
        flight2.setFlightClass("Economy");
        flight2.setDate("2024-06-19");
        flight2.setDepartureTime("10:15");
        flight2.setArrivalTime("13:15");
        flight2.setStatus("OK");
        flight2.setDepartureTerminal("");
        flight2.setArrivalTerminal("T3");

        deque2.addLast(flight1.getOriginDes());
        deque2.addLast(flight1.getFlight());
        deque2.addLast(flight1.getFlightClass());
        deque2.addLast(flight1.getDate());
        deque2.addLast(flight1.getDepartureTime());
        deque2.addLast(flight1.getArrivalTime());
        deque2.addLast(flight1.getStatus());
        deque2.addLast(flight1.getDepartureTerminal());
        deque2.addLast(flight1.getArrivalTerminal());
        deque2.addLast(flight2.getOriginDes());
        deque2.addLast(flight2.getFlight());
        deque2.addLast(flight2.getFlightClass());
        deque2.addLast(flight2.getDate());
        deque2.addLast(flight2.getDepartureTime());
        deque2.addLast(flight2.getArrivalTime());
        deque2.addLast(flight2.getStatus());
        deque2.addLast(flight2.getDepartureTerminal());
        deque2.addLast(flight2.getArrivalTerminal());
        handle(new Itinerary(), new UserInformation(), "G:\\Code\\JavaCode\\javaFx\\files\\doc\\ITINERARY-中_英.docx");
    }
}