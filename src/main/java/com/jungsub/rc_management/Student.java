package com.jungsub.rc_management;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Student implements Serializable {
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

    public Student(String name, int studentId, int roomNumber, int penaltyPoints, String semester, String rc, Object createdAt) throws ParseException {
        this.name = name;
        this.studentId = String.valueOf(studentId);
        this.roomNumber = String.valueOf(roomNumber);
        this.penaltyPoints = String.valueOf(penaltyPoints);
        this.semester = semester;
        this.createdAt = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.createdAt = new Timestamp(sdf.parse(String.valueOf(createdAt)).getTime());
        this.rc = null;
        for(RC r : RC.values()) {
            if(r.name().equals(rc)) {
                this.rc = r;
                break;
            }
        }
    }

//    public Student(Object name, Object studentId, Object roomNumber,
//                   Object penaltyPoints, Object semester, Object rc, Object createdAt) {
//        this.name = String.valueOf(name);
//        this.name = String.valueOf(studentId);
//        this.name = String.valueOf(roomNumber);
//        this.name = String.valueOf(penaltyPoints);
//        this.name = String.valueOf(semester);
//        this.name = String.valueOf(rc);
//        this.createdAt = (Timestamp) createdAt;
//    }

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
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(createdAt.getTime()));
//        c.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        c.add(Calendar.HOUR, 9);
        return new Timestamp(c.getTime().getTime());
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
