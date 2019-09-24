package com.client.window;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
/////
import com.client.Main_window;
/////
import com.client.modules.DragUtil;
import com.client.modules.MainTray;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import javax.imageio.ImageIO;


public class QuestionExit extends Application{
    public static Thread t;
    @Override
    public void start(Stage qstage) {
        qstage.initModality(Modality.APPLICATION_MODAL); //单句：设置为模态窗口，该窗口不关闭，主窗口不可用。
        qstage.initStyle(StageStyle.TRANSPARENT);
        VBox root = new VBox();
        root.setId("root");
        // 引入样式
        root.getStylesheets().add(Main_window.class.getResource("resources/css/title.css").toString());

        //顶部
        VBox top = new VBox();
        top.setId("top");
        top.setPrefSize(180,30);
        // 标题栏
        AnchorPane title1 = new AnchorPane();
        Label close1 = new Label();
        close1.setPrefWidth(20);
        close1.setPrefHeight(20);
        close1.setId("winClose");
        close1.setOnMousePressed(event -> {qstage.close();});

        title1.getChildren().add(close1);
        AnchorPane.setRightAnchor(close1, 5.0);
        AnchorPane.setTopAnchor(close1, 5.0);
        top.getChildren().add(title1);

        // 内容
        VBox content = new VBox();
        content.setPrefWidth(300);
        content.setMinHeight(150);
        AnchorPane content_in = new AnchorPane();
        Label test1 = new Label("确定要退出吗？");
        test1.setId("QuesExit");
        Button button1 = new Button("退出");button1.setOnMousePressed(event ->{System.exit(0);});
        Button button2 = new Button("最小化到托盘");
        button2.setOnAction(event ->{
           // Main_window.main_stage.hide();
            MainTray mt= new MainTray();
            mt.enableTray(qstage);
            Main_window.main_stage.hide();

            });


        CheckBox checkBox1 = new CheckBox("记住我的选择");
        HBox labels1 = new HBox();
        labels1.getChildren().addAll(test1);
        labels1.setPadding(new Insets(55,0,15,90));
        HBox checkboxes1 = new HBox();
        checkboxes1.getChildren().addAll(checkBox1);
        checkboxes1.setPadding(new Insets(5,0,15,50));
        HBox buttons1 = new HBox();
        buttons1.setPadding(new Insets(5,30,12,38));
        buttons1.setSpacing(60);
        buttons1.getChildren().addAll(button1,button2);

        /*
        content_in.getChildren().addAll(test1,checkBox1,button1,button2);
        AnchorPane.setLeftAnchor(test1,130.0);AnchorPane.setTopAnchor(test1,50.0);
        AnchorPane.setLeftAnchor(checkBox1,130.0);AnchorPane.setTopAnchor(checkBox1,80.0);
        AnchorPane.setLeftAnchor(button1,50.0);AnchorPane.setTopAnchor(button1,130.0);
        */
        content.getChildren().addAll(labels1,checkboxes1,buttons1);

        // 组装
        root.getChildren().addAll(top, content);
        Scene scene = new Scene(root);
        qstage.setScene(scene);
        DragUtil.addDragListener(qstage, top);
        // 显示
        qstage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent arg0) {
                qstage.hide();
            }
        });
        qstage.show();


    }







}
