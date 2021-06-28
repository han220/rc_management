package com.jungsub.rc_management;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class Student {
    private String name, studentId, roomNumber, penaltyPoints, semester;
    private Timestamp createdAt;
    private RC rc;

    public Student(String name, String studentId, String roomNumber, String penaltyPoints, String semester, RC rc) {
        this.name = name;
        this.studentId = studentId;
        this.roomNumber = roomNumber;
        this.penaltyPoints = penaltyPoints;
        this.semester = semester;
        this.rc = rc;
        createdAt = new Timestamp(new Date().getTime());
    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getPenaltyPoints() {
        return penaltyPoints;
    }

    public String getSemester() {
        return semester;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public RC getRc() {
        return rc;
    }

    public void edit() {
        int rc;
        System.out.print("Name? ");
        this.name = RCManagement.input.nextLine();

        System.out.print("StudentId? ");
        this.studentId = RCManagement.input.nextLine();

        System.out.print("RoomNumber? ");
        this.roomNumber = RCManagement.input.nextLine();

        System.out.print("PenaltyPoints? ");
        this.penaltyPoints = RCManagement.input.nextLine();

        System.out.println("Select RC");
        for (int i = 0; i < RC.values().length; i++) {
            System.out.println(i + 1 + ". " + RC.values()[i].name);
        }
        while (true) {
            try {
                System.out.print("숫자 입력? ");
                rc = Integer.parseInt(RCManagement.input.nextLine());
                if (rc > RC.values().length) {
                    System.out.println("범위 초과.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("숫자가 아닙니다.");
            }
        }
        this.rc = RC.values()[rc - 1];
    }

    public static Student getStudent(ArrayList<Student> data, String studentId) {
        for(Student student : data) {
            if(student.getStudentId().equals(studentId)) return student;
        }
        return null;
    }


//    public void print() {
//        System.out.println()
//    }
}
