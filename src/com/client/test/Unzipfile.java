package com.client.test;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class Unzipfile {
    public static void main(String[] temp){
        File file = new File("hello.zip");
        ZipInputStream zin;
        try {
            ZipFile zipFile = new ZipFile(file);
            zin = new ZipInputStream(new FileInputStream(file));
            ZipEntry entry = zin.getNextEntry();
            while (((entry = zin.getNextEntry()) != null) && !entry.isDirectory()) {
                File tmp = new File("D:\\" + entry.getName());
                if (!tmp.exists()) {
                    tmp.getParentFile().mkdirs();
                    OutputStream os = new FileOutputStream(tmp);
                    InputStream in = zipFile.getInputStream(entry);
                    int count = 0;
                    while ((count = in.read()) != -1) {
                        os.write(count);
                    }
                    os.close();
                    in.close();
            }
            zin.closeEntry();
            System.out.println(entry.getName() + "解压成功");
        }
        zin.close();
        }catch(Exception e){
        e.printStackTrace();
     }
    }
}
