package sample.enums;

import lombok.Getter;

/**
 * 航班枚举类
 *
 * @author guokun
 * @date 2024/6/19 22:34
 */
@Getter
public enum FlightEnum {
    TW_213("10:20", "12:50", TerminalEnum.RC_T1, TerminalEnum.CT_T2, "TW213", "B737", AirlineCompanyEnum.TW),
    TW_212("11:15", "14:05", TerminalEnum.CT_T2, TerminalEnum.RC_T1, "TW212", "B737", AirlineCompanyEnum.TW),

    TW_211("7:45", "10:15", TerminalEnum.RC_T1, TerminalEnum.CT_T2, "TW211", "", AirlineCompanyEnum.TW),
    TW_214("15:00", "18:05", TerminalEnum.CT_T2, TerminalEnum.RC_T1, "TW214", "", AirlineCompanyEnum.TW),

    LJ_205("9:45", "12:15", TerminalEnum.RC_T2, TerminalEnum.CT_T1, "LJ205", "", AirlineCompanyEnum.LJ),
    LJ_206("13:25", "15:45", TerminalEnum.CT_T1, TerminalEnum.RC_T2, "LJ206", "", AirlineCompanyEnum.LJ),

    LJ_203("7:25", "10:00", TerminalEnum.RC_T2, TerminalEnum.CT_T1, "LJ203", "", AirlineCompanyEnum.LJ),
    LJ_202("10:20", "13:00", TerminalEnum.CT_T1, TerminalEnum.RC_T2, "LJ202", "", AirlineCompanyEnum.LJ),

    RS_701("9:40", "12:00", TerminalEnum.RC_T1, TerminalEnum.CT_T1, "RS701", "A321-200", AirlineCompanyEnum.RS),
    RS_702("13:10", "15:50", TerminalEnum.CT_T1, TerminalEnum.RC_T1, "RS702", "A321-200", AirlineCompanyEnum.RS),

    KE_703("10:10", "12:40", TerminalEnum.RC_T2, TerminalEnum.CT_T1, "KE703", "B787-9", AirlineCompanyEnum.KE),
    KE_704("14:00", "16:30", TerminalEnum.CT_T1, TerminalEnum.RC_T2, "KE704", "B787-9", AirlineCompanyEnum.KE),

    OZ_102("9:00", "11:20", TerminalEnum.RC_T1, TerminalEnum.CT_T1, "OZ102", "A380-800", AirlineCompanyEnum.OZ),
    OZ_101("13:20", "15:50", TerminalEnum.CT_T1, TerminalEnum.RC_T1, "OZ101", "A380-800", AirlineCompanyEnum.OZ),

    _7C_1106("10:35", "12:55", TerminalEnum.RC_T1, TerminalEnum.YT_T3, "7C1106", "", AirlineCompanyEnum._7C),
    _7C_1105("13:55", "16:35", TerminalEnum.YT_T3, TerminalEnum.RC_T1, "7C1105", "", AirlineCompanyEnum._7C),

//    KE_2101("9:00", "11:20", TerminalEnum.JP_T1, TerminalEnum.YT_T3, "KE2101", "A330-300", AirlineCompanyEnum.KE),
//    KE_2102("12:30", "14:50", TerminalEnum.YT_T3, TerminalEnum.JP_T1, "KE2102", "A330-300", AirlineCompanyEnum.KE),
//
//    OZ_1085("8:40", "10:45", TerminalEnum.JP_T1, TerminalEnum.YT_T3, "OZ1085", "A330-300", AirlineCompanyEnum.OZ),
//    OZ_1075("12:05", "14:25", TerminalEnum.YT_T3, TerminalEnum.JP_T1, "OZ1075", "A330-300", AirlineCompanyEnum.OZ),
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
