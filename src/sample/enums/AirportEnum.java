package sample.enums;

import lombok.Getter;

/**
 * @author guokun
 * @date 2024/6/20 23:20
 */
@Getter
public enum AirportEnum {
    /**
     * 成田
     */
    ICN("ICN", "成田"),
    /**
     * 仁川
     */
    NRT("NRT", "仁川"),
    /**
     * 关西
     */
    KIX("KIX", "关西"),
    /**
     * 富士山机场
     */
    FSZ("FSZ", "富士山机场"),
    /**
     * 中部
     */
    NGO("NGO", "中部"),
    /**
     * 福冈机场
     */
    FUK("FUK", "福冈机场"),
    /**
     * 熊本机场
     */
    KMJ("KMJ", "熊本机场"),
    /**
     * 鹿儿岛机场
     */
    KOJ("KOJ", "鹿儿岛机场"),
    /**
     * 那霸机场
     */
    OKA("OKA", "那霸机场"),
    /**
     * 新千岁机场
     */
    CTS("CTS", "新千岁机场"),

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
