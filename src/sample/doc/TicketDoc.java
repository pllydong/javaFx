package sample.doc;

import org.apache.poi.xwpf.usermodel.*;
import sample.pojo.FlightInfo;
import sample.pojo.Ticket;
import sample.pojo.UserInformation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 机票
 */
public class TicketDoc {

    static ArrayDeque<String> deque = new ArrayDeque<>();
    static ArrayDeque<String> deque2 = new ArrayDeque<>();

    public static void initQueue(Ticket ticket) {
        deque.addLast(ticket.getAirlinePnr());
        deque.addLast(ticket.getBookingPnr());
        deque.addLast(ticket.getPassengerName());
        deque.addLast(ticket.getETicketNumber());
        deque.addLast(ticket.getIdNumber());
        deque.addLast(ticket.getConjunctionTicketNumber());
        deque.addLast(ticket.getIssuingAirline());
        deque.addLast(ticket.getDateOfIssue());
        deque.addLast(ticket.getIssuingAgent());
        deque.addLast(ticket.getIataCode());

    }

    public static void handle(Ticket ticket, UserInformation userInformation, String filePath, Double m1, Double m2) {
        initQueue(ticket);
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
                        } else if (i == 1) {
                            if (j != 0) {
                                XWPFParagraph paragraph = cell.getParagraphs().get(0);
                                for (int m = paragraph.getRuns().size() - 1; m >= 0; m--) {
                                    paragraph.removeRun(m);
                                }
                                if (!deque2.isEmpty()) {
                                    String s = deque2.pollFirst();
                                    XWPFRun newRun = paragraph.createRun();
                                    newRun.setText(s);
                                }
                            }
                        }
                        k++;
                    }
                    j++;
                }
                i++;
            }
            List<XWPFParagraph> paragraphs = doc.getParagraphs();
            final int[] l = {0};
            final AtomicBoolean[] check = {new AtomicBoolean(false)};
            for (XWPFParagraph paragraph : paragraphs) {
                String text = paragraph.getText();
                if (text.contains("机票款/FARE :") || text.contains("总计金额")) {
                    paragraph.getRuns().forEach(run -> {
                        String s1 = run.getText(0);
                        if (check[0].get()) {
                            if (l[0] == 0) {
                                run.setText("", 0);
                                run.setText(String.valueOf(m1), 0);
                                l[0]++;
                                check[0].set(false);
                            } else if (l[0] == 1) {
                                run.setText("", 0);
                                run.setText(String.valueOf(m2), 0);
                                l[0]++;
                                check[0].set(false);
                            } else if (l[0] == 2) {
                                run.setText("", 0);
                                run.setText(String.valueOf(m1 + m2), 0);
                                check[0].set(false);
                            }
                        }
                        if ("CNY".equals(s1)) {
                            check[0].set(true);
                        }
                    });
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
        handle(new Ticket(), new UserInformation(), "files/doc/ITINERARY-中_英.docx", 1050.00, 234.00);
    }
}
