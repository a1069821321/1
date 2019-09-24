package com.client.modules;

import javafx.scene.control.TextArea;

public class Client_DataAllocate {
    public void DataParser(String receive){

        String data_type=get_type_name(receive);
        MessagePrint m = new MessagePrint();
        m.print("接收到"+data_type+"类型数据" );
        //String data_dealt = receive.split(">")[1];
        if (data_type.equals("热力图")) {
            HeatPicture_build HPb = new HeatPicture_build();
            HPb.start(receive.split(">")[4]);
        }else if(data_type.equals("实时温湿图")) {
        }else if(data_type.equals("均温均湿图")) {
        }else if(data_type.equals("概况图")) {
        }else if(data_type.equals("指令")) {
        }else{
            System.out.println("接收到不明数据类型，已舍弃！");
        }


    }
    private String get_type_name(String receive){
        String data_type="不明";
        if ( receive.split(">")[1].equals("heat2picture"))
            data_type="热力图";
        else if  ( receive.split(">")[1].equals("temp&hum"))
            data_type="实时温湿图";
        else if  ( receive.split(">")[1].equals("atemp&ahum"))
            data_type="均温均湿图";
        else if  ( receive.split(">")[1].equals("gsitua"))
            data_type="概况图";
        else if  ( receive.split(">")[1].equals("command"))
            data_type="指令";
        else ;
        return data_type;
    }
}
