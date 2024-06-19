package sample.pojo;

import lombok.Data;

@Data
public class Itinerary {
    /**
     * 行程单名称
     */
    private String ItineraryName = "ITINERARY";
    /**
     * 航空公司记录编号
     */
    private String airlinePnr = "航空公司记录编号/AIRLINE PNR:3WZ7NV";

    /**
     * 订座记录编号
     */
    private String bookingPnr = "订座记录编号/1E PNR:3WZ7NV";

    /**
     * 旅客姓名
     */
    private String passengerName = "旅客姓名/NAME:LI/ENZI MS";

    /**
     * 电子票号
     */
    private String eTicketNumber = "电子票号/ETKT NBR:1807104380444";

    /**
     * 身份识别代码
     */
    private String idNumber = "身份识别代码/ID NUMBER:EH6367295";

    /**
     * 联票票号
     */
    private String conjunctionTicketNumber = "联票票号/CONJ NBR:";

    /**
     * 出票航空公司
     */
    private String issuingAirline = "出票航空公司/ISSUING AIRLINE: KOREAN AIR";

    /**
     * 出票日期
     */
    private String dateOfIssue = "出票日期/DATE OF ISSUE:2024-05-13";

    /**
     * 出票代理
     */
    private String issuingAgent = "出票代理/ISSUING AGENT:";

    /**
     * 航协号
     */
    private String iataCode = "航协号/IATA CODE:1125887000";

    public static void main(String[] args) {
        Itinerary itinerary = new Itinerary();
        System.out.println(itinerary);
    }
}
