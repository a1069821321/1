package com.client.window;
import java.awt.*;
import java.net.URL;
import javax.swing.*;
public class LoginDialog extends JFrame{
    private Image image;
    public void Login_Window() {
        JFrame login = new JFrame("登录");
        login.setVisible(true);
        login.setSize(300,300);
        login.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        login.setResizable(false);
        Font mf = new Font("黑体",Font.PLAIN,16);Font ms = new Font("宋体",Font.PLAIN,12);
        Container container = login.getContentPane();
        JButton button_login = new JButton("开始");
        JButton button_set = new JButton("设置");
        button_login.setBounds(60,200,80,30);button_login.setFont(mf);
        button_set.setBounds(170,200,80,30);button_set.setFont(mf);
        JLabel label_uid = new JLabel("刷新频率");label_uid.setFont(mf);
        JLabel label_pwd = new JLabel("密码");label_pwd.setFont(mf);
        /*
        final JLabel backpic = new JLabel();
        URL resource = this.getClass().getResource("login1.jpg");
        ImageIcon icon = new ImageIcon(resource);
        backpic.setIcon(icon);
        backpic.setBounds(0,0,100,100);
        */
        JTextField f_uid = new JTextField();
        JPasswordField f_pwd = new JPasswordField();f_pwd.setEchoChar('*');
        JCheckBox remenberpwd = new JCheckBox();remenberpwd.setText("记住密码");remenberpwd.setBackground(new Color(255,255,255));
        JCheckBox autologin = new JCheckBox();autologin.setText("自动登录");autologin.setBackground(new Color(255,255,255));
        remenberpwd.setFont(ms);autologin.setFont(ms);
        f_uid.setBounds(80,80,170,22);
        f_pwd.setBounds(80,120,170,22);
        label_uid.setBounds(10,78,8/0,22);
        label_pwd.setBounds(30,118,40,22);
        remenberpwd.setBounds(70,155,80,20);
        autologin.setBounds(180,155,80,20);
        container.add(button_login);
        container.add(button_set);
        container.add(label_uid);
        container.add(label_pwd);
        container.add(f_pwd);
        container.add(f_uid);
        container.add(remenberpwd);
        container.add(autologin);
        container.setBackground(Color.white);

    }

    public static void main(String args[]){
        new LoginDialog().Login_Window();
    }

}

