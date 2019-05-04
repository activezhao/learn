package com.practice;

import java.sql.Connection;

public class ConnTest {
    public static void main(String[] args){
        //实例化
        ConnPool pool = new ConnPool();
        for (int i = 0; i < 5; i++){
            //从连接池中取一个连接
            Connection conn = pool.getConn();
            System.out.println("取到的conn是: "+conn);
            pool.setConn(conn);
            System.out.println("返还的conn是: "+conn);
        }

    }
}
