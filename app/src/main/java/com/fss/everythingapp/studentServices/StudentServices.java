package com.fss.everythingapp.studentservices;
import java.applet.Applet;
import java.util.ArrayList;

class StudentServices {
    ArrayList<Counsellor> counsellors = new ArrayList<>(4);
    private void userInfo() {
        //get user name and grade

    }

    private void calendar() {
        counsellors.get(0).getCalendar();
    }

    private void userInput() {
        String input = AppointmentController.time;
        String name = AppointmentController.name;
        int grade = AppointmentController.grade;
    }


}
