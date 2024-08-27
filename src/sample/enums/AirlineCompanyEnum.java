package sample.enums;

import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.RandomUtil;
import lombok.Getter;

/**
 * 航空公司枚举
 * @author guokun
 * @date 2024/6/19 22:15
 */
@Getter
public enum AirlineCompanyEnum {
    /**
     * 首尔航空
     */
    RS("首尔航空", "AIR SEOUL", "RS", "820"),
    /**
     * 釜山航空
     */
    BX("釜山航空", "AIR BUSAN", "BX", "982"),
    /**
     * 真航空
     */
    LJ("真航空", "JIN AIR", "LJ", "718"),
    /**
     * 大韩航空
     */
    KE("大航航空", "KOREAN AIR", "KE", "180"),
    /**
     * 济州航空
     */
    _7C("济州航空", "JEJU AIR", "7C", "809"),
    /**
     * 易斯达航空
     */
    ZE("易斯达航空", "EASTAR JET", "ZE", "839"),
    /**
     * 韩亚航空
     */
    OZ("韩亚航空", "ASIANA AIRLINES", "OZ", "988"),
    /**
     * 德威航空
     */
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

    /**
     * 获取随机机票号码
     * @return
     */
    public String getRandomTicketNum() {
        return ticketNumPrefix + RandomUtil.randomNumbers(10);
    }
}
