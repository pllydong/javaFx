package sample.pojo;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 航班信息
 */
@Data
public class FlightInfo {

    /**
     * 起飞机场/抵达机场
     */
    private String originDestination;

    /**
     * 航班号
     */
    private String flightNumber;

    /**
     * 舱位等级
     */
    private String flightClass;

    /**
     * 出发日期
     */
    private LocalDateTime departureDate;

    /**
     * 起飞时间
     */
    private LocalDateTime departureTime;

    /**
     * 抵达时间
     */
    private LocalDateTime arrivalTime;

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

    @Override
    public String toString() {
        return "FlightInfo{" +
                "originDestination='" + originDestination + '\'' +
                ", flightNumber='" + flightNumber + '\'' +
                ", flightClass='" + flightClass + '\'' +
                ", departureDate=" + departureDate +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", status='" + status + '\'' +
                ", departureTerminal='" + departureTerminal + '\'' +
                ", arrivalTerminal='" + arrivalTerminal + '\'' +
                '}';
    }
}
