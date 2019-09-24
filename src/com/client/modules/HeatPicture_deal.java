package com.client.modules;
import com.client.Main_window;
import javafx.scene.image.Image;
import org.opencv.core.*;
import org.opencv.imgcodecs.*;
import org.opencv.imgproc.Imgproc;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import static org.opencv.highgui.HighGui.*;
import static org.opencv.imgproc.Imgproc.medianBlur;

public class HeatPicture_deal {

    public static Mat BufImg2Mat (BufferedImage original, int imgType, int matType) {
        if (original == null) {
            throw new IllegalArgumentException("original == null");
        }

        // Don't convert if it already has correct type
        if (original.getType() != imgType) {
            // Create a buffered image
            BufferedImage image = new BufferedImage(original.getWidth(), original.getHeight(), imgType);
            // Draw the image onto the new buffer
            Graphics2D g = image.createGraphics();
            try {
                g.setComposite(AlphaComposite.Src);
                g.drawImage(original, 0, 0, null);
            } finally {
                g.dispose();
            }
        }
        DataBufferByte dbi =(DataBufferByte)original.getRaster().getDataBuffer();
        byte[] pixels = dbi.getData();
        Mat mat = Mat.eye(original.getHeight(), original.getWidth(), matType);
        mat.put(0, 0, pixels);
        return mat;
    }
    public void start(BufferedImage bi){
        try{
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
             //src_g =BufImg2Mat(bi,BufferedImage.TYPE_INT_BGR,CvType.CV_16SC1);
            Mat src_g=Imgcodecs.imread("D:\\heat2picture\\Inter_test\\x.png");
            //读取图像到矩阵中
            if(src_g.empty()){
                throw new Exception("no file");
            }
            Mat dst_g = src_g.clone();
            Imgproc.GaussianBlur(src_g,dst_g,new Size(31,31),0,0);
            Imgcodecs.imwrite("D:\\heat2picture\\0831A_1.png", dst_g);

        }catch(Exception e){
            System.out.println("例外：" + e);
        }
        Mat src_m=Imgcodecs.imread("D:\\heat2picture\\0831A_1.png");//读入原图
        //进行中值滤波操作
        Mat dst_m=Imgcodecs.imread("D:\\heat2picture\\0831B_1.png");
        medianBlur(src_m,dst_m,19);
        Imgcodecs.imwrite("D:\\heat2picture\\0831B_1.png", dst_m);
        Mat src_s=Imgcodecs.imread("D:\\heat2picture\\0831B_1.png");
        Mat dst_s = src_s.clone();
        Mat kernel =new Mat(5, 5, CvType.CV_16SC1);
        kernel.put(0, 0,
                -1,-1,-1,-1,-1, -1,-1,-1,-1,-1, -1,-1,25,-1,-1, -1,-1,-1,-1,-1, -1,-1,-1,-1,-1);//核
        //0, 0, 0, -1, 0, -1, 5, -1, 0, -1, 0
        //-1, -1, -1, -1, 9, -1, -1, -1, -1
        //1,1,1,1,-7,1,1,1,1
        //-1,-1,-1,-1,-1, -1,-1,-1,-1,-1, -1,-1,25,-1,-1, -1,-1,-1,-1,-1, -1,-1,-1,-1,-1
        Imgproc.filter2D(src_s,dst_s,src_s.depth(),kernel);
        Imgcodecs.imwrite("D:\\heat2picture\\0831C_1.png", dst_s);
        Imgcodecs.imwrite("D:\\heat2picture\\Inter_test\\final.png", dst_s);
        javafx.scene.image.Image image = new Image("file:D:\\heat2picture\\Inter_test\\final.png");
        Main_window.heat_picture.setImage(image);
        System.out.println(GetTime.gettime()+"  图片重置");
    }

}