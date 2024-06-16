package tourism.constant;

/**
 * @author guokun
 * @date 2024/4/20 15:47
 */
public class UiConstant {
    public enum Title {
        /**
         * 主页标题
         */
        MAIN("Jingwolf")
        ;
        private final String title;

        Title(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum FxmlPath {
        /**
         * 主页
         */
        MAIN("mtyx/fxui/main.fxml"),




        DATE_TIME_PICKER("/mtyx/component/DateTimePicker.fxml"),
        DATE_TIME_PICKER_POPUP("/mtyx/component/DateTimePickerPopup.fxml"),
        HOURS_PICKER("/mtyx/component/HoursPicker.fxml"),
        MINUTE_SECOND_PICKER("/mtyx/component/MinuteSecondPicker.fxml"),
        ;
        private final String path;

        FxmlPath(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }
    }

    public static final String STR_START = "开始";
    public static final String STR_END = "结束";

    public static final String ICON_PATH = "/mtyx/component/icons/cat.png";
}
