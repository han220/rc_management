package com.jungsub.rc_management;

import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class RCManagement {
    public static Scanner input = new Scanner(System.in);

    private static ArrayList<Student> datas;

    public static void main(String[] args) {
        DatabaseManager dm = new DatabaseManager();

        int menu = -1;
        // Load data
        try {
            datas = FileManager.load("students.dat");
        } catch (FileNotFoundException e) {
            System.out.println("파일이 없습니다.");
        } catch (Exception e) {
            System.out.println("파일을 읽는 과정에 오류가 발생했습니다.");
            datas = null;
        } finally {
            if (datas == null) datas = new ArrayList<>();
        }

        while (menu != 0) {
            Menu.getMenu();
            menu = Menu.getMenuId();

            switch (menu) {
                case 1:
//                    datas.add(Menu.addNewEntry());
                    upsertStudent(dm, Menu.addNewEntry(), false);
                    break;
                case 2:
                    Menu.checkStudent(dm);
                    break;
                case 3:
                    Menu.deleteStudent(dm);
                    break;
                case 4:
                    Menu.editStudent(dm);
                    break;
                case 5:
                    Menu.viewStudents(dm);
                    break;
                case 6:
                    Menu.masterSearch(dm);
                    break;
                case 7:
                    Menu.save(datas);
                    break;
            }
        }
        dm.close();
    }

    public static boolean upsertStudent(DatabaseManager dm, Student student, boolean update) {
        try {
            // Prepared statement 생성
            PreparedStatement pstmt;
            if(update) {
                pstmt = dm.getConn().prepareStatement("UPDATE Student SET " +
                        "name = ?, roomNumber = ?, penaltyPoints = ?, semester = ?, rc = ?" +
                        "WHERE studentId = ?");
            } else {
                pstmt = dm.getConn().prepareStatement("INSERT INTO Student (" +
                        "name, roomNumber, penaltyPoints, semester, rc, studentId) " +
                        "VALUES (?, ?, ?, ?, ?, ?)");
            }
            // 입력된 학생 데이터 추가
            pstmt.setObject(6, student.getStudentId());
            pstmt.setObject(1, student.getName());
            pstmt.setObject(2, student.getRoomNumber());
            pstmt.setObject(3, student.getPenaltyPoints());
            pstmt.setObject(4, student.getSemester());
            pstmt.setObject(5, student.getRc());

            // 쿼리 실행
            pstmt.execute();

            // 입력건수
            int inserted = pstmt.getUpdateCount();

//            dm.getConn().commit();

            if (inserted == 1) {
                return true;
            } else {
                System.out.println("데이터 추가중 오류발생!");
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    // Default StudentID
    public static Student[] getStudent(DatabaseManager dm, String studentId) {
        return getStudent(dm, SearchType.STUDENTID, studentId);
    }

    public static Student[] getStudent(DatabaseManager dm, SearchType st, String search) {
        ArrayList<Student> result = new ArrayList<>();
        String searchSQL;
        try {
            PreparedStatement preparedStatement;
            if (st == null) {
                searchSQL = "SELECT * FROM Student";
                 preparedStatement = dm.conn.prepareStatement(searchSQL);
            } else {
                searchSQL = "SELECT * FROM Student WHERE " + st.sqlCol + " = ?";
                preparedStatement = dm.conn.prepareStatement(searchSQL);
                preparedStatement.setObject(1, search.trim());
            }

            // 데이터 조회
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
//                System.out.println(rs.getObject("createdAt"));
                Student s = new Student(rs.getString("name"),
                        rs.getInt("studentId"),
                        rs.getInt("roomNumber"),
                        rs.getInt("penaltyPoints"),
                        rs.getString("semester"),
                        rs.getString("rc"),
                        rs.getObject("createdAt"));
                result.add(s);
            }
            return result.toArray(new Student[0]);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Student[0];
    }

    public enum SearchType {
        STUDENTID("학번", "studentId"),
        NAME("이름", "name"),
        ROOMNUMBER("방번호", "roomNumber"),
        PENALITYPOINT("벌점", "penaltyPoints"),
        RC("기숙사", "rc");
        final String kor, sqlCol;

        SearchType(String kor, String sqlCol) {
            this.kor = kor;
            this.sqlCol = sqlCol;
        }
    }


}
