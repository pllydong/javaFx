package sample.enums;

import lombok.Getter;

/**
 * 性别枚举
 * @author guokun
 * @date 2024/6/20 21:04
 */
@Getter
public enum SexEnum {
    MAN("MR", "男"),
    WOMAN("MS", "女"),
    ;

    private String code;
    private String desc;

    SexEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
