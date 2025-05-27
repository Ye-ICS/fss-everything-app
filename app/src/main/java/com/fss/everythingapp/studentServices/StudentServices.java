package com.fss.everythingapp.studentservices;
import java.applet.Applet;
import java.util.ArrayList;

class StudentServices {
    ArrayList<Counsellor> counsellors = new ArrayList<>();

    private ArrayList<Counsellor> fillCounsellors(){
        for(int i = 0; i < counsellors.size(); i++){
            Counsellor counsellor = new Counsellor("temp", "temp");
            counsellors.add(counsellor);
        }
        return counsellors;
    }

    private void userName() {
        String name = AppointmentController.name;
    }

    private void userGrade() {
        int grade = AppointmentController.grade - 9;
        

    }

    private void userTime() {
        String time = AppointmentController.time;
    }

    private void userDate() {
        int date = AppointmentController.date;
    }


}
