package sample.pojo;

import lombok.Data;

@Data
public class UserInformation {
    /**
     * 영문 - 성
     * 英文 - 姓
     */
    private String englishLastName = "ZHENG";

    /**
     * 한자 - 성
     * 汉字 - 姓
     */
    private String chineseLastName = "郑";

    /**
     * 영문 - 명
     * 英文 - 名
     */
    private String englishFirstName = "YULONG";

    /**
     * 한자 - 명
     * 汉字 - 名
     */
    private String chineseFirstName = "禹龙";

    /**
     * 생년월일
     * 出生日期
     */
    private String dateOfBirth = "19951202";

    /**
     * 출생지
     * 出生地
     */
    private String placeOfBirth = "JILIN";

    /**
     * 성별
     * 性别
     */
    private String gender = "☑ 남         ☐ 여";

    /**
     * 혼인 여부
     * 婚姻状况
     */
    private String maritalStatus = " ☑  미혼     ☐ 기혼     ☐ 사별     ☐ 이혼";

    /**
     * 국적
     * 国籍
     */
    private String nationality = "CHINA";

    /**
     * 외국인등록증번호
     * 外国人登记证号码
     */
    private String foreignerRegistrationNumber = "951202—5760018";

    /**
     * 연락처
     * 联系电话
     */
    private String contactNumber = "010-5139-8700";

    /**
     * 이메일
     * 电子邮件
     */
    private String email = "1006159533@qq.com";

    /**
     * 현주소(등록증 위에 기재된 주소)
     * 现地址（登记证上的地址）
     */
    private String address = "경기도 파주시 문산읍 돈유안길135 305호";

    /**
     * 현 직업 및 직위
     * 现职业及职位
     */
    private String currentOccupationAndPosition = "☐ 회사원     ☐ 자영업     ☐ 일용직     ☑ 무직     ☐ 학생";

    /**
     * 회사 / 학교 명칭
     * 公司 / 学校 名称
     */
    private String companyOrSchoolName = "";

    /**
     * 전화 번호
     * 电话号码
     */
    private String companyOrSchoolPhoneNumber = "";

    /**
     * 회사 / 학교 주소
     * 公司 / 学校 地址
     */
    private String companyOrSchoolAddress = "";

    /**
     * 일본입국예정도시
     * 日本入境预定城市
     */
    private String plannedCityOfEntryInJapan = "NRT";

    /**
     * 입국항(항공편번호)
     * 入境港（航班号）
     */
    private String entryPortOrFlightNumber = "KE703";

    /**
     * 일본에서 체류 예정 기간
     * 在日本预定停留期间
     */
    private String plannedDurationOfStayInJapan = "%s년 %s 월 %s 일부터 %s 년 %s 월 %s 일까지";

    /**
     * 마지막으로 일본에 체류한 기간
     * 最后一次在日本停留期间
     */
    private String lastStayInJapanDuration = "NO 년 월 일부터 년 월 일까지";

    /**
     * 최근1년내 일본에 체류한 기간
     * 最近一年在日本的停留期间
     */
    private String totalStayDurationInJapanLastYear = "NO총 ( )일 체류";
}
