package com.client.modules;
import com.client.Main_window;
import com.client.window.ConnectSet;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
public class Client {
    public static  String IP = "127.0.0.1";//服务器地址
    public static  int PORT = 48464;//服务器端口号
    public int handler(){
        try {//实例化一个Socket，并指定服务器地址和端口
            roadini();
            Socket client = new Socket(IP, PORT);//开启两个线程，一个负责读，一个负责写
            new Thread(new ClientReadHandlerThread(client)).start();
            //new Thread(new ClientWriteHandlerThread(client,"。")).start();

            Main_window.client = client;
            return 1;
        } catch (Exception e) {
            MessagePrint m = new MessagePrint();
            m.print("连接服务器失败");
            return 0;
        }

    }
    private void roadini(){
        try {
            IP = Properate.read("connectset_server", "a.properties");
            PORT = Integer.parseInt(Properate.read("connectset_port", "a.properties"));
        }catch (Exception e ){
            ConnectSet b = new ConnectSet();
            b.start(new Stage());
        }
    }


}
//内部类处理读操作的线程
class ClientReadHandlerThread implements Runnable{
    private Socket client;
    public ClientReadHandlerThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        DataInputStream dis = null;
        try {
            MessagePrint m = new MessagePrint();
            m.print("连接服务器成功");
            while(true){//读取服务器端数据2
                dis = new DataInputStream(client.getInputStream());
                String receive = dis.readUTF();
                Client_DataAllocate d = new Client_DataAllocate();
                d.DataParser(receive);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                if(dis != null){
                    dis.close();
                }
                if(client != null){
                    client = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
//内部类客户端写操作线程
class ClientWriteHandlerThread implements Runnable{
    private Socket client;
    private String information;
    public ClientWriteHandlerThread(Socket client,String information) {
        this.client = client;
        this.information = information;
    }
    @Override
    public void run() {
        DataOutputStream dos = null;
        BufferedReader br = null;

        //时间戳>数据类型>机器数>机号
        /*
            long time = System.currentTimeMillis();
            String request_type="heat2picture";
            String machine_numbers="1";
            String machine_name="BaoDing1";
            String information =time+">"+request_type+">"+machine_numbers+">"+machine_name;

         */

        //new Thread(new ClientWriteHandlerThread(client,message)).start();
        try {
            while(true){//取得输出流
                System.out.print("请输入:\t");// 键盘录入
                br = new BufferedReader(new InputStreamReader(System.in));
                String request = br.readLine();//发送数据
                //String request = information;
                dos = new DataOutputStream(client.getOutputStream());
                dos.writeUTF(request);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{
                if(dos != null){
                    dos.close();
                }
                if(br != null){
                    br.close();
                }
                if(client != null){
                    client = null;
                }
            }catch(Exception e){
                e.printStackTrace(); }
        }
    }
}
