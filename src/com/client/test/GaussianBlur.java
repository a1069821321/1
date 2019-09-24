package com.client.test;
import org.opencv.core.*;
import org.opencv.imgcodecs.*;
import org.opencv.imgproc.Imgproc;
import static org.opencv.highgui.HighGui.*;
import static org.opencv.imgproc.Imgproc.medianBlur;

public class GaussianBlur {

    public static void main(String[] args) {
        try{
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

            Mat src_g=Imgcodecs.imread("D:\\heat2picture\\original\\3.png");
            //读取图像到矩阵中
            if(src_g.empty()){
                throw new Exception("no file");
            }

            Mat dst_g = src_g.clone();
            //复制矩阵进入dst

            Imgproc.GaussianBlur(src_g,dst_g,new Size(31,31),0,0);
            //图像模糊化处理11
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



    }

}

