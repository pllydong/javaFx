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
import java.util.*;
import java.util.stream.Collectors;

import static cn.hutool.core.date.DatePattern.PURE_DATE_PATTERN;

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
     * 用户信息
     */
    private UserInformation userInfo;
    /**
     * 申请表信息
     */
    private List<Itinerary> itineraryInfoList;
    /**
     * 机票信息
     */
    private FlightInfo flightInfo;

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
        String fileName = lastNameField.getText() + firstNameField.getText();

        String applicationPath = applicationPathField.getText();

        RequisitionDoc.handle(userInfo, applicationPath, fileName);
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
        int startDtNum = Integer.parseInt(cacheData.getStartDt());
        int endDtNum = Integer.parseInt(cacheData.getEndDt());
        int size = endDtNum - startDtNum + 1;

        itineraryInfoList = new ArrayList<>(size);

        // 随机分配景点
        Map<String, List<TouristSpot>> randomTouristMap = MyUtil.getRandomTouristMap(cacheData.getStartDt(), cacheData.getEndDt());

        for (int i = startDtNum; i <= endDtNum; i++) {
            String dt = StrUtil.fillBefore(String.valueOf(i), '0', 8);
            List<TouristSpot> touristSpots = randomTouristMap.get(dt);

            Itinerary itinerary = new Itinerary();
            itineraryInfoList.add(itinerary);

            itinerary.setDate(String.format("%s.%s.%s", dt.substring(0, 4), dt.substring(4, 6), dt.substring(6, 8)));
            ArrayList<String> activityPlan = new ArrayList<>(4);
            if (i == startDtNum) {
                activityPlan.add(AirportEnum.ICN.getFromToStr(AirportEnum.NRT));
                Hotel hotel = cacheData.getHotel();
                itinerary.setContactNumber(hotel.getPhone());
                itinerary.setAccommodationsAddress(hotel.getAddress());
            } else if (i == endDtNum) {
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
        flightInfo = new FlightInfo();


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
        userInfo = new UserInformation();
        userInfo.setChineseLastName(lastNameField.getText());
        userInfo.setChineseFirstName(firstNameField.getText());
        userInfo.setGender(MyUtil.setCheck(userInfo.getGender(), sexCombo.getSelectionModel().getSelectedIndex()));
        userInfo.setDateOfBirth(DateUtil.format(LocalDateTimeUtil.of(birthdayPicker.getValue()), PURE_DATE_PATTERN));
        userInfo.setPlaceOfBirth(birthplaceField.getText());
        userInfo.setMaritalStatus(MyUtil.setCheck(userInfo.getGender(), marriageCombo.getSelectionModel().getSelectedIndex()));
        userInfo.setNationality(nationalityField.getText());
        userInfo.setForeignerRegistrationNumber(idnField.getText());
        userInfo.setContactNumber(phoneField.getText());
        userInfo.setEmail(emailField.getText());
        userInfo.setAddress(addressField.getText());
        userInfo.setCurrentOccupationAndPosition(MyUtil.setCheck(userInfo.getGender(), occupationCombo.getSelectionModel().getSelectedIndex()));
        userInfo.setCompanyOrSchoolName(companyName.getText());
        userInfo.setCompanyOrSchoolPhoneNumber(companyPhone.getText());
        userInfo.setCompanyOrSchoolAddress(companyAddress.getText());
        userInfo.setEnglishLastName(PinyinUtil.getPinyin(lastNameField.getText(), StrUtil.EMPTY).toUpperCase(Locale.ROOT));
        userInfo.setEnglishFirstName(PinyinUtil.getPinyin(firstNameField.getText(), StrUtil.EMPTY).toUpperCase(Locale.ROOT));
        LocalDate startDt = startDatePicker.getValue();
        LocalDate endDt = endDatePicker.getValue();
        userInfo.setPlannedDurationOfStayInJapan(String.format(userInfo.getPlannedDurationOfStayInJapan(),
                StrUtil.fillBefore(String.valueOf(startDt.getYear()), '0', 4),
                StrUtil.fillBefore(String.valueOf(startDt.getMonthValue()), '0', 2),
                StrUtil.fillBefore(String.valueOf(startDt.getDayOfMonth()), '0', 2),
                StrUtil.fillBefore(String.valueOf(endDt.getYear()), '0', 4),
                StrUtil.fillBefore(String.valueOf(endDt.getMonthValue()), '0', 2),
                StrUtil.fillBefore(String.valueOf(endDt.getDayOfMonth()), '0', 2)
        ));
        userInfo.setEntryPortOrFlightNumber(cacheData.getFlight().getCode());
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
