package com.client.test;

import com.client.modules.DragUtil;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.*;

/**
 * @author Administrator
 */
public class ComputerServer extends Application {
	public  static Stage main_stage;
	private static Thread sendt;
	private boolean OVER_FLAG = false;
	public void start(Stage mainstage) {
		main_stage = mainstage;
		mainstage.initStyle(StageStyle.TRANSPARENT);
		VBox root = new VBox();
		root.setPrefSize(300, 300);
		TextField receive_field = new TextField();
		receive_field.setPrefSize(100, 25);

		Button send_button = new Button("Send");
		send_button.setOnMousePressed(event -> {
			new Thread() {
				public void run() {
			try {
				String data1 = "允许连接";
				System.out.println("服务端初始化完毕，正在等待");
				ServerSocket serversocket = new ServerSocket(8989);
				Socket socket = serversocket.accept();
				System.out.println("服务端收到数据");
				while(!OVER_FLAG) {
					final InputStream is = socket.getInputStream();
					InputStreamReader reader = new InputStreamReader(is);
					BufferedReader bf = new BufferedReader(reader);
					String info = null;
					while ((info = bf.readLine()) != null) {
						if ("请求连接".equals(info)) {
							System.out.println("服务端收到连接请求");
							OVER_FLAG = true;
							break;
						}

					}
					//is.close();
					//bf.close();
				}
				serversocket.accept();
				System.out.println("服务端接收对话结束");
				OutputStream os = socket.getOutputStream();
				PrintWriter pw = new PrintWriter(os);
				pw.write(data1);
				System.out.println("服务端向客户端发送数据成功");
				pw.flush();
				os.close();
				pw.close();
			}catch (IOException e){
				e.printStackTrace();
			}
				}
			}.start();
		});

		root.getChildren().addAll(receive_field, send_button);
		Scene scene = new Scene(root);
		mainstage.setScene(scene);
		// 显示
		mainstage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
