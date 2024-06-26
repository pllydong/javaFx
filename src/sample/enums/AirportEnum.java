package sample.enums;

import lombok.Getter;

/**
 * @author guokun
 * @date 2024/6/20 23:20
 */
@Getter
public enum AirportEnum {
    /**
     * 首尔机场
     */
    ICN("ICN", "首尔机场"),
    /**
     * 东京机场
     */
    NRT("NRT", "东京机场"),
//    /**
//     * 金浦机场
//     */
//    GMP("GMP", "金浦机场"),
    ;

    private String code;
    private String desc;

    AirportEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getFromToStr(AirportEnum to) {
        return getFromToStr(this, to);
    }

    public static String getFromToStr(AirportEnum from, AirportEnum to) {
        return from.getCode() + " -> " + to.getCode();
    }
}
