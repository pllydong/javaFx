package sample.pojo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AirlineBooking  {
    /**
     * 航空公司记录编号
     */
    private String airlinePnr;

    /**
     * 订座记录编号
     */
    private String bookingPnr;

    /**
     * 旅客姓名
     */
    private String passengerName;

    /**
     * 电子票号
     */
    private String eTicketNumber;

    /**
     * 身份识别代码
     */
    private String idNumber;

    /**
     * 联票票号
     */
    private String conjNumber;

    /**
     * 出票航空公司
     */
    private String issuingAirline;

    /**
     * 出票日期
     */
    private LocalDate dateOfIssue;

    /**
     * 出票代理
     */
    private String issuingAgent;

    /**
     * 航协号
     */
    private String iataCode;

    @Override
    public String toString() {
        return "AirlineBooking{" +
                "airlinePnr='" + airlinePnr + '\'' +
                ", bookingPnr='" + bookingPnr + '\'' +
                ", passengerName='" + passengerName + '\'' +
                ", eTicketNumber='" + eTicketNumber + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", conjNumber='" + conjNumber + '\'' +
                ", issuingAirline='" + issuingAirline + '\'' +
                ", dateOfIssue=" + dateOfIssue +
                ", issuingAgent='" + issuingAgent + '\'' +
                ", iataCode='" + iataCode + '\'' +
                '}';
    }

}
