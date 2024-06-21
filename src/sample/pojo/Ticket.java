package sample.pojo;

import lombok.Data;

@Data
public class Ticket {
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

    public void setItineraryName(String itineraryName) {
        ItineraryName = itineraryName;
    }

    public void setAirlinePnr(String airlinePnr) {
        this.airlinePnr = "航空公司记录编号/AIRLINE PNR:" + airlinePnr;
    }

    public void setBookingPnr(String bookingPnr) {
        this.bookingPnr = "订座记录编号/1E PNR:" + bookingPnr;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = "旅客姓名/NAME:" + passengerName + " MS";
    }

    public void seteTicketNumber(String eTicketNumber) {
        this.eTicketNumber = "电子票号/ETKT NBR:" + eTicketNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = "身份识别代码/ID NUMBER:" + idNumber;
    }

    public void setConjunctionTicketNumber(String conjunctionTicketNumber) {
        this.conjunctionTicketNumber = "联票票号/CONJ NBR:" + conjunctionTicketNumber;
    }

    public void setIssuingAirline(String issuingAirline) {
        this.issuingAirline = "出票航空公司/ISSUING AIRLINE: " + issuingAirline;
    }

    public void setDateOfIssue(String dateOfIssue) {
        this.dateOfIssue = "出票日期/DATE OF ISSUE:" + dateOfIssue;
    }

    public void setIssuingAgent(String issuingAgent) {
        this.issuingAgent = "出票代理/ISSUING AGENT:" + issuingAgent;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = "航协号/IATA CODE:" + iataCode;
    }

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        System.out.println(ticket);
    }
}
