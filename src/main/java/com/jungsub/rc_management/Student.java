package com.jungsub.rc_management;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Student implements Serializable {
    private String name, semester, rc;
    private int studentId, roomNumber, penaltyPoints;
    Date date;

    public Student(String name, String studentId, String roomNumber, String penaltyPoints, String semester, RC rc) {
        this.name = name;
        this.studentId = Integer.parseInt(studentId);
        this.roomNumber = Integer.parseInt(roomNumber);
        this.penaltyPoints = Integer.parseInt(penaltyPoints);
        this.semester = semester;
        this.rc = rc.toString();
    }

    public Student(String name, String semester, String rc, int studentId, int roomNumber, int penaltyPoints, Date date) {
        this.name = name;
        this.semester = semester;
        this.rc = rc;
        this.studentId = studentId;
        this.roomNumber = roomNumber;
        this.penaltyPoints = penaltyPoints;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getRc() {
        return rc;
    }

    public RC RC() {
        for(RC rc : RC.values())
            if(rc.toString().equals(this.rc)) return rc;
      return null;
    }

    public void setRc(String rc) {
        this.rc = rc;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getPenaltyPoints() {
        return penaltyPoints;
    }

    public void setPenaltyPoints(int penaltyPoints) {
        this.penaltyPoints = penaltyPoints;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
