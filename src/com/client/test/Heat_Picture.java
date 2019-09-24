package com.client.test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import com.client.modules.ReadTxt;

public class Heat_Picture {
    public static void main(String[] args){
        allstart("人");
        allstart("人2");
        allstart("人3");
        allstart("手");

    }
    public static void allstart(String path){
        BufferedImage bi = new BufferedImage(320, 240, BufferedImage.TYPE_INT_BGR);
        String ppath="D:\\热成像图片处理\\数据\\"+path+".txt";
        final File data_file = new File(ppath);
        ReadTxt read= new ReadTxt();
        String heat_data=read.txt2String(data_file);
        heat_data=heat_data.replaceAll("\n|\r","");
        String[] data0 = heat_data.split(" ");
        for (int i =1;i<data0.length;i++){
            if(i==768)
                data0[i]=data0[i].substring(0, 4); //最后一个数据后带有空格，须消除
            data0[i-1]=data0[i];

        }
        int[] data =new int[769];
        for (int i =0;i<data0.length;i++)
            data[i]=Integer.parseInt(data0[i]);
        data= Arrays.copyOf(data,768);
        for (int i =0;i<data.length;i++){
            System.out.println(data[i]);
        }

        writeImage(bi,data,path);
        //dealImage(data);

    }



    private static int[] getM(int[] data){
        int min=data[0];
        int max=data[0];
        for (int i =0;i<data.length;i++){
            if (min>data[i])
                min =data[i];
            if (max<data[i])
            max =data[i];
        }
        int[] result = new int[2];
        result[0]=min;
        result[1]=max;
        return result;
    }
    public static void dealImage(int[] data){
        try {
            int max,min;
            BufferedImage img = ImageIO.read(new File("D:\\热成像图片处理\\test.png"));
            int[] nums = getM(data);
            min= nums[0];
            max=nums[1];
            int[] rgb = new int[3];
            int p;
            //蓝色 -5-10  紫色 10-25 红色 25-40
            for (int x = 0; x < 320; x++) {
                for (int y = 0; y < 240; y++) {
                    int pixel =img.getRGB(x,y);
                    p = (pixel & 0xff0000)>>16;
                    //rgb[1] = (pixel & 0xff00) >> 8;
                    //rgb[2] = (pixel & 0xff);
                    //System.out.println(rgb[0]+" "+rgb[1]+" "+rgb[2]);


                }
                }

        }catch (IOException e){
            e.printStackTrace();
        }

    }


    public static boolean writeImage(BufferedImage bi,int[] data,String path){
        int i,j,r,g,b,min,max;
                int[] nums = getM(data);
                min= nums[0];
                max=nums[1];
                for (int x = 0; x < 32; x++) {
                    for (int y = 0; y < 24; y++) {
                        i = x + y*32;
                        if(i>=769)
                            System.out.println("data num error!");
                        try {
                            j = data[i];
                            //黑白色
                            r=g=b=(j-min)*255/(max-min);
                            //最低值20

                            if (j>2000 && j<=2600){
                                g = 0;
                                b = 255;
                                r = 120*(j-2000)/(2600-2000);
                            }
                            else if (j>2600 && j<=2800){
                                g = 0;
                                b = 255;
                                r = 120+135*(j-2600)/(2800-2600);
                            }
                            else if(j>2800 && j<=3400) {
                                r = 255;
                                g = 0;
                                b = 255-255*(j-2800)/(3400-2800);
                            }
                            else {
                                r = 0;b = 0;
                                g = 0;}




                            Color color = new Color(r, g, b);


                            //原像素
                            /*
                            bi.setRGB(x,y,color.getRGB());
                            */

                            //增大像素
                            for(int x2=x*10;x2<x*10+10;x2++){
                                for(int y2=y*10;y2<y*10+10;y2++){
                                    bi.setRGB(x2, y2, color.getRGB());
                                }
                            }



                        }catch (Exception e){
                            e.printStackTrace();

                        }

                    }
                }
            String ppath ="D:\\热成像图片处理\\"+path+".png";
            try {
                ImageIO.write(bi, "png", new File(ppath));
               // Desktop.getDesktop().open(new File(ppath));
            }catch (Exception e){
                e.printStackTrace();
            }
            boolean val = false;
            return val;
    }
}
