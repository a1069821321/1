package com.client.test;
import java.awt.*;
import java.util.Random;
import javax.swing.*;
import java.lang.Math;

import static java.lang.Math.abs;

public class thread_line_drawn extends JFrame{
    private Thread t;
    private static Color[] color = {Color.BLACK, Color.BLUE, Color.CYAN,Color.GREEN,Color.ORANGE,Color.YELLOW,Color.RED};
    private static final Random rand = new Random();
    private static Color getC() {
        return color[rand.nextInt(color.length)];
    }
    public thread_line_drawn(){
        t= new Thread(new Runnable() {
            int x =200;
            int y = 300;
            int r = 30;
            double test = 1;
            int n =1;
            @Override
            public void run() {
                while(true){
                    try{
                        Thread.sleep(100);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    Graphics graphics = getGraphics();
                    graphics.setColor(getC());
                    graphics.drawLine(100,100,x,y);
                    x=x+n;
                    if (abs(x)==100+r){
                            n=-n;
                    }
                    test=Math.sqrt(r*r-(x-100)*(x-100))+100;
                    y=(int)test;
                    }
                }


        });
        t.start();
    }
    public static void main(String[] args){
        init(new thread_line_drawn(),600,600);
    }
    public static void init(JFrame frame,int width,int height){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width,height);
        frame.setVisible(true);
    }


}
