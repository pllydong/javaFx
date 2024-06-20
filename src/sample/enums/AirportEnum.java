package sample.enums;

/**
 * @author guokun
 * @date 2024/6/20 23:20
 */
public enum AirportEnum {
    /**
     * 首尔机场
     */
    ICN("ICN", "首尔机场"),
    /**
     * 东京机场
     */
    NRT("NRT", "东京机场"),
    ;

    private String code;
    private String desc;

    AirportEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
