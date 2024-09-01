package sample.pojo;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import sample.enums.SexEnum;

import java.util.Date;

@Data
public class JapanVisaApplication {
    // Surname (as shown in passport) 姓氏（护照上显示的）
    private String surname="";

    // Given and middle names (as shown in passport) 名字和中间名（护照上显示的）
    private String givenAndMiddleNames="";

    // Other names (including any other names you are or have been known by) 其他名字（包括您现在或曾经使用的任何其他名字）
    private String otherNames="NONE";

    // Date of birth  出生日期
    private String dateOfBirth="";

    // Place of birth  出生地点
    private String placeOfBirthCountry="";

    /**
     * 个人的性别。
     * 1 - 男性（Male）
     * 2 - 女性（Female）
     */
    private Integer sex=-1;


    /**
     * 个人的婚姻状况。
     * 1 - 单身（Single）
     * 2 - 已婚（Married）
     * 3 - 寡妇/寡夫（Widowed）
     * 4 - 离异（Divorced）
     */
    private Integer maritalStatus=-1;

    // Nationality or citizenship 国籍或公民身份
    private String nationalityOrCitizenship="";

    // Former and/or other nationalities or citizenships 以前和/或其他国籍或公民身份
    private String formerNationalitiesOrCitizenships="";

    // ID No. issued to you by your government 您政府颁发给您的身份证号码
    private String governmentIdNumber="";

    /**
     * 护照类型，可以是以下选项之一：
     * 1 - 外交护照（Diplomatic）
     * 2 - 公务护照（Official）
     * 3 - 普通护照（Ordinary）
     * 4 - 其他类型的护照（Other）
     */
    private Integer passportType=-1;


    // Passport No. 护照号码
    private String passportNumber="";

    // Place of issue 发行地点
    private String placeOfIssue="";

    // Date of issue 发行日期
    private String dateOfIssue="";

    // Issuing authority 发行机关
    private String issuingAuthority="";

    // Date of expiry 过期日期
    private String dateOfExpiry="";

    // Certificate of Eligibility No. 合格证书号码
    private String certificateOfEligibilityNumber="";

    // Purpose of visit to Japan/Status of residence 访问日本目的/居留状态
    private String purposeOfVisitOrResidenceStatus="";

    // Intended length of stay in Japan 计划在日本停留时间
    private String intendedLengthOfStay="";

    // Date of arrival in Japan 到达日本日期
    private String dateOfArrivalInJapan="";

    // Port of entry into Japan 入境日本口岸
    private String portOfEntryIntoJapan="";

    // Name of ship or airline 船名或航空公司名称
    private String nameOfShipOrAirline="";

    // Names and addresses of hotels or persons with whom applicant intends to stay 申请人打算入住的酒店或个人的名称和地址
    private String namesAndAddressesOfIntendedStays="";

    // TEL 酒店电话
    private String telOfIntendedStays="";

    // 酒店地址
    private String addressOfIntendedStays="";

    //  Dates and duration of previous stays in Japan 日期和持续时间
    private String previousStaysInJapan="";

    // Your current residential address 您当前的住址
    private String currentResidentialAddress="";

    // Tel. 电话
    private String telephone="";

    // Mobile No. 手机号码
    private String mobileNumber="";

    // E-Mail 电子邮件
    private String email="";

    // Current profession or occupation and position 目前的职业或职业和职位
    private String professionOrOccupation="";

    // Employer name 雇主名称
    private String employerName="";

    // Employer telephone 雇主电话
    private String employerTelephone="";

    // Employer address 雇主地址
    private String employerAddress="";

    //(Note)Partner's profession/occupation (or that of parents, if applicant is a minor):
    //伴侣的职业/职业（或父母的职业，如果申请人是未成年人）：
    private String partnersProfessionOrOccupation="";

    //Date of application
    //申请单日期
    private String dateOfApplication="";

}
