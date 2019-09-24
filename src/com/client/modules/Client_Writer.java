package com.client.modules;

import javafx.scene.control.TextArea;

import java.io.*;
import java.net.Socket;

public class Client_Writer implements Runnable{
    private Socket client;
    private String request;
    public Client_Writer(Socket client,String request) {
        this.client = client;
        this.request = request;
    }
    @Override
    public void run() {
        DataOutputStream dos = null;
        try {
                dos = new DataOutputStream(client.getOutputStream());
                dos.writeUTF(request);
                MessagePrint m = new MessagePrint();
                Thread.sleep(500);
                m.print("客户端发送" + request.split(">")[1] + "请求成功");
        } catch (Exception e) {
            e.printStackTrace();
            MessagePrint m = new MessagePrint();
            m.print("到服务器的请求无回应！");
        } finally {
        }
    }
}