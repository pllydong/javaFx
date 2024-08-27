package sample.model;

import lombok.Data;
import sample.enums.AirlineCompanyEnum;
import sample.enums.FlightEnum;

/**
 * 航班
 * @author guokun
 * @date 2024/8/27 21:17
 */
@Data
public class Flight {
    private String flightCode;
    private String startCode;
    private String endCode;
    private String startTime;
    private String endTime;
    private String companyName;

    /**
     * 机票枚举，优先查找这个
     */
    private FlightEnum flightEnum;

    public String getFlightCode() {
        return null == flightEnum ? flightCode : flightEnum.getCode();
    }

    public String getStartCode() {
        return null == flightEnum ? startCode : flightEnum.getStartTerminal().getAirport().getCode();
    }

    public String getEndCode() {
        return null == flightEnum ? endCode : flightEnum.getEndTerminal().getAirport().getCode();
    }

    public String getStartTime() {
        return null == flightEnum ? startTime : flightEnum.getStartTime();
    }

    public String getEndTime() {
        return null == flightEnum ? endTime : flightEnum.getEndTime();
    }

    public String getCompanyName() {
        return null == flightEnum ? companyName : flightEnum.getCompany().getEnglishName();
    }

    private String startTerminalNo;
    private String endTerminalNo;

    public String getStartTerminalNo() {
        return null == flightEnum ? startTerminalNo : flightEnum.getStartTerminal().getTerminalNo();
    }

    public String getEndTerminalNo() {
        return null == flightEnum ? startTerminalNo : flightEnum.getEndTerminal().getTerminalNo();
    }

    private String ticketPreFix;

    public String getTicketPreFix() {
        return null == flightEnum ? ticketPreFix : flightEnum.getCompany().getTicketNumPrefix();
    }
}
