package sample.pojo;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Data;
import sample.enums.FlightEnum;

import static cn.hutool.core.date.DatePattern.PURE_DATE_PATTERN;

@Data
public class FligihtInfo {
    /**
     * 起飞机场/抵达机场
     */
    private String originDes;

    /**
     * 航班号
     */
    private String flight;

    /**
     * 舱位等级
     */
    private String flightClass = "Economy";

    /**
     * 出发日期
     */
    private String date;

    /**
     * 起飞时间
     */
    private String departureTime;

    /**
     * 抵达时间
     */
    private String arrivalTime;

    /**
     * 状态
     */
    private String status = "OK";

    /**
     * 出发航站楼
     */
    private String departureTerminal;

    /**
     * 抵达航站楼
     */
    private String arrivalTerminal;


    public static FligihtInfo createFlightInfo(FlightEnum flightEnum, String dt) {
        FligihtInfo flightInfo = new FligihtInfo();
        flightInfo.setFlight(flightEnum.getCode());
        flightInfo.setDate(DateUtil.parse(dt, PURE_DATE_PATTERN).toDateStr());
        flightInfo.setDepartureTerminal(flightEnum.getStartTerminal().getTerminalNo());
        flightInfo.setDepartureTime(flightEnum.getStartTime());
        flightInfo.setArrivalTerminal(flightEnum.getEndTerminal().getTerminalNo());
        flightInfo.setArrivalTime(flightEnum.getEndTime());
        flightInfo.setOriginDes(flightEnum.getStartTerminal().getAirport().getCode() + StrUtil.SLASH + flightEnum.getEndTerminal().getAirport().getCode());
        return flightInfo;
    }

}
