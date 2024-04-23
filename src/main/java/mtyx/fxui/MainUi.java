package mtyx.fxui;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import mtyx.constant.UiConstant;
import mtyx.utils.ExecutorUtils;
import mtyx.utils.HttpUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainUi extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        URL resource = getClass().getClassLoader().getResource(UiConstant.FxmlPath.MAIN.getPath());
//        resource = new URL("jar", null, resource.getFile());
        Parent root = FXMLLoader.load(resource);
        primaryStage.setTitle(UiConstant.Title.MAIN.getTitle());

        // 创建窗口图标
        List<Image> icons = new ArrayList<>();
        icons.add(new Image(UiConstant.ICON_PATH));
        // 设置窗口图标
        primaryStage.getIcons().addAll(icons);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest((event) -> {
            ExecutorUtils.close();
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
