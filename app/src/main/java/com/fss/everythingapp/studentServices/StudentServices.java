package com.fss.everythingapp.studentservices;
import java.applet.Applet;
import java.util.ArrayList;

class StudentServices {
    ArrayList<Counsellor> counsellors = new ArrayList<>(4);

    private ArrayList<Counsellor> fillCounsellors(){
        for(int i = 0; i < counsellors.size(); i++){
            Counsellor counsellor = new Counsellor("temp");
            counsellors.add(counsellor);
        }
        return counsellors;
    }
    
    void userGrade() {
        
    }

    //placeholder method for data being used by the app
    void Data() { 
        String name = AppointmentController.name;
        String time = AppointmentController.time;
        int grade = AppointmentController.grade - 9;
        int[] counsellorTimes = counsellors.get(grade).getTime();
        int date = AppointmentController.date;
    }

}
