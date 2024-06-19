package sample.enums;

import lombok.Getter;

/**
 * 航站楼枚举类
 * @author guokun
 * @date 2024/6/19 22:15
 */
@Getter
public enum TerminalEnum {
    RC_T1("仁川", "T1"),
    RC_T2("仁川", "T2"),

    CT_T1("成田", "T1"),
    CT_T2("成田", "T2"),

    JP_T1("金浦", "T1"),

    YT_T3("羽田", "T3"),
    ;

    private String terminalName;
    private String terminalNo;

    TerminalEnum(String terminalName, String terminalNo) {
        this.terminalName = terminalName;
        this.terminalNo = terminalNo;
    }
}
