package com.example.connectmysql;
import java.sql.*;
public class WebRs {
    private Connection conn=null;
    private Statement stmt=null;
    private ResultSet rs=null;
    private ResultSetMetaData stmd=null;
    public WebRs(String dri,String user,String pass,String url){
        try {
            Class.forName(dri);
        } catch (Exception e) {
            System.out.println("加载驱动异常");
        }
        try {
            conn=DriverManager.getConnection(url,user,pass);
            if(conn!=null){
                stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    public ResultSet getres(String sql){
        try {
            rs=stmt.executeQuery(sql);
            if(rs!=null){
            return rs;   
            }else{
                return null;
            }
        } catch (Exception e) {
            System.out.println("数据库查询失败");
        }
        return rs;
    }
   
}