package sample.ctrl;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public TextField lastNameField;
    public TextField firstNameField;
    public TextField ageField;
    public ComboBox sexCombo;
    public DatePicker birthdayPicker;
    public TextField birthplaceField;
    public TextField nationalityField;
    public TextField idnField;
    public TextField phoneField;
    public ComboBox marriageCombo;
    public TextField emailField;
    public TextField addressField;
    public ComboBox occupationCombo;
    public TextField companyName;
    public TextField companyPhone;
    public DatePicker startDatePicker;
    public DatePicker endDatePicker;
    public TextField ticketPathField;
    public TextField applicationPathField;
    public TextField travelPathField;
    public Button exportFileButton;
    public TextField fileNameField;

    /**
     * 界面资源加载完后调用该方法进行初始化
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
