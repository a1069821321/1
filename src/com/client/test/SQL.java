package com.client.test;
import java.sql.*;
public class SQL {
    Connection conn;
    Statement stmt;
    ResultSet rs;
    public Connection getConnection(){
        String sql="SELECT * FROM [user]";
        String url ="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=ertu";
        String user = "reader";
        String password = "r123456";
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("数据库驱动加载成功");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try{
            conn = DriverManager.getConnection(url,user,password);
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);
            System.out.println("数据库连接成功");
            while(rs.next())
            {
                String usr=rs.getString("user");
                String pwd=rs.getString("pwd");
                int istest=rs.getInt("istest");
                System.out.println("user:"+usr+"\tpwd:"+pwd+"\tistest:"+istest);
            }
            if(rs!=null){rs.close();rs=null;}
            if(stmt!=null){stmt.close();stmt=null;}
            if(conn!=null){conn.close();conn=null;}
        }catch (SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
    public static void main(String[] args){
        SQL s = new SQL();
        s.getConnection();
    }

}
