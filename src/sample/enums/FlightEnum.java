package sample.enums;

import lombok.Getter;

import static sample.enums.AirlineCompanyEnum.*;
import static sample.enums.TerminalEnum.*;

/**
 * 航班枚举类
 *
 * @author guokun
 * @date 2024/6/19 22:34
 */
@Getter
public enum FlightEnum {
    // 东京

    TW_213("10:20", "12:50", ICN_T1, NRT_T2, "TW213", "B737", TW),
    TW_212("11:15", "14:05", NRT_T2, ICN_T1, "TW212", "B737", TW),

    TW_211("07:45", "10:15", ICN_T1, NRT_T2, "TW211", "", TW),
    TW_214("15:00", "18:05", NRT_T2, ICN_T1, "TW214", "", TW),

    LJ_205("09:45", "12:15", ICN_T2, NRT_T1, "LJ205", "", LJ),
    LJ_206("13:25", "15:45", NRT_T1, ICN_T2, "LJ206", "", LJ),

    LJ_203("07:25", "10:00", ICN_T2, NRT_T1, "LJ203", "", LJ),
    LJ_202("10:20", "13:00", NRT_T1, ICN_T2, "LJ202", "", LJ),

    RS_701("09:40", "12:00", ICN_T1, NRT_T1, "RS701", "A321-200", RS),
    RS_702("13:10", "15:50", NRT_T1, ICN_T1, "RS702", "A321-200", RS),

    KE_703("10:10", "12:40", ICN_T2, NRT_T1, "KE703", "B787-9", KE),
    KE_704("14:00", "16:30", NRT_T1, ICN_T2, "KE704", "B787-9", KE),

    OZ_102("09:00", "11:20", ICN_T1, NRT_T1, "OZ102", "A380-800", OZ),
    OZ_101("13:20", "15:50", NRT_T1, ICN_T1, "OZ101", "A380-800", OZ),

    _7C_1106("10:35", "12:55", ICN_T1, NRT_T3, "7C1106", "", _7C),
    _7C_1105("13:55", "16:35", NRT_T3, ICN_T1, "7C1105", "", _7C),

    ZE601("08:00", "10:30", ICN_T1, NRT_T2, "ZE601", "", ZE),
    ZE606("10:50", "13:35", NRT_T2, ICN_T1, "ZE606", "", ZE),
    ZE602("11:30", "14:35", NRT_T2, ICN_T1, "ZE602", "", ZE),

    LJ201("06:45", "09:20", ICN_T2, NRT_T1, "LJ201", "", LJ),
    LJ204("10:50", "13:35", NRT_T1, ICN_T2, "LJ204", "", LJ),
    LJ202("10:20", "13:00", NRT_T1, ICN_T2, "LJ202", "", LJ),
    LJ203("07:25", "10:00", ICN_T2, NRT_T1, "LJ203", "", LJ),

    // 大阪
    // ICN<-->KIX
    //出发航站楼	出发时间	到达航站楼	到达时间	航班	航空公司	航空公司	机票信息
    //仁川T1	7:55	关西T1	9:40	OZ112	韩亚航空	ASIANA AIRLINES	988**（+10位）
    OZ112("7:55", "9:40", NRT_T1, KIX_T1, "OZ112", "", OZ),
    //关西T1	9:10	仁川T1	11:00	OZ115
    OZ115("9:10", "11:00", KIX_T1, NRT_T1, "OZ115", "", OZ),
    //
    //仁川T1	14:05	关西T1	15:50	OZ114	韩亚航空	ASIANA AIRLINES	988**（+10位）
    OZ114("14:05", "15:50", KIX_T1, NRT_T1, "OZ114", "", OZ),
    //关西T1	10:50	仁川T1	12:40	OZ111
    OZ111("10:50", "12:40", KIX_T1, NRT_T1, "OZ111", "", OZ),
    //
    //仁川T2	9:35	关西T1	11:20	KE723	大韩航空	KOREAN AIR	180**（+10位）
    KE723("9:35", "11:20", NRT_T2, KIX_T1, "KE723", "", KE),
    //关西T1	12:35	仁川T2	14:25	KE724
    KE724("9:35", "11:20", KIX_T1, NRT_T2, "KE724", "", KE),
    //
    //仁川T2	9:35	关西T1	11:20	KE723	大韩航空	KOREAN AIR	180**（+10位）
    //关西T1	9:15	仁川T2	11:05	KE722
    KE722("9:15", "11:05", KIX_T1, NRT_T2, "KE722", "", KE),

    // 中部富士名古
    // ICN<-->FSZ
    //出发航站楼	出发时间	到达航站楼	到达时间	航班	航空公司	航空公司	机票信息
    //仁川T1	15:10	富士山机场	17:05	7C1282	济州航空	JEJU AIR	809**（+10位）
    _7C1282("15:10", "17:05", NRT_T1, FSZ_T, "7C1282", "", _7C),
    //富士山机场	17:55	仁川T1	20:10	7C1281
    _7C1281("17:55", "20:10", FSZ_T, NRT_T1, "7C1281", "", _7C),

    // ICN<-->NGO
    //出发航站楼	出发时间	到达航站楼	到达时间	航班	航空公司	航空公司	机票信息
    //仁川T1	8:15	中部T1	10:05	OZ122	韩亚航空	ASIANA AIRLINES	988**（+10位）
    OZ122("8:15", "10:05", NRT_T1, NGO_T1, "OZ122", "", OZ),
    //中部T1	13:50	仁川T1	15:50	7C1601	济州航空	JEJU AIR	809**（+10位）
    _7C1601("13:50", "15:50", NGO_T1, NRT_T1, "7C1601", "", _7C),
    //
    //仁川T2	10:45	中部T1	12:40	KE741	大韩航空	KOREAN AIR	180**（+10位）
    KE741("10:45", "12:40", NRT_T2, NGO_T1, "KE741", "", KE),
    //中部T1	13:50	仁川T2	15:45	KE742
    KE742("13:50", "15:45", NGO_T1, NRT_T2, "KE742", "", KE),
    //
    //仁川T2	10:45	中部T1	12:40	KE741	大韩航空	KOREAN AIR	180**（+10位）
    //中部T1	9:15	仁川T2	11:20	KE744
    KE744("9:15", "11:20", NGO_T1, NRT_T2, "KE744", "", KE),

    // 九州福冈
    // ICN<-->FUK
    //出发航站楼	出发时间	到达航站楼	到达时间	航班	航空公司	航空公司	机票信息
    //仁川T1	8:45	福冈机场	10:15	OZ132	韩亚航空	ASIANA AIRLINES	988**（+10位）
    OZ132("8:45", "10:15", NRT_T1, FUK_T, "OZ132", "", OZ),
    //福冈机场	15:00	仁川T1	16:25	OZ133
    OZ133("15:00", "16:25", FUK_T, NRT_T1, "OZ133", "", OZ),
    //
    //仁川T2	8:00	福冈机场	9:25	KE787	大韩航空	KOREAN AIR	180**（+10位）
    KE787("8:00", "9:25", NRT_T2, FUK_T, "KE787", "", KE),
    //福冈机场	10:35	仁川T2	12:00	KE788
    KE788("10:35", "12:00", FUK_T, NRT_T2, "KE788", "", KE),

    // 九州熊本
    // ICN<-->KMJ
    //出发航站楼	出发时间	到达航站楼	到达时间	航班	航空公司	航空公司	机票信息
    //仁川T1	7:55	熊本机场	9:25	TW275	德威航空	TWAY AIR	722**（+10位）
    TW275("7:55", "9:25", NRT_T1, KMJ_T, "TW275", "", TW),
    //熊本机场	10:25	仁川T1	11:55	TW276
    TW276("8:00", "11:55", KMJ_T, NRT_T1, "TW276", "", TW),

    // 鹿儿岛
    // ICN<-->KOJ
    //出发航站楼	出发时间	到达航站楼	到达时间	航班	航空公司	航空公司	机票信息
    //仁川T2	9:20	鹿儿岛机场	10:55	KE785	大韩航空	KOREAN AIR	180**（+10位）
    KE785("9:20", "10:55", NRT_T2, KOJ_T, "KE785", "", KE),
    //鹿儿岛机场	12:00	仁川T2	13:35	KE786
    KE786("12:00", "13:35", KOJ_T, NRT_T2, "KE786", "", KE),
    //此航班每周二 周六 无

    // 冲绳
    // ICN<-->OKA
    //出发航站楼	出发时间	到达航站楼	到达时间	航班	航空公司	航空公司	机票信息
    //仁川T2	9:10	那霸机场	11:40	KE755	大韩航空	KOREAN AIR	180**（+10位）
    KE755("9:10", "11:40", NRT_T2, OKA_T, "KE755", "", KE),
    //那霸机场	12:50	仁川T2	15:20	KE756
    KE756("12:50", "15:20", OKA_T, NRT_T2, "KE756", "", KE),
    //
    //仁川T1	9:15	那霸机场	11:40	OZ172	韩亚航空	ASIANA AIRLINES	988**（+10位）
    OZ172("9:15", "11:40", NRT_T1, KOJ_T, "OZ172", "", OZ),
    //那霸机场	13:00	仁川T1	15:25	OZ171
    OZ171("13:00", "15:25", KOJ_T, NRT_T1, "OZ171", "", OZ),

    // 北海道
    // ICN<-->CTS(札幌)
    //出发航站楼	出发时间	到达航站楼	到达时间	航班	航空公司	航空公司	机票信息
    //仁川T1	9:15	新千岁机场	11:50	OZ174	韩亚航空	ASIANA AIRLINES	988**（+10位）
    OZ174("9:15", "11:50", NRT_T1, CTS_T, "OZ174", "", OZ),
    //新千岁机场	13:00	仁川T1	16:10	OZ173
    OZ173("13:00", "16:10", CTS_T, NRT_T1, "OZ173", "", OZ),
    //
    //仁川T2	10:05	新千岁机场	12:50	KE765	大韩航空	KOREAN AIR	180**（+10位）
    KE765("10:05", "12:50", NRT_T2, CTS_T, "KE765", "", KE),
    //新千岁机场	14:05	仁川T2	17:05	KE766
    KE766("14:05", "17:05", CTS_T, NRT_T2, "KE766", "", KE),


    ;
    /**
     * 起航时间 mm:ss
     */
    private String startTime;
    /**
     * 返航时间 mm:ss
     */
    private String endTime;
    private TerminalEnum startTerminal;
    private TerminalEnum endTerminal;
    /**
     * 航班号
     */
    private String code;
    /**
     * 机种
     */
    private String planeType;
    /**
     * 航空公司
     */
    private AirlineCompanyEnum company;

    FlightEnum(String startTime, String endTime, TerminalEnum startTerminal, TerminalEnum endTerminal, String code, String planeType, AirlineCompanyEnum company) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.startTerminal = startTerminal;
        this.endTerminal = endTerminal;
        this.code = code;
        this.planeType = planeType;
        this.company = company;
    }
}
