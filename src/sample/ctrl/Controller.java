package sample.ctrl;

import com.sun.javafx.scene.control.ReadOnlyUnbackedObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import sample.enums.SexEnum;
import sample.pojo.FlightInfo;
import sample.pojo.Itinerary;
import sample.pojo.UserInformation;

import java.net.URL;
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
    public DatePicker birthdayPicker;
    public TextField birthplaceField;
    public TextField nationalityField;
    public TextField idnField;
    public TextField phoneField;
    public ComboBox<String> marriageCombo;
    public TextField emailField;
    public TextField addressField;
    public ComboBox<String> occupationCombo;
    public TextField companyName;
    public TextField companyPhone;
    public DatePicker startDatePicker;
    public DatePicker endDatePicker;
    public TextField ticketPathField;
    public TextField applicationPathField;
    public TextField travelPathField;
    public Button exportFileButton;
    public TextField fileNameField;
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

        initButtonClick();
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


    }
}
