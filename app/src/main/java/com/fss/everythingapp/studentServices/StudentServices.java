package com.fss.everythingapp.studentservices;
import java.applet.Applet;
import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

class StudentServices {
    static ArrayList<Counsellor> counsellors = new ArrayList<>(4);

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

    //placeholder method for data being used by the app
    void finalData() { 
        int[] counsellorTimes = counsellors.get(grade).getTime();
    }

    public void layoutLauncher(Stage stage){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/AppointmentLayout.fxml"));
        AppointmentController apointcontroller = new AppointmentController();
        fxmlLoader.setController(apointcontroller);
        Parent view = null;

        counsellors = fillCounsellors();

        try {
            view = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            view = new Label("A fatal error has occurred.");
        }

        Scene scene = new Scene(view);
        stage.setScene(scene);

        stage.setTitle("Student Services Apointments");
        stage.show();
    }
}
