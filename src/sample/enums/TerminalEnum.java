package sample.enums;

import lombok.Getter;

/**
 * 航站楼枚举类
 * @author guokun
 * @date 2024/6/19 22:15
 */
@Getter
public enum TerminalEnum {
    RC_T1("仁川", "T1", "ICN"),
    RC_T2("仁川", "T2", "ICN"),

    CT_T1("成田", "T1", "NRT"),
    CT_T2("成田", "T2", "NRT"),

    JP_T1("金浦", "I", "ICN"),

    YT_T3("羽田", "T3", "NRT"),
    ;

    private String terminalName;
    private String terminalNo;
    private String airport;

    TerminalEnum(String terminalName, String terminalNo, String airport) {
        this.terminalName = terminalName;
        this.terminalNo = terminalNo;
        this.airport = airport;
    }


}
