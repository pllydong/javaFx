package sample.pojo;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import sample.enums.SexEnum;

import java.util.Date;

@Data
public class JapanVisaApplication {
    // Surname (as shown in passport) 姓氏（护照上显示的）
    private String surname="zheng";

    // Given and middle names (as shown in passport) 名字和中间名（护照上显示的）
    private String givenAndMiddleNames="yu long";

    // Other names (including any other names you are or have been known by) 其他名字（包括您现在或曾经使用的任何其他名字）
    private String otherNames="";

    // Date of birth  出生日期
    private String dateOfBirth="2010-1-1";

    // Place of birth  出生地点
    private String placeOfBirthCountry="東京都渋谷区1-3-chome, Shibuya, Tokyo";

    /**
     * 个人的性别。
     * 1 - 男性（Male）
     * 2 - 女性（Female）
     */
    private Integer sex=2;


    /**
     * 个人的婚姻状况。
     * 1 - 单身（Single）
     * 2 - 已婚（Married）
     * 3 - 寡妇/寡夫（Widowed）
     * 4 - 离异（Divorced）
     */
    private Integer maritalStatus=3;

    // Nationality or citizenship 国籍或公民身份
    private String nationalityOrCitizenship="China";

    // Former and/or other nationalities or citizenships 以前和/或其他国籍或公民身份
    private String formerNationalitiesOrCitizenships="CHINA";

    // ID No. issued to you by your government 您政府颁发给您的身份证号码
    private String governmentIdNumber="6416546516565161";

    /**
     * 护照类型，可以是以下选项之一：
     * 1 - 外交护照（Diplomatic）
     * 2 - 公务护照（Official）
     * 3 - 普通护照（Ordinary）
     * 4 - 其他类型的护照（Other）
     */
    private Integer passportType=2;


    // Passport No. 护照号码
    private String passportNumber="124125215612356";

    // Place of issue 发行地点
    private String placeOfIssue="東京都渋谷区1-3-chome, Shibuya, Tokyo";

    // Date of issue 发行日期
    private String dateOfIssue="2024-123-123";

    // Issuing authority 发行机关
    private String issuingAuthority="萨菲发啥SGS昂";

    // Date of expiry 过期日期
    private String dateOfExpiry="2024-123-12";

    // Certificate of Eligibility No. 合格证书号码
    private String certificateOfEligibilityNumber="2142145215125";

    // Purpose of visit to Japan/Status of residence 访问日本目的/居留状态
    private String purposeOfVisitOrResidenceStatus="正常";

    // Intended length of stay in Japan 计划在日本停留时间
    private String intendedLengthOfStay="188";

    // Date of arrival in Japan 到达日本日期
    private String dateOfArrivalInJapan="2302-21-21";

    // Port of entry into Japan 入境日本口岸
    private String portOfEntryIntoJapan="赛哈佛啊似乎给你";

    // Name of ship or airline 船名或航空公司名称
    private String nameOfShipOrAirline="随便";

    // Names and addresses of hotels or persons with whom applicant intends to stay 申请人打算入住的酒店或个人的名称和地址
    private String namesAndAddressesOfIntendedStays="酒店";

    // TEL 酒店电话
    private String telOfIntendedStays="2142142145";

    // 酒店地址
    private String addressOfIntendedStays="東京都渋谷区1-3-chome, Shibuya, Tokyo";

    //  Dates and duration of previous stays in Japan 日期和持续时间
    private String previousStaysInJapan="2024-2-15";

    // Your current residential address 您当前的住址
    private String currentResidentialAddress="東京都渋谷区1-3-chome, Shibuya, Tokyo";

    // Tel. 电话
    private String telephone="214214515";

    // Mobile No. 手机号码
    private String mobileNumber="25215125165";

    // E-Mail 电子邮件
    private String email="54165465@ggag.com";

    // Current profession or occupation and position 目前的职业或职业和职位
    private String professionOrOccupation="学生";

    // Employer name 雇主名称
    private String employerName="2134124";

    // Employer telephone 雇主电话
    private String employerTelephone="2141251252154";

    // Employer address 雇主地址
    private String employerAddress="東京都渋谷区1-3-chome, Shibuya, Tokyo";

    //(Note)Partner's profession/occupation (or that of parents, if applicant is a minor):
    //伴侣的职业/职业（或父母的职业，如果申请人是未成年人）：
    private String partnersProfessionOrOccupation="不是啥水果214125";

}
