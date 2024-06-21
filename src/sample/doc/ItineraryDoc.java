package sample.doc;

import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlCursor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;
import sample.pojo.Itinerary;
import sample.utils.MyFileUtil;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class ItineraryDoc {
    public static void main(String[] args) throws FileNotFoundException {
        List<Itinerary> list = new ArrayList<>();
        Random random = new Random();

        List<String> dates = Arrays.asList("2024. 07.25", "2024. 07.26", "2024. 07.27");
        List<String> activityPlans = Arrays.asList(
                "인천공항-〉나리타공항",
                "센소지, 하쿠힌칸 토이 파크, 도쿄타워",
                "나리타공항-〉인천공항"
        );
        List<String> contactNumbers = Arrays.asList("010-2211-6314", "", "");
        List<String> accommodationsAddresses = Arrays.asList(
                "MIMARU TOKYO IKEBUKURO, 2 Chome-61-1 Ikebukuro, Toshima City, Tokyo 171-0014 日本, +81 3-5927-8877",
                "",
                ""
        );

        Itinerary itinerary1 = new Itinerary();
        itinerary1.setDate(dates.get(random.nextInt(dates.size())));
        itinerary1.setActivityPlan(generateActivityPlan(random, 3, 5));
        itinerary1.setContactNumber(contactNumbers.get(random.nextInt(contactNumbers.size())));
        itinerary1.setAccommodationsAddress(accommodationsAddresses.get(random.nextInt(accommodationsAddresses.size())));

        Itinerary itinerary2 = new Itinerary();
        itinerary2.setDate(dates.get(random.nextInt(dates.size())));
        itinerary2.setActivityPlan(generateActivityPlan(random, 3, 5));
        itinerary2.setContactNumber(contactNumbers.get(random.nextInt(contactNumbers.size())));
        itinerary2.setAccommodationsAddress(accommodationsAddresses.get(random.nextInt(accommodationsAddresses.size())));

        Itinerary itinerary3 = new Itinerary();
        itinerary3.setDate(dates.get(random.nextInt(dates.size())));
        itinerary3.setActivityPlan(generateActivityPlan(random, 3, 5));
        itinerary3.setContactNumber(contactNumbers.get(random.nextInt(contactNumbers.size())));
        itinerary3.setAccommodationsAddress(accommodationsAddresses.get(random.nextInt(accommodationsAddresses.size())));

        Itinerary itinerary4 = new Itinerary();
        itinerary4.setDate(dates.get(random.nextInt(dates.size())));
        itinerary4.setActivityPlan(generateActivityPlan(random, 3, 5));
        itinerary4.setContactNumber(contactNumbers.get(random.nextInt(contactNumbers.size())));
        itinerary4.setAccommodationsAddress(accommodationsAddresses.get(random.nextInt(accommodationsAddresses.size())));

        list.add(itinerary1);
        list.add(itinerary2);
        list.add(itinerary3);
        list.add(itinerary4);
        handle(list, "D:/export/", "2025", "06", "12", "WU HAN", "徐涵", "AC Hotel Tokyo Ginza", "6 Chome-14-7 Ginza, Chuo C\n" + "ity, Tokyo 104-0061日本", "+81 3-5550-0102");
    }

    private static List<String> generateActivityPlan(Random random, int minSize, int maxSize) {
        List<String> activityPlan = new ArrayList<>();
        int size = random.nextInt(maxSize - minSize + 1) + minSize;
        for (int i = 0; i < size; i++) {
            activityPlan.add("Activity " + i);
        }
        return activityPlan;
    }

    /**
     * @param itinerary 行程单
     * @param filePath  文件路径
     * @param year      表单的年份
     * @param month     表单的月份（如果是单数月前面要有0 比如6月就是 06）
     * @param day       天数（单数的天数前面也要0补齐）
     * @param pinyin    顾客的名字拼音 比如徐涵就是 XU HAN，英文大写并中间用空格
     * @param name      顾客的名字，中文名字，用来生成申请单的名字
     */
    public static void handle(List<Itinerary> itinerary, String filePath, String year, String month, String day, String pinyin, String name, String a, String b, String c) {
        filePath = MyFileUtil.apendEndSeperator(filePath) + name + "行程单.doc";
        MyFileUtil.createAllDirectoriesIfNotExist(filePath);
        try (FileInputStream fis = new FileInputStream("files/doc/6徐晗行程.docx");
             XWPFDocument doc = new XWPFDocument(fis)) {

            List<XWPFParagraph> paragraphs = doc.getParagraphs();
            XWPFRun run1 = paragraphs.get(0).getRuns().get(0);
            run1.setText("", 0);
            run1.setText(year + " (");
            XWPFRun run2 = paragraphs.get(0).getRuns().get(2);
            run2.setText("", 0);
            run2.setText(")  " + month);
            XWPFRun run3 = paragraphs.get(0).getRuns().get(6);
            run3.setText("", 0);
            run3.setText(" " + day + " (");
            List<XWPFRun> runs = paragraphs.get(4).getRuns();
            runs.get(3).setText("", 0);
            runs.get(3).setText(pinyin);
            int sum = 0;
            for (Itinerary i : itinerary) {
                sum += i.getActivityPlan().size();
            }

            if (sum < 3) {
                sum = 3;
            }
            XWPFTable table = doc.createTable(sum + 1, 4);

            // 设置表格宽度为100%
            CTTblWidth tblWidth = table.getCTTbl().getTblPr().addNewTblW();
            tblWidth.setType(STTblWidth.DXA);
            tblWidth.setW(BigInteger.valueOf(10500)); // 100%的宽度

            // 获取表格的第一行
            XWPFTableRow headerRow = table.getRow(0);
            headerRow.setHeight(600);
            // 创建一个方法设置单元格内容和换行
            headerRow.getCell(0).setWidth(String.valueOf(1500));
            headerRow.getCell(1).setWidth(String.valueOf(3000));
            headerRow.getCell(2).setWidth(String.valueOf(3000));
            setCellText(headerRow.getCell(0), "Date\n날짜");
            setCellText(headerRow.getCell(1), "Activity plan\n여행 계획");
            setCellText(headerRow.getCell(2), "Contact number\n연락처");
            setCellText(headerRow.getCell(3), "Accommodations address\n숙소 명 및 주소");

            XWPFTableRow rowx = table.getRow(1);
            setCellText(rowx.getCell(3), a);
            XWPFTableRow row2 = table.getRow(2);
            setCellText(row2.getCell(3), b);
            XWPFTableRow row3 = table.getRow(3);
            setCellText(row3.getCell(3), c);

            int k = 1;
            for (Itinerary i : itinerary) {
                XWPFTableRow row = table.getRow(k);
                row.setHeight(600);
                setCellText(row.getCell(0), i.getDate());
                setCellText(row.getCell(2), i.getContactNumber());
                for (int j = 0; j < i.getActivityPlan().size(); j++) {
                    XWPFTableRow row1 = table.getRow(k);
                    row1.setHeight(600);
                    row1.getCell(0).setWidth(String.valueOf(1500));
                    row1.getCell(1).setWidth(String.valueOf(3000));
                    row1.getCell(2).setWidth(String.valueOf(3000));
                    setCellText(row1.getCell(1), i.getActivityPlan().get(j));
                    k++;
                }
            }


            try (FileOutputStream out = new FileOutputStream(filePath)) {
                doc.write(out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void setCellText(XWPFTableCell cell, String text) {
        // 清除单元格内容
        cell.removeParagraph(0);

        // 添加段落并设置文本
        XWPFParagraph paragraph = cell.addParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER); // 居中对齐
        paragraph.setSpacingAfter(0);
        paragraph.setSpacingBefore(0);
        paragraph.setSpacingBetween(1.0);

        // 根据换行符拆分文本
        String[] lines = text.split("\n");
        for (int i = 0; i < lines.length; i++) {
            XWPFRun run = paragraph.createRun();
            run.setText(lines[i]);
            run.setFontSize(10);
            run.setFontFamily("SimSun"); // 设置字体为宋体
            if (i < lines.length - 1) {
                run.addBreak(); // 仅在不是最后一行时添加换行符
            }
        }
    }
}
