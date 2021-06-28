package com.jungsub.rc_management;

import com.jungsub.rc_management.assets.CommandLineTable;

import java.util.ArrayList;

public class Menu {
    public static void getMenu() {
        System.out.println("============= RC MANAGEMENT =============");
        System.out.println("1. 새로운 학생 추가");
        System.out.println("2. 기존 학생 확인");
        System.out.println("3. 학생 삭제");
        System.out.println("4. 학생 정보 수정");
        System.out.println("5. 모든 학생 보기");
        System.out.println("0. 나가기");
        System.out.println("============= RC MANAGEMENT =============");
    }

    public static int getMenuId() {
        String number;
        int result;
        while (true) {
            System.out.print("메뉴를 입력해주세요 > ");
            try {
                number = RCManagement.input.nextLine();
                result = Integer.parseInt(number);
                break;
            } catch (NumberFormatException e) {
                System.out.println("번호가 아닙니다.");
            }
        }
        return result;
    }

    // 1번 새로운 학생 추가.
    public static Student addNewEntry() {
        String name, studentId, roomNumber, penaltyPoints;
        int rc;

        System.out.print("Name? ");
        name = RCManagement.input.nextLine();

        System.out.print("StudentId? ");
        studentId = RCManagement.input.nextLine();

        System.out.print("RoomNumber? ");
        roomNumber = RCManagement.input.nextLine();

        System.out.print("PenaltyPoints? ");
        penaltyPoints = RCManagement.input.nextLine();

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
        
        return new Student(name, studentId, roomNumber, penaltyPoints, "2021-2", RC.values()[rc]);
    }

    // 2번 기존 학생 확인
    public static Student checkStudent(ArrayList<Student> data) {
        System.out.print("학번 입력> ");
        RCManagement.input.next(); // Clear buffer
        String studentId = RCManagement.input.nextLine();

        Student student = Student.getStudent(data, studentId);

        if (student == null) {
            System.out.println("경고! 학생을 찾을 수 없습니다.");
        }

        // 출력
        CommandLineTable st = new CommandLineTable();
        st.setShowVerticalLines(true);
        st.setHeaders("이름", "학번", "기숙사", "방번호", "벌점", "학기", "생성시간");

        st.addRow(student.getName(), student.getStudentId(), student.getRc().name, student.getRoomNumber(), student.getPenaltyPoints(), student.getSemester(), student.getCreatedAt().toString());
        return student;
    }

    // 3번 학생 삭제
    public static void deleteStudent(ArrayList<Student> data) {
        
        System.out.print("학번 입력> ");
        String studentId = RCManagement.input.nextLine();

        Student student = Student.getStudent(data, studentId);

        if (student == null) {
            System.out.println("경고! 학생을 찾을 수 없습니다.");
        } else {
            data.remove(student);
            System.out.println("삭제 완료!");
        }
        
    }

    // 4번 학생 정보수정.
    public static void editStudent(ArrayList<Student> data) {
        
        System.out.print("학번 입력> ");
        String studentId = RCManagement.input.nextLine();

        Student student = Student.getStudent(data, studentId);

        if (student == null) {
            System.out.println("경고! 학생을 찾을 수 없습니다.");
        } else {
            student.edit();
        }
        
    }

    // 5번

    public static void viewStudents(ArrayList<Student> data) {
        CommandLineTable st = new CommandLineTable();
        st.setShowVerticalLines(true);
        st.setHeaders("이름", "학번", "기숙사", "방번호", "벌점", "학기", "생성시간");
        for (Student s : data) {
            st.addRow(s.getName(), s.getStudentId(), s.getRc().name, s.getRoomNumber(), s.getPenaltyPoints(), s.getSemester(), s.getCreatedAt().toString());
        }
        st.print();
    }

    //
}
