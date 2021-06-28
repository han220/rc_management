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

    public void setName(String name) {
        this.name = name;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setPenaltyPoints(String penaltyPoints) {
        this.penaltyPoints = penaltyPoints;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setRc(RC rc) {
        this.rc = rc;
    }
}
