package sample.ctrl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.pinyin.PinyinUtil;
import com.sun.javafx.scene.control.ReadOnlyUnbackedObservableList;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.doc.ItineraryDoc;
import sample.doc.PdfFormHandler;
import sample.doc.RequisitionDoc;
import sample.doc.TicketDoc;
import sample.enums.*;
import sample.model.*;
import sample.pojo.*;
import sample.utils.ActivationUtil;
import sample.utils.MyUtil;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    public TextField companyPhoneField;
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
    public TextField companyAddressField;
    /**
     * 酒店选择器
     */
    public ComboBox<Hotel> hotelCombo;
    public TextField last1yStayDaysField;
    public DatePicker lastStayEndDtPicker;
    public DatePicker lastStayStartDtPicker;
//    public ComboBox<String> backFlightCombo;
//    public Button randomFlightButton;
//    public ComboBox<String> flightCombo;
    /**
     * 护照号码
     */
    public TextField passportField;
    /**
     * 护照类型
     */
    public ComboBox<String> passportTypeComb;
    public TextField issuingAuthorityField;
    public TextField placeOfIssueField;
    public DatePicker passportEndDtPicker;
    public DatePicker passportStartDtPicker;
    public TextField certificateField;
    public TextField purposeField;
    /**
     * 入境港口
     */
    public TextField portOfEntryIntoJapanField;
    public TreeView<Branch> branchTreeView;

    private final List<Hotel> showHotelList = new ArrayList<>();
    /**
     * 出发航班信息输入 [开始地点, 出发时间, 目的地, 抵达时间, 航班号码
     */
    public TextField startFlightField;
    /**
     * 返航航班信息输入
     */
    public TextField endFlightField;
    /**
     * 客户其他名称
     */
    public TextField otherNameField;
    /**
     * 申请单2日期选择器:默认当日
     */
    public DatePicker application2DatePicker;
    /**
     * 伴侣工作，仅已婚生效
     */
    public ComboBox<OccupationEnum> partnerOccupationCombo;


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

        initTreeView();

        initComboBox();

        initDatePicker();

        initButtonClick();

        clearPanelValues();

        activation();
    }

    private void initTreeView() {
        if (null == branchTreeView.getRoot()) {
            branchTreeView.setRoot(new TreeItem<>());
        }
        buildBranchTreeView(branchTreeView.getRoot(), CacheData.getRootBranch());

        branchTreeView.setCellFactory(param -> new TreeCell<Branch>() {
            @Override
            protected void updateItem(Branch item, boolean empty) {
                // 这一行代码不能删除！
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(StrUtil.EMPTY);
                } else {
                    setText(String.format("%s %s %s",
                            StrUtil.blankToDefault(item.getZhName(), StrUtil.EMPTY),
                            StrUtil.isBlank(item.getEnName()) ? StrUtil.EMPTY : String.format("(%s)", item.getEnName()),
                            StrUtil.isBlank(item.getShortName()) ? StrUtil.EMPTY : String.format("[%s]", item.getShortName())
                    ));
                }
            }
        });
        branchTreeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // 更新酒店选项
            System.out.println(newValue.getValue().getZhName());
            showHotelList.clear();
            showHotelList.addAll(CacheData.getHotelMap().getListByIdSet(newValue.getValue().getAllHotels()));
            showHotelList.sort(Comparator.comparing(o -> Integer.valueOf(o.getHotelId())));
            hotelCombo.setItems(new ReadOnlyUnbackedObservableList<Hotel>() {
                @Override
                public Hotel get(int i) {
                    return showHotelList.get(i);
                }

                @Override
                public int size() {
                    return showHotelList.size();
                }
            });
            if (CollUtil.isNotEmpty(showHotelList)) {
                hotelCombo.getSelectionModel().select(RandomUtil.randomInt(showHotelList.size()));
            }
        });
    }

    private void buildBranchTreeView(TreeItem<Branch> root, Branch rootBranch) {
        root.setValue(rootBranch);

        ObservableList<TreeItem<Branch>> children = root.getChildren();
        List<TreeItem<Branch>> list = new ArrayList<>(rootBranch.getChildren().size());

        for (String childId : rootBranch.getChildren()) {
            TreeItem<Branch> item = new TreeItem<>();
            buildBranchTreeView(item, CacheData.getBranchMap().get(childId));
            list.add(item);
        }

        list.sort(Comparator.comparing(o -> Integer.valueOf(o.getValue().getId())));
        children.addAll(list);
    }

    /**
     * 激活
     */
    private void activation() {
        // 是否已经激活
        if (ActivationUtil.simpleActivation(null)) {
            return;
        }

        TextInputDialog dialog = new TextInputDialog(StrUtil.EMPTY);
        dialog.setTitle("激活程序");
        dialog.setHeaderText("激活程序");
        dialog.setContentText("请输入激活码:");

        Optional<String> result = dialog.showAndWait();
        if (!result.isPresent() || !ActivationUtil.simpleActivation(result.get())) {
            activation();
        }
    }

    private void clearPanelValues() {
        firstNameField.setText(StrUtil.EMPTY);
        lastNameField.setText(StrUtil.EMPTY);
        birthplaceField.setText(StrUtil.EMPTY);
//        birthdayPicker.setValue(null);
        nationalityField.setText(StrUtil.EMPTY);
        idnField.setText(StrUtil.EMPTY);
        phoneField.setText(StrUtil.EMPTY);
        emailField.setText(StrUtil.EMPTY);
        addressField.setText(StrUtil.EMPTY);
        companyName.setText(StrUtil.EMPTY);
        companyAddressField.setText(StrUtil.EMPTY);
        companyPhoneField.setText(StrUtil.EMPTY);
//        startDatePicker.setValue(null);
//        endDatePicker.setValue(null);
        passportField.setText(StrUtil.EMPTY);
    }

    /**
     * 初始化日期选择器
     */
    private void initDatePicker() {
        startDatePicker.setValue(LocalDate.now());

        // 默认相隔4天（一共五天）
        endDatePicker.setValue(LocalDate.now().plusDays(4));

        birthdayPicker.setValue(LocalDate.now().minusYears(20));

        // 行程单2日期，默认当日
        application2DatePicker.setValue(LocalDate.now());
    }

    private void popMsg(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(msg);
        alert.getDialogPane().setPrefSize(500, 500);
        alert.showAndWait();
    }

    private void popMsg(String title, String headerText, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(msg);
        alert.getDialogPane().setPrefSize(500, 500);
        alert.showAndWait();
    }

    /**
     * 初始化按钮点击事件
     */
    private void initButtonClick() {
        exportFileButton.setOnAction(event -> {
            try {
                if (!collectInfos()) {
                    return;
                }

                exportFiles();

                popMsg("导出成功！", "成功将客户[" + cacheData.getUserInfo().getChineseLastName() + cacheData.getUserInfo().getChineseFirstName() + "]的申请表、行程单、机票信息导出。");
            } catch (Exception e) {
                popMsg("操作失败！", StrUtil.blankToDefault(e.getMessage(), "操作失败"), Arrays.toString(e.getStackTrace()));
                throw e;
            }
        });

//        randomFlightButton.setOnAction(event -> {
//            flightCombo.getSelectionModel().select(RandomUtil.randomInt(flightCombo.getItems().size()));
//            backFlightCombo.getSelectionModel().select(RandomUtil.randomInt(backFlightCombo.getItems().size()));
//        });
    }

    /**
     * 导出文件
     */
    private void exportFiles() {
        String now = LocalDateTimeUtil.format(LocalDateTimeUtil.now(), PURE_DATE_PATTERN);
        UserInformation userInfo = cacheData.getUserInfo();
        //System.out.println("-------------------------------" + userInfo);
        String name = userInfo.getChineseLastName() + userInfo.getChineseFirstName();
        String pinyin = userInfo.getEnglishLastName() + StrUtil.SPACE + userInfo.getEnglishFirstName();
        String fileName = name + StrUtil.UNDERLINE + now;

        String applicationPath = applicationPathField.getText();
        String travelPath = travelPathField.getText();
        String ticketPath = ticketPathField.getText();
        String pdfApplicationPath = applicationPathField.getText();

        // 导出申请表
        RequisitionDoc.handle(userInfo, applicationPath, fileName);

        // 导出行程单
        ItineraryDoc.handle(cacheData.getItineraryInfoList(), travelPath,
                now.substring(0, 4), now.substring(4, 6), now.substring(6, 8),
                pinyin, fileName,
                cacheData.getHotel().getEnName(), cacheData.getHotel().getAddress(), cacheData.getHotel().getPhone());

//        // 导出机票
//        TicketDoc.handle(cacheData.getTicketInfo(), cacheData.getFlightInfo(), cacheData.getBackFlightInfo(), fileName, ticketPath,
//                1079, 1542);

        // 导出申请表2
        PdfFormHandler.handle(cacheData.getJapanVisaApplication(), pdfApplicationPath, fileName);
    }

    /**
     * 收集用户输入转换为class
     */
    private boolean collectInfos() {
        if (!initCacheData()) {
            return false;
        };

        fillUserInfo();

//        fillFlightInfo();

        fillItineraryInfo();

        fillJapanVisaApplicationInfo();

        return true;
    }

    /**
     * 行程单2
     */
    private void fillJapanVisaApplicationInfo() {
        JapanVisaApplication info = new JapanVisaApplication();
        cacheData.setJapanVisaApplication(info);

        info.setEmail(emailField.getText());

        info.setDateOfBirth(birthdayPicker.getValue() == null ?
                StrUtil.EMPTY :
                birthdayPicker.getValue().format(ddMMyyyy));
        info.setSurname(PinyinUtil.getPinyin(lastNameField.getText()).toUpperCase(Locale.ROOT) + "  " + lastNameField.getText());
        info.setGivenAndMiddleNames(PinyinUtil.getPinyin(firstNameField.getText()).toUpperCase(Locale.ROOT) + "  " + firstNameField.getText());
        info.setOtherNames(StrUtil.blankToDefault(otherNameField.getText(), "NONE"));
        info.setPlaceOfBirthCountry(birthplaceField.getText());
        info.setSex(sexCombo.getSelectionModel().getSelectedIndex() + 1);
        info.setMaritalStatus(marriageCombo.getSelectionModel().getSelectedIndex() + 1);
        info.setNationalityOrCitizenship(nationalityField.getText());
        info.setFormerNationalitiesOrCitizenships(nationalityField.getText());
        info.setGovernmentIdNumber(idnField.getText());

        // 护照信息
        info.setPassportType(passportTypeComb.getSelectionModel().getSelectedIndex() + 1);
        info.setPassportNumber(passportField.getText());
        info.setPlaceOfIssue(placeOfIssueField.getText());
        info.setDateOfIssue(getDdMmYyyyTimeStr(passportStartDtPicker.getValue()));
        info.setIssuingAuthority(issuingAuthorityField.getText());
        info.setDateOfExpiry(getDdMmYyyyTimeStr(passportEndDtPicker.getValue()));

        info.setCertificateOfEligibilityNumber(certificateField.getText());
        info.setPurposeOfVisitOrResidenceStatus(purposeField.getText());

        // 计划在日本停留时间
        LocalDate startDt = startDatePicker.getValue();
        LocalDate endDt = endDatePicker.getValue();
        String between = null != startDt && null != endDt ?
                String.valueOf(LocalDateTimeUtil.betweenPeriod(startDt, endDt).getDays())
                : StrUtil.EMPTY;
        int i = Integer.parseInt(between) + 1;
        info.setIntendedLengthOfStay(i + " days");
        // 抵达日本时间（出发日期）
        info.setDateOfArrivalInJapan(getDdMmYyyyTimeStr(startDt));
        // 入境日本口岸
        info.setPortOfEntryIntoJapan(portOfEntryIntoJapanField.getText());
        // 入境航空公司名称 (航班号码)
        info.setNameOfShipOrAirline(cacheData.getFlight().getFlightCode());

        // 入住酒店名称
        Hotel hotel = cacheData.getHotel();
        info.setNamesAndAddressesOfIntendedStays(hotel.getEnName());
        // 酒店联系方式
        info.setTelOfIntendedStays(hotel.getPhone());
        // 酒店地址
        info.setAddressOfIntendedStays(hotel.getAddress());

        // 之前在日本居住的时间
        String previousStaysInJapan = getDdMmYyyyTimeStr(lastStayStartDtPicker.getValue()) + "~" + getDdMmYyyyTimeStr(lastStayEndDtPicker.getValue());
        if ("~".equals(previousStaysInJapan)) {
            previousStaysInJapan = StrUtil.EMPTY;
        }
        info.setPreviousStaysInJapan(StrUtil.blankToDefault(previousStaysInJapan, "NONE"));

        // 当前居住地址
        info.setCurrentResidentialAddress(addressField.getText());
        info.setTelephone(phoneField.getText());
        info.setMobileNumber(phoneField.getText());
        info.setEmail(emailField.getText());

        // 当前职位
        int selectedIndex = occupationCombo.getSelectionModel().getSelectedIndex();
        info.setProfessionOrOccupation(OccupationEnum.values()[selectedIndex].getCode());

        // 工作地址
        info.setEmployerAddress(companyAddressField.getText());
        info.setEmployerTelephone(companyPhoneField.getText());
        info.setEmployerName(companyName.getText());

        // 伴侣或父母的职业(仅已婚生效)
        info.setPartnersProfessionOrOccupation(
                MarriageEnum.MARRIED.getDesc().equals(marriageCombo.getValue()) && null != partnerOccupationCombo.getValue() ?
                        partnerOccupationCombo.getValue().getCode() : StrUtil.EMPTY
        );

        // 行程单2日期
        info.setDateOfApplication(getDdMmYyyyTimeStr(application2DatePicker.getValue()));

    }

    private static final DateTimeFormatter ddMMyyyy = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private String getDdMmYyyyTimeStr(LocalDate localDate) {
        return null == localDate ? StrUtil.EMPTY : localDate.format(ddMMyyyy);
    }

    /**
     * 填充化行程单信息
     */
    private void fillItineraryInfo() {
        // 入境港口不能为空
        Assert.notBlank(portOfEntryIntoJapanField.getText(), "入境港口不能为空");

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
                // 第一条数据：固定 ICN -> 指定入境港口
                activityPlan.add(AirportEnum.ICN.getFromToStr(portOfEntryIntoJapanField.getText()));
                itinerary.setContactNumber(phoneField.getText());
            } else if (dt.equals(cacheData.getEndDt())) {
                activityPlan.add(portOfEntryIntoJapanField.getText() + " -> " + AirportEnum.ICN.getCode());
            }
            activityPlan.addAll(touristSpots.stream().map(t -> String.format("%s (%s)", t.getEnglishName(), t.getJapaneseName())).collect(Collectors.toList()));
            itinerary.setActivityPlan(activityPlan);
        }
    }

    /**
     * 填充机票信息
     */
    private void fillFlightInfo() {
        Flight flight = cacheData.getFlight();
        cacheData.setFlightInfo(FligihtInfo.createFlightInfo(flight, cacheData.getStartDt()));
        Flight backFlight = cacheData.getBackFlight();
        cacheData.setBackFlightInfo(FligihtInfo.createFlightInfo(backFlight, cacheData.getEndDt()));
        Ticket ticketInfo = new Ticket();
        cacheData.setTicketInfo(ticketInfo);
        ticketInfo.setAirlinePnr(RandomUtil.randomStringUpper(6));
        ticketInfo.setBookingPnr(RandomUtil.randomStringUpper(6));
        ticketInfo.seteTicketNumber(AirlineCompanyEnum.getRandomTicketNum(flight.getTicketPreFix()));
        ticketInfo.setPassengerName(cacheData.getUserInfo().getEnglishLastName() + StrUtil.SLASH + cacheData.getUserInfo().getEnglishFirstName(), SexEnum.values()[sexCombo.getSelectionModel().getSelectedIndex()]);
        ticketInfo.setIdNumber(passportField.getText());
        ticketInfo.setConjunctionTicketNumber("");
        ticketInfo.setDateOfIssue(LocalDateTimeUtil.now().format(DatePattern.NORM_DATE_FORMATTER));
        ticketInfo.setIataCode(RandomUtil.randomNumbers(8));
        ticketInfo.setIssuingAirline(flight.getCompanyName());
    }

    /**
     * 初始化缓存
     */
    private boolean initCacheData() {
        cacheData = new CacheData();

        // 选择的酒店
        int selectedIndex = hotelCombo.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0) {
            popMsg("警告", "请选择酒店后再开始！");
            return false;
        }
        cacheData.setHotel(hotelCombo.getItems().get(selectedIndex));

        // 选择的航班
        Flight startFlight = new Flight();
        String startFlightFieldText = startFlightField.getText();
        if (StrUtil.isNotBlank(startFlightFieldText)) {
            try {
                getFlightInfoFromText(startFlight, startFlightFieldText);
            } catch (Exception e) {
                popMsg("警告", "输入的开始航班信息有误，请按照格式[开始地点,开始地点航站号,开始时间,终点,终点航站号,抵达时间,航班号码,航空公司,机票号码前缀]输入，或者清空输入框！" +
                        "\n示例：NRT,T1,10:00,KIX,T1,12:00,KE1111,KOREAN AIR,180");
                return false;
            }
        } else {
//            startFlight.setFlightEnum(FLIGHT_LIST.get(flightCombo.getSelectionModel().getSelectedIndex()));
        }
        cacheData.setFlight(startFlight);

        Flight endFlight = new Flight();
        String endFlightFieldText = startFlightField.getText();
        if (StrUtil.isNotBlank(endFlightFieldText)) {
            try {
                getFlightInfoFromText(endFlight, endFlightFieldText);
            } catch (Exception e) {
                popMsg("警告", "输入的结束航班信息有误，请按照格式[开始地点,开始地点航站号,开始时间,终点,终点航站号,抵达时间,航班号码,航空公司,机票号码前缀]输入，或者清空输入框！" +
                        "\n示例：NRT,T1,10:00,KIX,T1,12:00,KE1111,KOREAN AIR,180");
                return false;
            }
        } else {
//            endFlight.setFlightEnum(BACK_FLIGHT_LIST.get(backFlightCombo.getSelectionModel().getSelectedIndex()));
        }
        cacheData.setBackFlight(endFlight);


        String startDt = LocalDateTimeUtil.format(startDatePicker.getValue(), PURE_DATE_PATTERN);
        cacheData.setStartDt(startDt);
        String endDt = LocalDateTimeUtil.format(endDatePicker.getValue(), PURE_DATE_PATTERN);
        Assert.notBlank(startDt, "开始日期不能为空");
        Assert.notBlank(endDt, "结束日期不能为空");
        cacheData.setEndDt(endDt);


        TreeItem<Branch> selectedBranch = branchTreeView.getSelectionModel().getSelectedItem();
        if (null == selectedBranch) {
            popMsg("警告", "请选择城市后再开始！");
            return false;
        }
        // 生成随机行程
        cacheData.setTouristMap(
                MyUtil.getRandomTouristMap(CacheData.getTouristSpotMap().getListByIdSet(selectedBranch.getValue().getAllSports()),
                        cacheData.getStartDt(), cacheData.getEndDt()));


        return true;
    }

    private void getFlightInfoFromText(Flight flight, String startFlightFieldText) {
//        List<String> list = Arrays.stream(startFlightFieldText.split(",")).map(String::trim).collect(Collectors.toList());
//        flight.setStartCode(list.get(0));
//        flight.setStartTerminalNo(list.get(1));
//        flight.setStartTime(list.get(2));
//        flight.setEndCode(list.get(3));
//        flight.setEndTerminalNo(list.get(4));
//        flight.setEndTime(list.get(5));
//        flight.setFlightCode(list.get(6));
//        flight.setCompanyName(list.get(7));
//        flight.setTicketPreFix(list.get(8));
        flight.setFlightCode(startFlightFieldText);
    }

    /**
     * 填充用户信息
     */
    private void fillUserInfo() {
        cacheData.setUserInfo(new UserInformation());
        UserInformation userInfo = cacheData.getUserInfo();
        userInfo.setPassportNum(passportField.getText());
        String lastName = lastNameField.getText();
        userInfo.setChineseLastName(lastName);
        String firstName = firstNameField.getText();
        userInfo.setChineseFirstName(firstName);

        Assert.notBlank(lastName, "姓氏不能为空");
        Assert.notBlank(firstName, "名称不能为空");

        userInfo.setGender(MyUtil.setCheck(userInfo.getGender(), sexCombo.getSelectionModel().getSelectedIndex()));
        userInfo.setDateOfBirth(DateUtil.format(LocalDateTimeUtil.of(birthdayPicker.getValue()), PURE_DATE_PATTERN));
        userInfo.setPlaceOfBirth(birthplaceField.getText());
        userInfo.setMaritalStatus(MyUtil.setCheck(userInfo.getMaritalStatus(), marriageCombo.getSelectionModel().getSelectedIndex()));
        userInfo.setNationality(nationalityField.getText());
        userInfo.setForeignerRegistrationNumber(idnField.getText());
        userInfo.setContactNumber(phoneField.getText());
        userInfo.setEmail(emailField.getText());
        userInfo.setAddress(addressField.getText());
        userInfo.setCurrentOccupationAndPosition(MyUtil.setCheck(userInfo.getCurrentOccupationAndPosition(), occupationCombo.getSelectionModel().getSelectedIndex()));
        userInfo.setCompanyOrSchoolName(companyName.getText());
        userInfo.setCompanyOrSchoolPhoneNumber(companyPhoneField.getText());
        userInfo.setCompanyOrSchoolAddress(companyAddressField.getText());
        userInfo.setEnglishLastName(PinyinUtil.getPinyin(lastName, StrUtil.EMPTY).toUpperCase(Locale.ROOT));
        userInfo.setEnglishFirstName(PinyinUtil.getPinyin(firstName, StrUtil.EMPTY).toUpperCase(Locale.ROOT));
        LocalDate startDt = startDatePicker.getValue();
        LocalDate endDt = endDatePicker.getValue();
        userInfo.setPlannedDurationOfStayInJapan(String.format(UserInformation.DURATION_FORMAT,
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
            userInfo.setLastStayInJapanDuration(String.format(UserInformation.DURATION_FORMAT,
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
            userInfo.setTotalStayDurationInJapanLastYear(
                    String.format(UserInformation.STAY_DURATION_JAPAN_LAST_YEAR_FORMAT, last1yStayDaysFieldText)
            );
        }

        // 航班号
        Flight flight = cacheData.getFlight();
        userInfo.setEntryPortOrFlightNumber(
//                flight.getEndTerminal().getAirport()
//                + StrPool.COMMA +
                flight.getFlightCode());

        // 入境港口
        String portName = portOfEntryIntoJapanField.getText();
        if (StrUtil.isNotBlank(portName)) {
            userInfo.setPlannedCityOfEntryInJapan(portName);
        }
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

        partnerOccupationCombo.setItems(new ReadOnlyUnbackedObservableList<OccupationEnum>() {
            @Override
            public OccupationEnum get(int i) {
                return OccupationEnum.values()[i];
            }

            @Override
            public int size() {
                return OccupationEnum.values().length;
            }
        });
        partnerOccupationCombo.getSelectionModel().selectFirst();


//        // 出发航班下拉框
//        flightCombo.setItems(new ReadOnlyUnbackedObservableList<String>() {
//            @Override
//            public String get(int i) {
//                FlightEnum flightEnum = FLIGHT_LIST.get(i);
//                return getFlightComboStr(flightEnum);
//            }
//
//            @Override
//            public int size() {
//                return FLIGHT_LIST.size();
//            }
//        });
//        flightCombo.getSelectionModel().select(RandomUtil.randomInt(FLIGHT_LIST.size()));


//        backFlightCombo.setItems(new ReadOnlyUnbackedObservableList<String>() {
//
//
//            @Override
//            public String get(int i) {
//                FlightEnum flightEnum = BACK_FLIGHT_LIST.get(i);
//                return getFlightComboStr(flightEnum);
//            }
//
//            @Override
//            public int size() {
//                return BACK_FLIGHT_LIST.size();
//            }
//        });
//        backFlightCombo.getSelectionModel().select(RandomUtil.randomInt(BACK_FLIGHT_LIST.size()));

        passportTypeComb.setItems(new ReadOnlyUnbackedObservableList<String>() {


            @Override
            public String get(int i) {
                return PassportTypeEnum.values()[i].getDesc();
            }

            @Override
            public int size() {
                return PassportTypeEnum.values().length;
            }
        });
        passportTypeComb.getSelectionModel().select(PassportTypeEnum.ORDINARY.ordinal());
    }

    private String getFlightComboStr(FlightEnum flightEnum) {
        return flightEnum.getCode() + StrPool.AT +
                flightEnum.getStartTerminal().getTerminalName() + flightEnum.getStartTerminal().getTerminalNo()
                + StrPool.DASHED +
                flightEnum.getEndTerminal().getTerminalName() + flightEnum.getEndTerminal().getTerminalNo()
                + StrPool.BRACKET_START + flightEnum.getStartTime() + StrPool.COMMA
                + flightEnum.getEndTime() + StrPool.BRACKET_END
                + StrPool.AT + flightEnum.getCompany().getChineseName();
//        return flightEnum.getStartTerminal().getAirport().getCode() +
//                "," +
//                flightEnum.getStartTime() +
//                "," +
//                flightEnum.getEndTerminal().getAirport().getCode() +
//                "," +
//                flightEnum.getEndTime() +
//                "," +
//                flightEnum.getCode() +
//                "," +
//                flightEnum.getCompany().getEnglishName();
    }

    List<FlightEnum> FLIGHT_LIST = Arrays.stream(FlightEnum.values()).collect(Collectors.toList());

    List<FlightEnum> BACK_FLIGHT_LIST = Arrays.stream(FlightEnum.values()).collect(Collectors.toList());
}
