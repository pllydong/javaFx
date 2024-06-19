package sample.enums;

import lombok.Getter;

/**
 * @author guokun
 * @date 2024/6/19 22:15
 */
@Getter
public enum AirlineCompanyEnum {
    RS("首尔航空", "AIR SEOUL", "RS", "820"),
    BX("釜山航空", "AIR BUSAN", "BX", "982"),
    LJ("真航空", "JIN AIR", "LJ", "718"),
    KE("大航航空", "KOREAN AIR", "KE", "180"),
    _7C("济州航空", "JEJU AIR", "7C", "809"),
    ZE("易斯达航空", "EASTAR JET", "ZE", "839"),
    OZ("韩亚航空", "ASIANA AIRLINES", "OZ", "988"),
    TW("德威航空", "TWAY AIR", "TW", "722"),
    ;

    private String chineseName;
    private String englishName;
    private String abbreviation;
    private String ticketNumPrefix;

    AirlineCompanyEnum(String chineseName, String englishName, String abbreviation, String ticketNumPrefix) {
        this.chineseName = chineseName;
        this.englishName = englishName;
        this.abbreviation = abbreviation;
        this.ticketNumPrefix = ticketNumPrefix;
    }
}
