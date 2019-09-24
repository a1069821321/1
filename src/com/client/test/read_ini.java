package com.client.test;

import org.dtools.ini.*;


import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class read_ini {

    public static void main(String[] args) {
    addItems("connect","D://setting.ini");
    //ini_write("connect", "server_ip", "127.0.0.2", "D://setting.ini");
    ini_write("connect", "port", "7989", "D://setting.ini");
        ini_traverse("D://setting.ini");
    }

    public static void init(){
        File file = new File("D://setting.ini");
        if (file.exists()) {
        } else {
            System.out.println("初始化");
            try {
                File newfile = new File("D://setting.ini");
            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }

    public static void addItems(String item,String filepath){
        try {
            IniFile iniFile = new BasicIniFile();
            IniSection dataSection = new BasicIniSection(item);
            iniFile.addSection(dataSection);
            File newfile = new File("D://setting.ini");
            IniFileWriter iniFileWriter = new IniFileWriter(iniFile, newfile);
            iniFileWriter.write();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void judeFileExists(File file) {
            if (file.exists()) {
                         System.out.println("file exists");
                     } else {
                        System.out.println("file not exists, create it ...");
                        try {
                            IniFile iniFile = new BasicIniFile();
                            IniSection dataSection = new BasicIniSection("password");
                            //写入修改
            		        //iniItem.setValue("Konan");
                            //iniSection.addItem(iniItem);
                            iniFile.addSection(dataSection);
                            File newfile = new File("D://setting.ini");
                            IniFileWriter iniFileWriter = new IniFileWriter(iniFile,newfile);
                            iniFileWriter.write();

                             } catch (Exception e) {

                                e.printStackTrace();
                             }
                     }

             }
    //保存ini文件，group分组必须存在，不能为空
    public static void ini_write(String group, String key, String value, String filepath)
    {
        IniFile iniFile=new BasicIniFile();
        File file=new File(filepath);
        IniFileReader rad=new IniFileReader(iniFile, file);
        IniFileWriter wir=new IniFileWriter(iniFile, file);
        try {
            rad.read();
            IniSection iniSection=iniFile.getSection(group);
            //System.out.println(iniSection.getName());
            IniItem iniItem=iniSection.getItem(key);
            if(iniItem==null)
            {
                iniItem=new IniItem(key);
                iniItem.setValue(value);
                iniSection.addItem(iniItem);
            }
            else {
                iniItem.setValue(value);
            }

            wir.write();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void get_content(){
        File file = new File("D://my.ini");
        IniFile iniFile = new BasicIniFile();
        IniFileReader rad =  new IniFileReader(iniFile,file);
        try {
            rad.read();
        }catch (Exception e)
        {e.printStackTrace();}
        IniSection iniSection = iniFile.getSection(0);
        //得到这个section中所有的key值
        Collection<String> collection = iniSection.getItemNames();
        for (String coll: collection) {
            IniItem iniItem = iniSection.getItem(coll);
            //得到key值对应的value
            String value = iniItem.getValue();
        }

    }

    public static String ini_read(String group, String key, String default_value, String filepath)
    {
        IniFile iniFile=new BasicIniFile();
        File file=new File(filepath);
        IniFileReader rad=new IniFileReader(iniFile, file);
        try {
            //读取item
            rad.read();
            IniSection iniSection=iniFile.getSection(group);
            if(iniSection==null)
                return default_value;
            IniItem iniItem=iniSection.getItem(key);
            if (iniItem==null) {
                return default_value;
            }
            return iniItem.getValue();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void ini_traverse(String filepath) {
        try {
            IniFile ini = new BasicIniFile(false);//不使用大小写敏感
            IniFileReader reader = new IniFileReader(ini, new File(filepath));
            reader.read();
            for(int i=0;i<ini.getNumberOfSections();i++){
                IniSection sec = ini.getSection(i);
                System.out.println("---- " + sec.getName() + " ----");
                for(IniItem item : sec.getItems()){
                    System.out.println(item.getName() + " = " + item.getValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
