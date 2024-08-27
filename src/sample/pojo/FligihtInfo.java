package sample.pojo;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Data;
import sample.enums.FlightEnum;
import sample.model.Flight;

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


    public static FligihtInfo createFlightInfo(Flight flight, String dt) {
        FligihtInfo flightInfo = new FligihtInfo();
        flightInfo.setFlight(flight.getFlightCode());
        flightInfo.setDate(DateUtil.parse(dt, PURE_DATE_PATTERN).toDateStr());
        flightInfo.setDepartureTerminal(flight.getStartTerminalNo());

        String startTime = formatTime(flight.getStartTime());
        flightInfo.setDepartureTime(startTime);

        flightInfo.setArrivalTerminal(flight.getEndTerminalNo());

        String endTime = formatTime(flight.getEndTime());
        flightInfo.setArrivalTime(endTime);

        flightInfo.setOriginDes(flight.getStartCode() + StrUtil.SLASH + flight.getEndCode());
        return flightInfo;
    }

    private static String formatTime(String time) {
        if (time.matches("\\d:\\d{2}")) {
            return "0" + time;
        } else if (time.matches("\\d\\.\\d{2}")) {
            return "0" + time.replace('.', ':');
        } else if (time.matches("\\d{2}\\.\\d{2}")) {
            return time.replace('.', ':');
        }
        return time;
    }


}
