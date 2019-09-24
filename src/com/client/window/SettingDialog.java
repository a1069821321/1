package com.client.window;

import com.client.Main_window;
import com.client.modules.DragUtil;
import com.client.modules.Properate;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SettingDialog extends Application {
    public static Stage cstage;
    public void start(Stage connectsetstage) {
        cstage = connectsetstage;
        //connectsetstage.initModality(Modality.APPLICATION_MODAL);
        connectsetstage.initStyle(StageStyle.TRANSPARENT);

        VBox root = new VBox();
        root.setId("root");
        // 引入样式
        root.getStylesheets().add(Main_window.class.getResource("resources/css/title.css").toString());
        root.getStylesheets().add(Main_window.class.getResource("resources/css/box.css").toString());
        root.getStylesheets().add(Main_window.class.getResource("resources/css/many.css").toString());

        //顶部
        VBox top = new VBox();
        top.setId("top");
        top.setPrefSize(500,30);
        // 标题栏
        AnchorPane title = new AnchorPane();
        Button close = new Button();
        close.setPrefWidth(20);
        close.setPrefHeight(20);
        close.setId("winClose");//winClose css样式Id
        close.setOnMousePressed(event -> {
            connectsetstage.close();
        });

        title.getChildren().addAll(close);
        AnchorPane.setRightAnchor(close, 5.0);AnchorPane.setTopAnchor(close, 5.0);
        top.getChildren().add(title);

        // 内容
        VBox content = new VBox();
        content.getStyleClass().add("connect_set_vbox");




        //content.getChildren().addAll(s,e);

        root.getChildren().addAll(top, content);
        Scene scene = new Scene(root);
        connectsetstage.setScene(scene);
        DragUtil.addDragListener(connectsetstage, top);
        // 显示
        //initProperty(server_ip_t,connect_port_t);
        connectsetstage.show();
    }
    private void initProperty(TextField server_t,TextField port_t){
        try {
            String server = Properate.read("connectset_server", "a.properties");
            String port = Properate.read("connectset_port", "a.properties");
            server_t.setText(server);
            port_t.setText(port);
        }catch (Exception e3){
            Alert _alert = new Alert(Alert.AlertType.ERROR);
            _alert.setTitle("");
            _alert.setHeaderText("初始化失败");
            _alert.setContentText("无法读取配置文件，请确认配置文件是否存在");
            _alert.initOwner(cstage);
            _alert.show();
        }
    }

}
