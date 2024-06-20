package sample.enums;

import lombok.Getter;

/**
 * 性别枚举
 *
 * @author guokun
 * @date 2024/6/20 21:04
 */
@Getter
public enum OccupationEnum {
    STAFF("staff", "会社员(职员)"),
    SELF_OPERATING("self-operating", "自营业"),
    DAILY_WORK("daily word", "日当"),
    JOBLESS("jobless", "无业"),
    STUDENT("student", "学生"),
    ;

    private String code;
    private String desc;

    OccupationEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
