package sample.model;

import lombok.Data;
import sample.enums.FlightEnum;
import sample.pojo.FligihtInfo;
import sample.pojo.Itinerary;
import sample.pojo.Ticket;
import sample.pojo.UserInformation;

import java.util.List;
import java.util.Map;

/**
 * @author guokun
 * @date 2024/6/20 23:11
 */
@Data
public class CacheData {
    /**
     * 出发日期 yyyyMMdd
     */
    private String startDt;
    /**
     * 返航日期 yyyyMMdd
     */
    private String endDt;
    /**
     * 酒店
     */
    private Hotel hotel;
    /**
     * 游玩景点
     * Map<Date(yyyyMMdd), List<TouristSpot>>
     */
    private Map<String, List<TouristSpot>> touristMap;
    /**
     * 出发航班
     */
    private FlightEnum flight;
    /**
     * 返航航班
     */
    private FlightEnum backFlight;

    /**
     * 用户信息
     */
    private UserInformation userInfo;
    /**
     * 申请表信息
     */
    private List<Itinerary> itineraryInfoList;
    /**
     * 机票信息
     */
    private FligihtInfo flightInfo;
    /**
     * 返航机票信息
     */
    private FligihtInfo backFlightInfo;
    /**
     * 机票信息
     */
    private Ticket ticketInfo;
}
