package com.client.test;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ListView.EditEvent;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.awt.*;

public class Two_thread extends Application{
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();
        VBox vbox = new VBox();

        ObservableList<Data> list = FXCollections.observableArrayList();


        list.add(new Data("1", "18", "man","green"));
        list.add(new Data("2", "18", "woman","green"));
        list.add(new Data("3", "18", "man","green"));
        list.add(new Data("4", "18", "woman","green"));
        list.add(new Data("5", "18", "man","green"));
        list.add(new Data("6", "18", "man","green"));
        list.add(new Data("7", "18", "man","green"));
        list.add(new Data("8", "18", "man","green"));
        list.add(new Data("9", "18", "man","green"));
        list.add(new Data("10", "18", "man","green"));
        list.add(new Data("11", "18", "man","green"));
        list.add(new Data("12", "18", "man","green"));
        list.add(new Data("13", "18", "man","green"));
        list.add(new Data("14", "18", "man","green"));
        list.add(new Data("15", "18", "man","green"));
        list.add(new Data("16", "18", "man","green"));
        ListView<Data> listview = new ListView<Data>();
        //占位符 当listview没有数据时显示占位符
        listview.setPlaceholder(new Label("没有数据"));
        //添加一个可观测的列表显示
        listview.setItems(list);
        listview.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2)
                {
                    //Student s = list.data().get(index);
                    int index = listview.getSelectionModel().getSelectedIndex();
                    Data en =list.get(index);
                    String ens =en.getName();
                    System.out.println("你双击了"+ens);
                    //list.set(index,ens+" ");
                    //listview.setItems(null);
                    //listview.setItems(data);
                    en.setName("1被点击");
                    System.out.println(en.toString());
                    listview.setItems(list);

                }
                else if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1)
                {
                    int index = listview.getSelectionModel().getSelectedIndex();
                    Data en =list.get(index);
                    String ens =en.getName();
                    System.out.println("你双击了"+ens);
                    //list.set(index,ens+" ");
                    //listview.setItems(null);
                    //listview.setItems(data);
                    en.setName(ens+"被点击");
                    System.out.println(en.toString());
                    list.set(index,en);
                    listview.setItems(list);


                }

            }});

        listview.setEditable(true);

        listview.setCellFactory(new Callback<ListView<Data>, ListCell<Data>>() {
            Data data = null;
            ListCell<Data> cell;
            @Override
            public ListCell<Data> call(ListView<Data> param) {
                // TODO Auto-generated method stub

                param.setOnEditStart(new EventHandler<ListView.EditEvent<Data>>() {

                    @Override
                    public void handle(EditEvent<Data> event) {
                        // TODO Auto-generated method stub
                        data = param.getItems().get(event.getIndex());
                    }
                });
                ListCell<Data> listcell  = new ListCell<Data>(){

                    @Override
                    //提交编辑内容
                    public void commitEdit(Data newValue) {
                        // TODO Auto-generated method stub
                        super.commitEdit(newValue);
                    }

                    @Override
                    //取消编辑
                    public void cancelEdit() {
                        // TODO Auto-generated method stub
                        super.cancelEdit();
                        HBox hbox = new HBox();
                        Button name = new Button(data.getName());
                        Rectangle rect = new Rectangle(20,20);
                        rect.setFill(Color.web(data.getColor()));
                        hbox.getChildren().addAll(rect,name);

                        this.setGraphic(hbox);
                    }

                    @Override
                    public void startEdit() {
                        // TODO Auto-generated method stub
                        super.startEdit();
                        cell = this;
                        HBox hbox = new HBox();
                        hbox.setAlignment(Pos.CENTER_LEFT);
                        Button name = new Button(data.getName());
                        Rectangle rect = new Rectangle(20,20);
                        rect.setFill(Color.web(data.getColor()));
                        TextField age = new TextField(data.getAge());
                        age.setPrefWidth(40);
                        TextField sex = new TextField(data.getSex());
                        sex.setPrefWidth(40);
                        hbox.getChildren().addAll(rect,name,age,sex);
                        this.setGraphic(hbox);
                        //对age的修改
                        hbox.setOnMousePressed(event -> {
                            cell.commitEdit(new Data(name.getText(),age.getText(),sex.getText(),"red"));
                        });
                        age.setOnKeyPressed(new EventHandler<KeyEvent>() {
                            @Override
                            public void handle(KeyEvent event) {
                                // 如果按下ENTER键则提交修改
                                if(event.getCode().getName().equals(KeyCode.ENTER.getName())){
                                    //如果文本框为空则提交原始数据
                                    if(age.getText().equals("")){
                                        cell.commitEdit(data);
                                    }else{
                                        cell.commitEdit(new Data(name.getText(),age.getText(),sex.getText(),"red"));
                                    }
                                }
                            }
                        });

                        sex.setOnKeyPressed(new EventHandler<KeyEvent>() {

                            @Override
                            public void handle(KeyEvent event) {
                                // 如果按下ENTER键则提交修改
                                if(event.getCode().getName().equals(KeyCode.ENTER.getName())){
                                    //如果文本框为空则提交原始数据
                                    if(sex.getText().equals("")){
                                        cell.commitEdit(data);
                                    }else{
                                        cell.commitEdit(new Data(name.getText(),age.getText(),sex.getText(),"red"));
                                    }
                                }
                            }
                        });


                    }

                    @Override
                    //只定义编辑单元格一定要重写的方法
                    protected void updateItem(Data item, boolean empty) {
                        // TODO Auto-generated method stub
                        super.updateItem(item, empty);

                        if(empty == false){
                            HBox hbox = new HBox();
                            Button name = new Button(item.getName());
                            Label age = new Label(item.getAge());
                            Label sex = new Label(item.getSex());
                            Rectangle rect = new Rectangle(20,20);
                            rect.setFill(Color.web(item.getColor()));
                            hbox.getChildren().addAll(rect,name,age,sex);
                            this.setGraphic(hbox);

                        }
                    }

                };
                return listcell;
            }
        });








        //设置单元格尺寸
        listview.setFixedCellSize(50);

        an.getChildren().addAll(listview,vbox);
        AnchorPane.setTopAnchor(listview, 50.0);
        AnchorPane.setLeftAnchor(listview, 30.0);
        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("javafx");
        primaryStage.setHeight(500);
        primaryStage.setWidth(500);
        primaryStage.show();
    }
}

class Data{

    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty age = new SimpleStringProperty();
    private SimpleStringProperty sex = new SimpleStringProperty();
    private SimpleStringProperty color = new SimpleStringProperty();
    public Data(String name, String age, String sex,String color){
        this.name.set(name);
        this.age.set(age);
        this.sex.set(sex);
        this.color.set(color);
    }

    public SimpleStringProperty getNameProperty() {
        return name;
    }
    public void setName(String _new){this.name.set(_new);}
    public String getName(){
        return name.get();
    }

    public SimpleStringProperty getAgeProperty() {
        return age;
    }
    public String getAge(){
        return age.get();
    }

    public SimpleStringProperty getSexProperty() {
        return sex;
    }
    public String getSex(){
        return sex.get();
    }

    public SimpleStringProperty getColorProperty() {
        return color;
    }
    public String getColor(){
        return color.get();
    }

    @Override
    public String toString() {
        return "name=" + name + ", age=" + age + ", sex=" + sex+", color="+color;
    }

}
