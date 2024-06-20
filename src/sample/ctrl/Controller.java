package sample.ctrl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.pinyin.PinyinUtil;
import com.sun.javafx.scene.control.ReadOnlyUnbackedObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import sample.doc.ItineraryDoc;
import sample.doc.RequisitionDoc;
import sample.enums.*;
import sample.model.CacheData;
import sample.model.Hotel;
import sample.model.TouristSpot;
import sample.pojo.FlightInfo;
import sample.pojo.Itinerary;
import sample.pojo.UserInformation;
import sample.utils.MyUtil;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    /**
     * 初始化按钮点击事件
     */
    private void initButtonClick() {
        exportFileButton.setOnAction(event -> {
            collectInfos();

            exportFiles();
        });
    }

    /**
     * 导出文件
     */
    private void exportFiles() {
        String now = LocalDateTimeUtil.format(LocalDateTimeUtil.now(), PURE_DATE_PATTERN);
        UserInformation userInfo = cacheData.getUserInfo();
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
                pinyin, fileName);
    }

    /**
     * 收集用户输入转换为class
     */
    private void collectInfos() {
        initCacheData();

        fillUserInfo();

        fillFlightInfo();

        fillItineraryInfo();
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
                Hotel hotel = cacheData.getHotel();
                itinerary.setContactNumber(hotel.getPhone());
                itinerary.setAccommodationsAddress(hotel.getAddress());
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
        cacheData.setFlightInfo(new FlightInfo());


    }

    /**
     * 初始化缓存
     */
    private void initCacheData() {
        cacheData = new CacheData();

        cacheData.setFlight(RandomUtil.randomEle(Arrays.stream(FlightEnum.values()).filter(f ->
                f.getStartTerminal().getAirport() == AirportEnum.ICN
        ).collect(Collectors.toList())));
        cacheData.setFlight(RandomUtil.randomEle(Arrays.stream(FlightEnum.values()).filter(f ->
                f.getEndTerminal().getAirport() == AirportEnum.ICN
        ).collect(Collectors.toList())));
        cacheData.setStartDt(LocalDateTimeUtil.format(startDatePicker.getValue(), PURE_DATE_PATTERN));
        cacheData.setEndDt(LocalDateTimeUtil.format(endDatePicker.getValue(), PURE_DATE_PATTERN));
        cacheData.setHotel(RandomUtil.randomEle(new ArrayList<>(Hotel.HOTEL_MAP.values())));
        cacheData.setTouristMap(MyUtil.getRandomTouristMap(cacheData.getStartDt(), cacheData.getEndDt()));
    }

    /**
     * 填充用户信息
     */
    private void fillUserInfo() {
        cacheData.setUserInfo(new UserInformation());
        cacheData.getUserInfo().setChineseLastName(lastNameField.getText());
        cacheData.getUserInfo().setChineseFirstName(firstNameField.getText());
        cacheData.getUserInfo().setGender(MyUtil.setCheck(cacheData.getUserInfo().getGender(), sexCombo.getSelectionModel().getSelectedIndex()));
        cacheData.getUserInfo().setDateOfBirth(DateUtil.format(LocalDateTimeUtil.of(birthdayPicker.getValue()), PURE_DATE_PATTERN));
        cacheData.getUserInfo().setPlaceOfBirth(birthplaceField.getText());
        cacheData.getUserInfo().setMaritalStatus(MyUtil.setCheck(cacheData.getUserInfo().getGender(), marriageCombo.getSelectionModel().getSelectedIndex()));
        cacheData.getUserInfo().setNationality(nationalityField.getText());
        cacheData.getUserInfo().setForeignerRegistrationNumber(idnField.getText());
        cacheData.getUserInfo().setContactNumber(phoneField.getText());
        cacheData.getUserInfo().setEmail(emailField.getText());
        cacheData.getUserInfo().setAddress(addressField.getText());
        cacheData.getUserInfo().setCurrentOccupationAndPosition(MyUtil.setCheck(cacheData.getUserInfo().getGender(), occupationCombo.getSelectionModel().getSelectedIndex()));
        cacheData.getUserInfo().setCompanyOrSchoolName(companyName.getText());
        cacheData.getUserInfo().setCompanyOrSchoolPhoneNumber(companyPhone.getText());
        cacheData.getUserInfo().setCompanyOrSchoolAddress(companyAddress.getText());
        cacheData.getUserInfo().setEnglishLastName(PinyinUtil.getPinyin(lastNameField.getText(), StrUtil.EMPTY).toUpperCase(Locale.ROOT));
        cacheData.getUserInfo().setEnglishFirstName(PinyinUtil.getPinyin(firstNameField.getText(), StrUtil.EMPTY).toUpperCase(Locale.ROOT));
        LocalDate startDt = startDatePicker.getValue();
        LocalDate endDt = endDatePicker.getValue();
        cacheData.getUserInfo().setPlannedDurationOfStayInJapan(String.format(cacheData.getUserInfo().getPlannedDurationOfStayInJapan(),
                StrUtil.fillBefore(String.valueOf(startDt.getYear()), '0', 4),
                StrUtil.fillBefore(String.valueOf(startDt.getMonthValue()), '0', 2),
                StrUtil.fillBefore(String.valueOf(startDt.getDayOfMonth()), '0', 2),
                StrUtil.fillBefore(String.valueOf(endDt.getYear()), '0', 4),
                StrUtil.fillBefore(String.valueOf(endDt.getMonthValue()), '0', 2),
                StrUtil.fillBefore(String.valueOf(endDt.getDayOfMonth()), '0', 2)
        ));
        cacheData.getUserInfo().setEntryPortOrFlightNumber(cacheData.getFlight().getCode());
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
    }
}
