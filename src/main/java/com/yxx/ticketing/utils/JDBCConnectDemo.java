package com.yxx.ticketing.utils;


import java.sql.*;

public class JDBCConnectDemo {
    public static void main(String[] args) throws Exception{

        //加载数据库驱动程序
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException cne){
            cne.printStackTrace();
        }
        String dburl = "jdbc:mysql://81.68.231.201/Plane_Ticket_Sys";//?serverTimezone=GMT%2B8
        String sql = "SELECT * FROM t_user";
        try(Connection conn = (Connection) DriverManager.getConnection(dburl,"root","zhang1111");
            Statement stmt = conn.createStatement();
            ResultSet rst = stmt.executeQuery(sql))

        {
            while (rst.next()){
                System.out.println(rst.getString(1));
                System.out.println(rst.getString(2));
            }

        }catch (SQLException se){
            se.printStackTrace();
        }

    }
}
