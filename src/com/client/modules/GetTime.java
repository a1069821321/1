package com.client.modules;
import java.util.Date;
import java.text.SimpleDateFormat;
public class GetTime {
    public static String gettime(){
        SimpleDateFormat df = new SimpleDateFormat("MM-dd HH:mm:ss ");//设置日期格式
        String time = df.format(new Date());// new Date()为获取当前系统时间
        return time;
    }
}



