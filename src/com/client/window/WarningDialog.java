package com.client.window;

import com.client.Main_window;
import com.client.modules.DragUtil;
import com.client.modules.Properate;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class WarningDialog extends Application {
    public static Stage cstage;
    public void start(Stage connectsetstage) {
        cstage = connectsetstage;
        connectsetstage.initStyle(StageStyle.TRANSPARENT);
        VBox root = new VBox();
        root.setId("root");
        // 引入样式
        root.getStylesheets().add(Main_window.class.getResource("resources/css/title.css").toString());
        root.getStylesheets().add(Main_window.class.getResource("resources/css/box.css").toString());
        root.getStylesheets().add(Main_window.class.getResource("resources/css/many.css").toString());
        root.getStylesheets().add(Main_window.class.getResource("resources/css/button.css").toString());

        //顶部
        VBox top = new VBox();
        top.setId("top");
        top.setPrefSize(400,30);
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
        VBox content_in = new VBox();
        //content.setAlignment(Pos.CENTER);
        content.getStyleClass().add("connect_set_vbox");
        Label GreatTitle = new Label("监测到异常状况！");
        GreatTitle.setId("Warning_label_title");
        Label Name = new Label("机号：");Name.setId("Warning_label_main");
        Label Time = new Label("时间：");Time.setId("Warning_label_main");
        Label Parameter = new Label("异动参数：");Parameter.setId("Warning_label_main");
        HBox buttons = new HBox();
        Button ensure = new Button("确定");
        Button go = new Button("立即查看");
        buttons.getChildren().addAll(ensure,go);buttons.setSpacing(150);
        content_in.getChildren().addAll(GreatTitle,Name,Time,Parameter,buttons);
        content.getChildren().addAll(GreatTitle,Name,Time,Parameter,buttons);
        root.getChildren().addAll(top,content);
        Scene scene = new Scene(root);
        connectsetstage.setScene(scene);
        DragUtil.addDragListener(connectsetstage, top);
        // 显示
        //initProperty(server_ip_t,connect_port_t);
        connectsetstage.show();
    }


}