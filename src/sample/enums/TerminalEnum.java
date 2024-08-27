package sample.enums;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;

import static sample.enums.AirportEnum.*;

/**
 * 航站楼枚举类
 * @author guokun
 * @date 2024/6/19 22:15
 */
@Getter
public enum TerminalEnum {
    /**
     * 成田T1
     */
    ICN_T1("T1", AirportEnum.ICN),
    /**
     * 成田T2
     */
    ICN_T2("T2", AirportEnum.ICN),

    /**
     * 仁川T1
     */
    NRT_T1("T1", AirportEnum.NRT),
    /**
     * 仁川T2
     */
    NRT_T2("T2", AirportEnum.NRT),
    /**
     * 仁川T3
     */
    NRT_T3("T3", AirportEnum.NRT),

    /**
     * 关西T1
     */
    KIX_T1("T1", KIX),

    /**
     * 富士山机场
     */
    FSZ_T("T", FSZ),
    /**
     * 中部
     */
    NGO_T1("T1", NGO),
    /**
     * 福冈机场
     */
    FUK_T("T", FUK),
    /**
     * 熊本机场
     */
    KMJ_T("T", KMJ),
    /**
     * 鹿儿岛机场
     */
    KOJ_T("T", KOJ),
    /**
     * 那霸机场
     */
    OKA_T("T", OKA),
    /**
     * 新千岁机场
     */
    CTS_T("T", CTS),


//    JP_T1("金浦", "I", AirportEnum.GMP),

    ;

    private String terminalNo;
    private AirportEnum airport;

    TerminalEnum(String terminalNo, AirportEnum airport) {
        this.terminalNo = terminalNo;
        this.airport = airport;
    }

    public String getTerminalName() {
        return null == this.airport ? StrUtil.EMPTY : this.airport.getDesc();
    }
}
