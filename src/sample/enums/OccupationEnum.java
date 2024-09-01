package sample.enums;

import lombok.Getter;

/**
 * 工作枚举
 *
 * @author guokun
 * @date 2024/6/20 21:04
 */
@Getter
public enum OccupationEnum {
    STAFF("office employee", "会社员(职员)"),
    SELF_OPERATING("self-employed", "自营业"),
    DAILY_WORK("part time", "日当"),
    JOBLESS("no occupation", "无职业"),
    STUDENT("student", "学生"),
    ;

    private String code;
    private String desc;

    OccupationEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    @Override
    public String toString() {
        return this.desc;
    }
}
