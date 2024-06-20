package sample.enums;

import lombok.Getter;

/**
 * 性别枚举
 * @author guokun
 * @date 2024/6/20 21:04
 */
@Getter
public enum MarriageEnum {
    MARRIED("Y", "已婚"),
    UNMARRIED("N", "未婚"),
    ;

    private String code;
    private String desc;

    MarriageEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
