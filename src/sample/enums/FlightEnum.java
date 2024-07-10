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

    TW_211("07:45", "10:15", TerminalEnum.RC_T1, TerminalEnum.CT_T2, "TW211", "", AirlineCompanyEnum.TW),
    TW_214("15:00", "18:05", TerminalEnum.CT_T2, TerminalEnum.RC_T1, "TW214", "", AirlineCompanyEnum.TW),

    LJ_205("09:45", "12:15", TerminalEnum.RC_T2, TerminalEnum.CT_T1, "LJ205", "", AirlineCompanyEnum.LJ),
    LJ_206("13:25", "15:45", TerminalEnum.CT_T1, TerminalEnum.RC_T2, "LJ206", "", AirlineCompanyEnum.LJ),

    LJ_203("07:25", "10:00", TerminalEnum.RC_T2, TerminalEnum.CT_T1, "LJ203", "", AirlineCompanyEnum.LJ),
    LJ_202("10:20", "13:00", TerminalEnum.CT_T1, TerminalEnum.RC_T2, "LJ202", "", AirlineCompanyEnum.LJ),

    RS_701("09:40", "12:00", TerminalEnum.RC_T1, TerminalEnum.CT_T1, "RS701", "A321-200", AirlineCompanyEnum.RS),
    RS_702("13:10", "15:50", TerminalEnum.CT_T1, TerminalEnum.RC_T1, "RS702", "A321-200", AirlineCompanyEnum.RS),

    KE_703("10:10", "12:40", TerminalEnum.RC_T2, TerminalEnum.CT_T1, "KE703", "B787-9", AirlineCompanyEnum.KE),
    KE_704("14:00", "16:30", TerminalEnum.CT_T1, TerminalEnum.RC_T2, "KE704", "B787-9", AirlineCompanyEnum.KE),

    OZ_102("09:00", "11:20", TerminalEnum.RC_T1, TerminalEnum.CT_T1, "OZ102", "A380-800", AirlineCompanyEnum.OZ),
    OZ_101("13:20", "15:50", TerminalEnum.CT_T1, TerminalEnum.RC_T1, "OZ101", "A380-800", AirlineCompanyEnum.OZ),

    _7C_1106("10:35", "12:55", TerminalEnum.RC_T1, TerminalEnum.CT_T3, "7C1106", "", AirlineCompanyEnum._7C),
    _7C_1105("13:55", "16:35", TerminalEnum.CT_T3, TerminalEnum.RC_T1, "7C1105", "", AirlineCompanyEnum._7C),

    ZE601("08:00", "10:30", TerminalEnum.RC_T1, TerminalEnum.CT_T2, "ZE601", "", AirlineCompanyEnum.ZE),
    ZE606("10:50", "13:35", TerminalEnum.CT_T2, TerminalEnum.RC_T1, "ZE606", "", AirlineCompanyEnum.ZE),
    ZE602("11:30", "14:35", TerminalEnum.CT_T2, TerminalEnum.RC_T1, "ZE602", "", AirlineCompanyEnum.ZE),

    LJ201("06:45", "09:20", TerminalEnum.RC_T2, TerminalEnum.CT_T1, "LJ201", "", AirlineCompanyEnum.LJ),
    LJ204("10:50", "13:35", TerminalEnum.CT_T1, TerminalEnum.RC_T2, "LJ204", "", AirlineCompanyEnum.LJ),
    LJ202("10:20", "13:00", TerminalEnum.CT_T1, TerminalEnum.RC_T2, "LJ202", "", AirlineCompanyEnum.LJ),
    LJ203("07:25", "10:00", TerminalEnum.RC_T2, TerminalEnum.CT_T1, "LJ203", "", AirlineCompanyEnum.LJ),

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
