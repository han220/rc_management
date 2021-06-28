package com.jungsub.rc_management;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class RCManagement {
    public static Scanner input = new Scanner(System.in);

    private static ArrayList<Student> datas = new ArrayList<>();

    public static void main(String[] args) {
        int menu = -1;
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
            }
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
                    if (student.getStudentId().trim().equals(search.trim())) result.add(student);
                    break;
                case NAME:
                    if (student.getName().trim().contains(search.trim())) result.add(student);
                    break;
                case ROOMNUMBER:
                    if (student.getRoomNumber().trim().equals(search.trim())) result.add(student);
                    break;
                case PENALITYPOINT:
                    if (student.getPenaltyPoints().trim().equals(search.trim())) result.add(student);
                    break;
                case RC:
                    if (student.getRc().name.trim().equals(search.trim())) result.add(student);
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
