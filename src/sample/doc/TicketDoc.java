package sample.doc;

import cn.hutool.core.util.NumberUtil;
import org.apache.poi.xwpf.usermodel.*;
import sample.pojo.FligihtInfo;
import sample.pojo.Ticket;
import sample.utils.MyFileUtil;

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
        deque.addLast(ticket.);
        deque.addLast(ticket.getConjunctionTicketNumber());
        deque.addLast(ticket.getIssuingAirline());
        deque.addLast(ticket.getDateOfIssue());
        deque.addLast(ticket.getIssuingAgent());
        deque.addLast(ticket.getIataCode());

    }

    /**
     *
     * @param ticket 机票信息
     * @param name 客户名字全称（比如:徐涵）
     * @param filePath 模板文件地址
     * @param m1 机票款
     * @param m2 税费
     */
    public static void handle(Ticket ticket, FligihtInfo f1, FligihtInfo f2, String name, String filePath, double m1, double m2) {
        filePath = MyFileUtil.apendEndSeperator(filePath) + "5" + name + "机票.docx";
        MyFileUtil.createAllDirectoriesIfNotExist(filePath);
        initQueue(ticket);
        initDeque(f1);
        initDeque(f2);
        try (FileInputStream fis = new FileInputStream(TICKT_TMP_FILE_PATH);
             XWPFDocument doc = new XWPFDocument(fis)) {
            int i = 0;
            for (XWPFTable table : doc.getTables()) {
                int j = 0;
                for (XWPFTableRow row : table.getRows()) {
                    int k = 0;
                    for (XWPFTableCell cell : row.getTableCells()) {
                        String cellText = cell.getText();
                        if (i == 0) {
                            if (cellText.contains("行程单")) {
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
//            List<XWPFParagraph> paragraphs = doc.getParagraphs();
//            final int[] l = {0};
//            final AtomicBoolean[] check = {new AtomicBoolean(false)};
//            for (XWPFParagraph paragraph : paragraphs) {
//                String text = paragraph.getText();
//                if (text.contains("机票款/FARE :") || text.contains("总计金额")) {
//                    paragraph.getRuns().forEach(run -> {
//                        String s1 = run.getText(0);
//                        if (check[0].get()) {
//                            if (l[0] == 0) {
//                                run.setText("", 0);
//                                run.setText(NumberUtil.roundStr(m1, 2), 0);
//                                l[0]++;
//                                check[0].set(false);
//                            } else if (l[0] == 1) {
//                                run.setText("", 0);
//                                run.setText(NumberUtil.roundStr(m2, 2), 0);
//                                l[0]++;
//                                check[0].set(false);
//                            } else if (l[0] == 2) {
//                                run.setText("", 0);
//                                run.setText(NumberUtil.roundStr(m1 + m2, 2), 0);
//                                check[0].set(false);
//                            }
//                        }
//                        if ("CNY".equals(s1)) {
//                            check[0].set(true);
//                        }
//                    });
//                }
//            }


            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                doc.write(fos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void initDeque(FligihtInfo f2) {
        deque2.addLast(f2.getOriginDes());
        deque2.addLast(f2.getFlight());
        deque2.addLast(f2.getFlightClass());
        deque2.addLast(f2.getDate());
        deque2.addLast(f2.getDepartureTime());
        deque2.addLast(f2.getArrivalTime());
        deque2.addLast(f2.getStatus());
        deque2.addLast(f2.getDepartureTerminal());
        deque2.addLast(f2.getArrivalTerminal());
    }


    public static void main(String[] args) {
        FligihtInfo flight1 = new FligihtInfo();
        flight1.setOriginDes("ICN/SHE");
        flight1.setFlight("KE0831");
        flight1.setFlightClass("Economy");
        flight1.setDate("2024-06-13");
        flight1.setDepartureTime("08:00");
        flight1.setArrivalTime("08:55");
        flight1.setStatus("OK");
        flight1.setDepartureTerminal("T2");
        flight1.setArrivalTerminal("");

        FligihtInfo flight2 = new FligihtInfo();
        flight2.setOriginDes("SHE/ICN");
        flight2.setFlight("KE0832");
        flight2.setFlightClass("Economy");
        flight2.setDate("2024-06-19");
        flight2.setDepartureTime("10:15");
        flight2.setArrivalTime("13:15");
        flight2.setStatus("OK");
        flight2.setDepartureTerminal("");
        flight2.setArrivalTerminal("T3");
        handle(new Ticket(),flight1,flight2, "徐涵", "D:/export/", 1050.00, 234.00);
    }
    public static String TICKT_TMP_FILE_PATH = "files/徐晗 010-2211-6314/5徐晗机票.docx";
}
