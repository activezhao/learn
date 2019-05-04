package com.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;

/**
 * 连接池
 */
public class ConnPool {
    //设置list存储连接
    LinkedList<Connection> pools = null;
    private String DriverName = "com.mysql.cj.jdbc.Driver";
    private String DB = "ssmdemo";
    private String url = "jdbc:mysql://localhost:3306/"+DB+"?serverTimezone=UTC&useSSL=true";
    private String user = "root";
    private  String passworf = "123456";

    public ConnPool(){
        try{
            Class.forName(DriverName);
        }catch(Exception e){
            e.printStackTrace();
        }

        pools = new LinkedList<>();
        //初始化10个连接
        for (int i = 0; i < 10; i++){
            try{
                Connection conn = DriverManager.getConnection(url,user,passworf);
                //将连接放到池中
                pools.add(conn);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 从连接池中取连接，，并从list的最前面取
     * @return
     */
    public Connection getConn(){
        //如果池子中没有了连接，就新建一个
        if (pools.size()<1){
                try{
                    Connection conn = DriverManager.getConnection(url,user,passworf);
                    //将连接放到池中
                    pools.add(conn);
                }catch(Exception e){
                    e.printStackTrace();
                }
        }
        return pools.removeFirst();
    }

    /**
     * 将用完的连接还给连接池，并放到list的最后面
     * @param conn
     */
    public void setConn(Connection conn){
        pools.addLast(conn);
    }
}
