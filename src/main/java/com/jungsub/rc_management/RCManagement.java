package com.jungsub.rc_management;

import com.jungsub.rc_management.db.DBManager;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class RCManagement {
    public static Scanner input = new Scanner(System.in);

    private static ArrayList<Student> datas;

    public static void main(String[] args) {
        DBManager.startDb();
        int menu = -1;
        // Load data
        try {
            datas = FileManager.load("students.dat");
        } catch(FileNotFoundException e) {
            System.out.println("파일이 없습니다.");
        }
        catch (Exception e) {
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
                    datas.add(Menu.addNewEntry());
                    break;
                case 2:
                    Menu.checkStudent(datas);
                    break;
                case 3:
                    Menu.deleteStudent(datas);
                    break;
                case 4:
                    Menu.editStudent(datas);
                    break;
                case 5:
                    Menu.viewStudents(datas);
                    break;
                case 6:
                    Menu.masterSearch(datas);
                    break;
                case 7:
                    Menu.save(datas);
                    break;
            }
        }
        try {
            DBManager.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // Default StudentID
    public static Student[] getStudent(ArrayList<Student> data, String studentId) {
        return getStudent(data, SearchType.STUDENTID, studentId);
    }

    public static Student[] getStudent(ArrayList<Student> data, SearchType st, String search) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student student : data) {
            switch (st) {
                case STUDENTID:
                    if (String.valueOf(student.getStudentId()).equals(search.trim())) result.add(student);
                    break;
                case NAME:
                    if (student.getName().trim().contains(search.trim())) result.add(student);
                    break;
                case ROOMNUMBER:
                    if (String.valueOf(student.getRoomNumber()).equals(search.trim())) result.add(student);
                    break;
                case PENALITYPOINT:
                    if (String.valueOf(student.getPenaltyPoints()).equals(search.trim())) result.add(student);
                    break;
                case RC:
                    if (student.RC().name.trim().equals(search.trim())) result.add(student);
                    break;
            }
        }
        return result.toArray(new Student[0]);
    }

    public enum SearchType {
        STUDENTID("학번"),
        NAME("이름 (포함)"),
        ROOMNUMBER("방번호"),
        PENALITYPOINT("벌점"),
        RC("기숙사");
        final String kor;

        SearchType(String kor) {
            this.kor = kor;
        }
    }


}
