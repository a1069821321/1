package com.client.modules;

import java.io.File;
import java.net.Socket;

public class Server_RequestResponse {
    private Thread time_late;

    public void deal(Socket client, String reciver) {
        //时间戳>数据类型>机器数>机号
            /*
            long time = System.currentTimeMillis();
            String request_type="heat2picture";
            String machine_numbers="1";
            String machine_name="BaoDing1";
            String information =time+">"+request_type+">"+machine_numbers+">"+machine_name;
             */
        String request_type = reciver.split(">")[1];
        if (request_type.equals("heat2picture")) {
            String information = reciver;
            new Thread(new Send_data(client, information)).start();

        }else if(request_type.equals("binfo")){
            ;
        }else{
            System.out.println("请求类型出错！");
        }
    }

    //建立数据传输线程
    class Send_data implements Runnable {
        private Socket client;
        private String information;

        private Send_data(Socket client, String information) {
            this.client = client;
            this.information = information;
        }

        @Override
        public void run() {
            try {
                while(true) {
                    time_late.sleep(500);
                    String data = get_data(information);
                    //new Thread(new Server_Writer(client, data)).start();
                    Server_Writer sw= new Server_Writer(client, data);
                    String result =sw.call();
                    if(result=="0"){
                        System.out.println("停止传输数据！");
                        break;}

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public String read_heat_data() {
            ReadTxt read = new ReadTxt();
            String data[] = {"D:\\heat2picture\\data\\人.txt",
                    "D:\\heat2picture\\data\\人2.txt",
                    "D:\\heat2picture\\data\\人3.txt",
                    "D:\\heat2picture\\data\\手.txt"};

            long randomNum = System.currentTimeMillis();
            int ran1 = (int) (randomNum % 10 * 0.4);
            final File data_file = new File(data[ran1]);
            System.out.println("获取" + ran1 + "号数据");
            String heat_data0 = read.txt2String(data_file);
            String heat_data = System.currentTimeMillis() + ">" + "heat2picture>" + "BaoDing1>" + ">" + heat_data0;
            return heat_data;
        }

        //数据接口
        private String get_data(String information) {
            return read_heat_data();
        }
    }
}