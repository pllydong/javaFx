package sample.enums;

import lombok.Getter;

/**
 * 婚姻状态枚举
 * @author guokun
 * @date 2024/6/20 21:04
 */
@Getter
public enum MarriageEnum {
    UNMARRIED("UNMARRIED", "未婚"),
    MARRIED("MARRIED", "已婚"),
    BEREAVED("BEREAVED", "丧偶"),
    DIVORCE("DIVORCE", "离婚"),
    ;

    private String code;
    private String desc;

    MarriageEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
