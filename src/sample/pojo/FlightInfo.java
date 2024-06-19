package sample.pojo;

import lombok.Data;

@Data
public class FlightInfo {
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
    private String flightClass;

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
    private String status;

    /**
     * 出发航站楼
     */
    private String departureTerminal;

    /**
     * 抵达航站楼
     */
    private String arrivalTerminal;
}
