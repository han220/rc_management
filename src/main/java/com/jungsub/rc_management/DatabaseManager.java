package com.jungsub.rc_management;

import java.sql.*;

public class DatabaseManager {
    public Connection conn;

    public final String FILENAME = "data.db";
    private final String CREATETBL =
            "create table if not exists Student (" +
                    "studentId integer primary key," +
                    "name varchar(20)," +
                    "roomNumber integer," +
                    "penaltyPoints integer," +
                    "semester varchar(20)," +
                    "rc varchar(20)," +
                    "createdAt datetime DEFAULT CURRENT_TIMESTAMP)"
            ;


    public DatabaseManager() {
        Statement stmt = null;

        // sqlite jdbc 클래스 확인
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            //test.db 데이터베이스 연결(없으면 생성)
            conn = DriverManager.getConnection("jdbc:sqlite:" + FILENAME);

            //테이블 생성 : SQL문 실행
            stmt = conn.createStatement();
            stmt.execute(CREATETBL);
            stmt.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Connection getConn() {
        return conn;
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
