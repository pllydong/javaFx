package sample.model;

import lombok.Data;
import sample.enums.FlightEnum;
import sample.pojo.*;
import sample.utils.MyMap;

import java.util.*;

/**
 * @author guokun
 * @date 2024/6/20 23:11
 */
@Data
public class CacheData {

    private static Branch rootBranch;
    private static final MyMap<Branch> branchMap = new MyMap<>();
    private static final MyMap<TouristSpot> touristSpotMap = new MyMap<>();
    private static final MyMap<Hotel> hotelMap = new MyMap<>();

    public static Branch getRootBranch() {
        return rootBranch;
    }

    public static void setRootBranch(Branch rootBranch) {
        CacheData.rootBranch = rootBranch;
    }

    public static MyMap<Branch> getBranchMap() {
        return branchMap;
    }

    public static MyMap<TouristSpot> getTouristSpotMap() {
        return touristSpotMap;
    }

    public static MyMap<Hotel> getHotelMap() {
        return hotelMap;
    }


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
    /**
     * 日本申请单2
     */
    private JapanVisaApplication japanVisaApplication;
}
