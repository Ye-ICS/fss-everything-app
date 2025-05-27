package com.fss.everythingapp.studentservices;
import java.applet.Applet;
import java.util.ArrayList;

class StudentServices {
    static ArrayList<Counsellor> counsellors = new ArrayList<>();

    private ArrayList<Counsellor> fillCounsellors(){
        for(int i = 0; i < counsellors.size(); i++){
            Counsellor counsellor = new Counsellor("temp", "temp");
            counsellors.add(counsellor);
        }
        return counsellors;
    }

    int grade;

    private void userName() {
        String name = AppointmentController.name;
    }

    void userGrade() {
        grade = AppointmentController.grade - 9;
        

    }

    private void userTime() {
        String time = AppointmentController.time;
    }

    private void userDate() {
        int date = AppointmentController.date;
    }

    //placeholder method for data being used by the app
    void finalData() { 
        int[] counsellorTimes = counsellors.get(grade).getTime();
    }

}
