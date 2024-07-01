package sample.ctrl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.pinyin.PinyinUtil;
import com.sun.javafx.scene.control.ReadOnlyUnbackedObservableList;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.doc.ItineraryDoc;
import sample.doc.RequisitionDoc;
import sample.doc.TicketDoc;
import sample.enums.*;
import sample.model.CacheData;
import sample.model.Hotel;
import sample.model.TouristSpot;
import sample.pojo.*;
import sample.utils.MyUtil;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;
import java.util.stream.Collectors;

import static cn.hutool.core.date.DatePattern.PURE_DATE_PATTERN;
import static sample.utils.MyUtil.addDay;

public class Controller implements Initializable {
    /**
     * 姓氏
     */
    public TextField lastNameField;
    /**
     * 名称
     */
    public TextField firstNameField;
    /**
     * 年龄
     */
    public TextField ageField;
    /**
     * 性别
     */
    public ComboBox<String> sexCombo;
    /**
     * 出生日期
     */
    public DatePicker birthdayPicker;
    /**
     * 出生地
     */
    public TextField birthplaceField;
    /**
     * 国籍
     */
    public TextField nationalityField;
    /**
     * 身份证号码
     */
    public TextField idnField;
    /**
     * 联系电话
     */
    public TextField phoneField;
    /**
     * 婚姻情况
     */
    public ComboBox<String> marriageCombo;
    /**
     * 电子邮箱
     */
    public TextField emailField;
    /**
     * 居住地址
     */
    public TextField addressField;
    /**
     * 职业
     */
    public ComboBox<String> occupationCombo;
    /**
     * 公司/学校 名称
     */
    public TextField companyName;
    /**
     * 公司/学校 联系电话
     */
    public TextField companyPhone;
    /**
     * 出发日期
     */
    public DatePicker startDatePicker;
    /**
     * 返航日期
     */
    public DatePicker endDatePicker;
    /**
     * 机票导出路径
     */
    public TextField ticketPathField;
    /**
     * 申请单导出路径
     */
    public TextField applicationPathField;
    /**
     * 行程单导出路径
     */
    public TextField travelPathField;
    /**
     * 导出按钮
     */
    public Button exportFileButton;
    /**
     * 导出文件名称格式
     */
    public TextField fileNameField;
    /**
     * 公司/学校 地址
     */
    public TextField companyAddress;
    /**
     * 酒店选择器
     */
    public ComboBox<String> hotelCombo;
    public TextField last1yStayDaysField;
    public DatePicker lastStayEndDtPicker;
    public DatePicker lastStayStartDtPicker;
    public ComboBox<String> backFlightCombo;
    public Button randomFlightButton;
    public ComboBox<String> flightCombo;
    /**
     * 护照号码
     */
    public TextField passportField;
    /**
     * 护照类型
     */
    public ComboBox<String> passportTypeComb;


    /**
     * 主要信息缓存
     */
    private CacheData cacheData;

    /**
     * 界面资源加载完后调用该方法进行初始化
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTooTip();

        initComboBox();

        initDatePicker();

        initButtonClick();

//        clearPanelValues();
    }

    /**
     * 初始化日期选择器
     */
    private void initDatePicker() {
        startDatePicker.setValue(LocalDate.now());

        // 默认相隔4天（一共五天）
        endDatePicker.setValue(LocalDate.now().plusDays(4));

        birthdayPicker.setValue(LocalDate.now().minusYears(20));
    }

    private void popMsg(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pop Message");
        alert.setHeaderText(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    /**
     * 初始化按钮点击事件
     */
    private void initButtonClick() {
        exportFileButton.setOnAction(event -> {
            try {
                collectInfos();

                exportFiles();

                popMsg("导出成功！", "成功将客户[" + cacheData.getUserInfo().getChineseLastName() + cacheData.getUserInfo().getChineseFirstName() + "]的申请表、行程单、机票信息导出。");
            } catch (Exception e) {
                popMsg("操作失败！", e.getMessage());
                throw e;
            }
        });

        randomFlightButton.setOnAction(event -> {
            flightCombo.getSelectionModel().select(RandomUtil.randomInt(flightCombo.getItems().size()));
            backFlightCombo.getSelectionModel().select(RandomUtil.randomInt(backFlightCombo.getItems().size()));
        });
    }

    /**
     * 导出文件
     */
    private void exportFiles() {
        String now = LocalDateTimeUtil.format(LocalDateTimeUtil.now(), PURE_DATE_PATTERN);
        UserInformation userInfo = cacheData.getUserInfo();
        System.out.println("-------------------------------"+userInfo);
        String name = userInfo.getChineseLastName() + userInfo.getChineseFirstName();
        String pinyin = userInfo.getEnglishLastName() + StrUtil.SPACE + userInfo.getEnglishFirstName();
        String fileName = name + StrUtil.UNDERLINE + now;

        String applicationPath = applicationPathField.getText();
        String travelPath = travelPathField.getText();
        String ticketPath = ticketPathField.getText();

        // 导出申请表
        RequisitionDoc.handle(userInfo, applicationPath, fileName);

        // 导出行程单
        ItineraryDoc.handle(cacheData.getItineraryInfoList(), travelPath,
                now.substring(0, 4), now.substring(4, 6), now.substring(6, 8),
                pinyin, fileName,
                cacheData.getHotel().getName(), cacheData.getHotel().getAddress(), cacheData.getHotel().getPhone());

        // 导出机票
        TicketDoc.handle(cacheData.getTicketInfo(), cacheData.getFlightInfo(), cacheData.getBackFlightInfo(), fileName, ticketPath,
                1079, 1542);
    }

    /**
     * 收集用户输入转换为class
     */
    private void collectInfos() {
        initCacheData();

        fillUserInfo();

        fillFlightInfo();

        fillItineraryInfo();

        fillJapanVisaApplicationInfo();
    }

    private void fillJapanVisaApplicationInfo() {
        JapanVisaApplication info = new JapanVisaApplication();
        cacheData.setJapanVisaApplication(info);

        info.setEmail(emailField.getText());
        DateTimeFormatter ddMMyyyy = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        info.setDateOfBirth(birthdayPicker.getValue() == null ?
                StrUtil.EMPTY :
                birthdayPicker.getValue().format(ddMMyyyy));
        info.setSurname(PinyinUtil.getPinyin(lastNameField.getText()).toLowerCase(Locale.ROOT));
        info.setGivenAndMiddleNames(PinyinUtil.getPinyin(firstNameField.getText()).toLowerCase(Locale.ROOT));
        info.setPlaceOfBirthCountry(birthplaceField.getText());
        info.setSex(sexCombo.getSelectionModel().getSelectedIndex() + 1);
        info.setMaritalStatus(marriageCombo.getSelectionModel().getSelectedIndex() + 1);
        info.setNationalityOrCitizenship(nationalityField.getText());
        info.setFormerNationalitiesOrCitizenships(nationalityField.getText());
        info.setGovernmentIdNumber(idnField.getText());
        info.setPassportType(passportTypeComb.getSelectionModel().getSelectedIndex() + 1);
        info.setPassportNumber(passportField.getText());
        
        info.setPlaceOfIssue(StrUtil.EMPTY);
        info.setDateOfIssue(StrUtil.EMPTY);
        info.setIssuingAuthority(StrUtil.EMPTY);
        info.setDateOfExpiry(StrUtil.EMPTY);

        info.setCertificateOfEligibilityNumber(StrUtil.EMPTY);
        info.setPurposeOfVisitOrResidenceStatus(StrUtil.EMPTY);
    }

    /**
     * 填充化行程单信息
     */
    private void fillItineraryInfo() {
        cacheData.setItineraryInfoList(new ArrayList<>());

        // 随机分配的景点
        Map<String, List<TouristSpot>> randomTouristMap = cacheData.getTouristMap();

        for (String dt = cacheData.getStartDt(); dt.compareTo(cacheData.getEndDt()) <= 0; dt = addDay(dt)) {
            List<TouristSpot> touristSpots = randomTouristMap.get(dt);

            Itinerary itinerary = new Itinerary();
            cacheData.getItineraryInfoList().add(itinerary);

            itinerary.setDate(String.format("%s.%s.%s", dt.substring(0, 4), dt.substring(4, 6), dt.substring(6, 8)));
            ArrayList<String> activityPlan = new ArrayList<>(4);
            if (dt.equals(cacheData.getStartDt())) {
                activityPlan.add(AirportEnum.ICN.getFromToStr(AirportEnum.NRT));
                itinerary.setContactNumber(phoneField.getText());
            } else if (dt.equals(cacheData.getEndDt())) {
                activityPlan.add(AirportEnum.NRT.getFromToStr(AirportEnum.ICN));
            }
            activityPlan.addAll(touristSpots.stream().map(TouristSpot::getEnglishName).collect(Collectors.toList()));
            itinerary.setActivityPlan(activityPlan);
        }
    }

    /**
     * 填充机票信息
     */
    private void fillFlightInfo() {
        cacheData.setFlightInfo(FligihtInfo.createFlightInfo(cacheData.getFlight(), cacheData.getStartDt()));
        cacheData.setBackFlightInfo(FligihtInfo.createFlightInfo(cacheData.getBackFlight(), cacheData.getEndDt()));
        Ticket ticketInfo = new Ticket();
        cacheData.setTicketInfo(ticketInfo);
        ticketInfo.setAirlinePnr(RandomUtil.randomStringUpper(6));
        ticketInfo.setBookingPnr(RandomUtil.randomStringUpper(6));
        ticketInfo.seteTicketNumber(cacheData.getFlight().getCompany().getRandomTicketNum());
        ticketInfo.setPassengerName(cacheData.getUserInfo().getEnglishLastName() + StrUtil.SLASH + cacheData.getUserInfo().getEnglishFirstName(), SexEnum.values()[sexCombo.getSelectionModel().getSelectedIndex()]);
        ticketInfo.setIdNumber(passportField.getText());
        ticketInfo.setConjunctionTicketNumber("");
        ticketInfo.setDateOfIssue(LocalDateTimeUtil.now().format(DatePattern.NORM_DATE_FORMATTER));
        ticketInfo.setIataCode(RandomUtil.randomNumbers(8));
        ticketInfo.setIssuingAirline(cacheData.getFlight().getCompany().getEnglishName());
    }

    /**
     * 初始化缓存
     */
    private void initCacheData() {
        cacheData = new CacheData();

        cacheData.setFlight(FLIGHT_LIST.get(flightCombo.getSelectionModel().getSelectedIndex()));
        cacheData.setBackFlight(BACK_FLIGHT_LIST.get(backFlightCombo.getSelectionModel().getSelectedIndex()));
        cacheData.setStartDt(LocalDateTimeUtil.format(startDatePicker.getValue(), PURE_DATE_PATTERN));
        cacheData.setEndDt(LocalDateTimeUtil.format(endDatePicker.getValue(), PURE_DATE_PATTERN));
        cacheData.setTouristMap(MyUtil.getRandomTouristMap(cacheData.getStartDt(), cacheData.getEndDt()));


        cacheData.setHotel(Hotel.HOTEL_LIST.get(hotelCombo.getSelectionModel().getSelectedIndex()));
    }

    /**
     * 填充用户信息
     */
    private void fillUserInfo() {
        cacheData.setUserInfo(new UserInformation());
        cacheData.getUserInfo().setPassportNum(passportField.getText());
        cacheData.getUserInfo().setChineseLastName(lastNameField.getText());
        cacheData.getUserInfo().setChineseFirstName(firstNameField.getText());
        cacheData.getUserInfo().setGender(MyUtil.setCheck(cacheData.getUserInfo().getGender(), sexCombo.getSelectionModel().getSelectedIndex()));
        cacheData.getUserInfo().setDateOfBirth(DateUtil.format(LocalDateTimeUtil.of(birthdayPicker.getValue()), PURE_DATE_PATTERN));
        cacheData.getUserInfo().setPlaceOfBirth(birthplaceField.getText());
        cacheData.getUserInfo().setMaritalStatus(MyUtil.setCheck(cacheData.getUserInfo().getMaritalStatus(), marriageCombo.getSelectionModel().getSelectedIndex()));
        cacheData.getUserInfo().setNationality(nationalityField.getText());
        cacheData.getUserInfo().setForeignerRegistrationNumber(idnField.getText());
        cacheData.getUserInfo().setContactNumber(phoneField.getText());
        cacheData.getUserInfo().setEmail(emailField.getText());
        cacheData.getUserInfo().setAddress(addressField.getText());
        cacheData.getUserInfo().setCurrentOccupationAndPosition(MyUtil.setCheck(cacheData.getUserInfo().getCurrentOccupationAndPosition(), occupationCombo.getSelectionModel().getSelectedIndex()));
        cacheData.getUserInfo().setCompanyOrSchoolName(companyName.getText());
        cacheData.getUserInfo().setCompanyOrSchoolPhoneNumber(companyPhone.getText());
        cacheData.getUserInfo().setCompanyOrSchoolAddress(companyAddress.getText());
        cacheData.getUserInfo().setEnglishLastName(PinyinUtil.getPinyin(lastNameField.getText(), StrUtil.EMPTY).toUpperCase(Locale.ROOT));
        cacheData.getUserInfo().setEnglishFirstName(PinyinUtil.getPinyin(firstNameField.getText(), StrUtil.EMPTY).toUpperCase(Locale.ROOT));
        LocalDate startDt = startDatePicker.getValue();
        LocalDate endDt = endDatePicker.getValue();
        cacheData.getUserInfo().setPlannedDurationOfStayInJapan(String.format(UserInformation.DURATION_FORMAT,
                StrUtil.fillBefore(String.valueOf(startDt.getYear()), '0', 4),
                StrUtil.fillBefore(String.valueOf(startDt.getMonthValue()), '0', 2),
                StrUtil.fillBefore(String.valueOf(startDt.getDayOfMonth()), '0', 2),
                StrUtil.fillBefore(String.valueOf(endDt.getYear()), '0', 4),
                StrUtil.fillBefore(String.valueOf(endDt.getMonthValue()), '0', 2),
                StrUtil.fillBefore(String.valueOf(endDt.getDayOfMonth()), '0', 2)
        ));

        LocalDate lastStayStartDtPickerValue = lastStayStartDtPicker.getValue();
        LocalDate lastStayEndDtPickerValue = lastStayEndDtPicker.getValue();
        if (null != lastStayEndDtPickerValue && null != lastStayStartDtPickerValue) {
            cacheData.getUserInfo().setLastStayInJapanDuration(String.format(UserInformation.DURATION_FORMAT,
                    StrUtil.fillBefore(String.valueOf(lastStayStartDtPickerValue.getYear()), '0', 4),
                    StrUtil.fillBefore(String.valueOf(lastStayStartDtPickerValue.getMonthValue()), '0', 2),
                    StrUtil.fillBefore(String.valueOf(lastStayStartDtPickerValue.getDayOfMonth()), '0', 2),
                    StrUtil.fillBefore(String.valueOf(lastStayEndDtPickerValue.getYear()), '0', 4),
                    StrUtil.fillBefore(String.valueOf(lastStayEndDtPickerValue.getMonthValue()), '0', 2),
                    StrUtil.fillBefore(String.valueOf(lastStayEndDtPickerValue.getDayOfMonth()), '0', 2)
            ));
        }

        String last1yStayDaysFieldText = last1yStayDaysField.getText();
        if (StrUtil.isNotBlank(last1yStayDaysFieldText)) {
            cacheData.getUserInfo().setTotalStayDurationInJapanLastYear(
                    String.format(UserInformation.STAY_DURATION_JAPAN_LAST_YEAR_FORMAT, last1yStayDaysFieldText)
            );
        }

        // 港口,航班号
        cacheData.getUserInfo().setEntryPortOrFlightNumber(cacheData.getFlight().getEndTerminal().getAirport()
                + StrPool.COMMA
                + cacheData.getFlight().getCode());
    }



    /**
     * 初始化提示语
     */
    private void initTooTip() {

    }

    /**
     * 初始化提示语
     */
    private void initComboBox() {
        sexCombo.setItems(new ReadOnlyUnbackedObservableList<String>() {
            @Override
            public String get(int i) {
                return SexEnum.values()[i].getDesc();
            }

            @Override
            public int size() {
                return SexEnum.values().length;
            }
        });
        sexCombo.getSelectionModel().select(SexEnum.MAN.ordinal());

        marriageCombo.setItems(new ReadOnlyUnbackedObservableList<String>() {
            @Override
            public String get(int i) {
                return MarriageEnum.values()[i].getDesc();
            }

            @Override
            public int size() {
                return MarriageEnum.values().length;
            }
        });
        marriageCombo.getSelectionModel().select(MarriageEnum.UNMARRIED.ordinal());

        occupationCombo.setItems(new ReadOnlyUnbackedObservableList<String>() {
            @Override
            public String get(int i) {
                return OccupationEnum.values()[i].getDesc();
            }

            @Override
            public int size() {
                return OccupationEnum.values().length;
            }
        });
        occupationCombo.getSelectionModel().select(OccupationEnum.STUDENT.ordinal());

        hotelCombo.setItems(new ReadOnlyUnbackedObservableList<String>() {
            @Override
            public String get(int i) {
                return Hotel.HOTEL_LIST.get(i).getName();
            }

            @Override
            public int size() {
                return Hotel.HOTEL_LIST.size();
            }
        });
        hotelCombo.getSelectionModel().select(RandomUtil.randomInt(Hotel.HOTEL_LIST.size()));


        flightCombo.setItems(new ReadOnlyUnbackedObservableList<String>() {


            @Override
            public String get(int i) {
                FlightEnum flightEnum = FLIGHT_LIST.get(i);
                return getFlightComboStr(flightEnum);
            }

            @Override
            public int size() {
                return FLIGHT_LIST.size();
            }
        });
        flightCombo.getSelectionModel().select(RandomUtil.randomInt(FLIGHT_LIST.size()));


        backFlightCombo.setItems(new ReadOnlyUnbackedObservableList<String>() {


            @Override
            public String get(int i) {
                FlightEnum flightEnum = BACK_FLIGHT_LIST.get(i);
                return getFlightComboStr(flightEnum);
            }

            @Override
            public int size() {
                return BACK_FLIGHT_LIST.size();
            }
        });
        backFlightCombo.getSelectionModel().select(RandomUtil.randomInt(BACK_FLIGHT_LIST.size()));

        passportTypeComb.setItems(new ReadOnlyUnbackedObservableList<String>() {


            @Override
            public String get(int i) {
                return RandomUtil.randomEle(PassportTypeEnum.values()).getDesc();
            }

            @Override
            public int size() {
                return PassportTypeEnum.values().length;
            }
        });
        passportTypeComb.getSelectionModel().select(PassportTypeEnum.DIPLOMATIC.ordinal());

    }

    private String getFlightComboStr(FlightEnum flightEnum) {
        return flightEnum.getCode() + StrPool.AT +
                flightEnum.getStartTerminal().getTerminalName() + flightEnum.getStartTerminal().getTerminalNo()
                + StrPool.DASHED +
                flightEnum.getEndTerminal().getTerminalName() + flightEnum.getEndTerminal().getTerminalNo()
                + StrPool.BRACKET_START + flightEnum.getStartTime() + StrPool.COMMA
                + flightEnum.getEndTime() + StrPool.BRACKET_END
                + StrPool.AT + flightEnum.getCompany().getChineseName();
    }

    List<FlightEnum> FLIGHT_LIST = Arrays.stream(FlightEnum.values()).filter(f ->
            f.getStartTerminal().getAirport() == AirportEnum.ICN
    ).collect(Collectors.toList());

    List<FlightEnum> BACK_FLIGHT_LIST = Arrays.stream(FlightEnum.values()).filter(f ->
            f.getStartTerminal().getAirport() == AirportEnum.NRT
    ).collect(Collectors.toList());
}
