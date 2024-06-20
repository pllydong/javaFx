package sample.ctrl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.sun.javafx.scene.control.ReadOnlyUnbackedObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import sample.enums.MarriageEnum;
import sample.enums.OccupationEnum;
import sample.enums.SexEnum;
import sample.pojo.FlightInfo;
import sample.pojo.Itinerary;
import sample.pojo.UserInformation;

import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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
    private UserInformation userInfo = new UserInformation();
    /**
     * 申请表信息
     */
    private Itinerary applicationInfo = new Itinerary();
    /**
     * 机票信息
     */
    private FlightInfo flightInfo = new FlightInfo();

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
    }

    /**
     * 初始化按钮点击事件
     */
    private void initButtonClick() {
        exportFileButton.setOnAction(event -> {
            collectInfos();
        });
    }

    /**
     * 收集用户输入转换为class
     */
    private void collectInfos() {
        fillUserInfo();
    }

    /**
     * 填充用户信息
     */
    private void fillUserInfo() {
        userInfo.setChineseLastName(lastNameField.getText());
        userInfo.setChineseFirstName(firstNameField.getText());
        userInfo.setGender(sexCombo.cursorProperty().getName());
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
