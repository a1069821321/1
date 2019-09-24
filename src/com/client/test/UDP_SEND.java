package com.client.test;
import java.net.*;
public class UDP_SEND extends Thread{
    String messsage = "天气很好";
    int port = 9898;
    InetAddress iaddress = null;
    MulticastSocket socket = null;
    UDP_SEND(){
        try{
            iaddress = InetAddress.getByName("224.255.10.0");
            socket = new MulticastSocket(port);
            socket.setTimeToLive(1);
            socket.joinGroup(iaddress);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void run(){
        while(true){
            DatagramPacket packet = null;
            byte data[]=messsage.getBytes();
            packet = new DatagramPacket(data,data.length,iaddress,port);
            System.out.println(new String(data));
            try{
                socket.send(packet);
                sleep(3000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public  static void main(String[] args) {
        UDP_SEND m = new UDP_SEND();
        m.start();
    }
}
