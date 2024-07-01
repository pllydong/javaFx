package sample.enums;

import lombok.Getter;

/**
 * 护照类型，可以是以下选项之一：
 * 1 - 外交护照（Diplomatic）
 * 2 - 公务护照（Official）
 * 3 - 普通护照（Ordinary）
 * 4 - 其他类型的护照（Other）
 */
@Getter
public enum PassportTypeEnum {
    DIPLOMATIC("Diplomatic", "外交护照"),
    OFFICIAL("Official", "公务护照"),
    ORDINARY("Ordinary", "普通护照"),
    OTHER("Other", "其他类型的护照"),
    ;
    private final String code;
    private final String desc;

    PassportTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
