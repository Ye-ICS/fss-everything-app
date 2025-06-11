package com.fss.everythingapp.fitness;
import java.util.Timer;
import java.util.TimerTask;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SleepTimer {

     public static String formattedDate;

     public static void getDate(String[] args) {
      LocalDateTime myDateObj = LocalDateTime.now();
    System.out.println("Before formatting: " + myDateObj);
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    formattedDate = myDateObj.format(myFormatObj);
    System.out.println("After formatting: " + formattedDate);
    }

    void ButtonStart(LocalDateTime myDateObj) {
        // Start the sleep timer
        SleepSchedule.slept = myDateObj.getHour();
    }

    void ButtonStop(LocalDateTime myDateObj) {
        // Stop the sleep timer
    SleepSchedule.woke = myDateObj.getHour();
    SleepSchedule.timeslept = SleepSchedule.slept - SleepSchedule.woke;
    if (SleepSchedule.timeslept < 0) {
        SleepSchedule.timeslept = SleepSchedule.timeslept + 24;
    }
    }

    void ButtonReset() {
        // Reset the sleep timer
        SleepSchedule.slept = 0;
        SleepSchedule.woke = 0;
        SleepSchedule.timeslept = 0;
    }

    void ButtonSave() {
        // Save the sleep timer data
        
    }

    void ButtonLoad() {
        // Load the sleep timer data
    }

}
