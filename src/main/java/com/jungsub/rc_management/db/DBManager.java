package com.jungsub.rc_management.db;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;
import java.sql.*;

public class DBManager {
    public static Connection conn;
    public static final String FILENAME = "data.db";

    public static void startDb() {
        conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;

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
            // sql 스키마 스키립트 실행
            ScriptRunner sr = new ScriptRunner(conn);
            Reader reader = new BufferedReader(new FileReader("template.sql"));
//            stmt = conn.createStatement();
//            stmt.execute(CREATETBL);
            sr.runScript(reader);
            sr.closeConnection();
            reader.close();

//            //데이터 추가 : Statment 사용
//            String sql = "insert into score (name, kor, eng, math) values ('홍길동', 100, 80, 90)";
//            stmt.execute(sql);
//
//            //데이터 추가 : PreparedStatement 사용
//            String sql2 = "insert into score (name, kor, eng, math) values(?,?,?,?)";
//            pstmt = conn.prepareStatement(sql2);
//            pstmt.setString(1, "김한동");
//            pstmt.setInt(2, 90);
//            pstmt.setInt(3, 100);
//            pstmt.setInt(4, 80);
//            pstmt.executeUpdate();
//
//            //데이터 조회 : SQL문 실행
//            ResultSet rs = stmt.executeQuery("select * from score");
//            while(rs.next()) {
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//                int kor = rs.getInt("kor");
//                int eng = rs.getInt("eng");
//                int math = rs.getInt("math");
//                String regdate = rs.getString("regdate");
//                System.out.println(id + " " + name + " " + kor + " " + eng + " " + math + " " + regdate);
//            }
//            rs.close();
//            pstmt.close();
//            stmt.close();
//            conn.close();

        } catch (SQLException | FileNotFoundException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void close() throws SQLException {
        conn.close();
    }
}
