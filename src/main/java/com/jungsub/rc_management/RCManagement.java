package com.jungsub.rc_management;

import java.util.ArrayList;

public class RCManagement {
    private static ArrayList<Student> datas = new ArrayList<>();
    public static void main(String[] args) {
        int menu = -1;
        while(menu != 0) {
            Menu.getMenu();
            menu = Menu.getMenuId();

            switch(menu) {
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
            }
        }

    }
}
