package com.fss.everythingapp.fitness;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class SleepTimer {
     public static String formattedDate;

     

     public static void getDate(String[] args) {
        LocalDateTime myDateObj = LocalDateTime.now();
    System.out.println("Before formatting: " + myDateObj);
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    formattedDate = myDateObj.format(myFormatObj);
    System.out.println("After formatting: " + formattedDate);
        System.out.println("This runs every second");
      }


    void ButtonStart(LocalDateTime myDateObj) {
        // Start the sleep timer
        SleepSchedule.timeWentToSleep = myDateObj.getHour();
    }

    void ButtonStop(LocalDateTime myDateObj) {
        // Stop the sleep timer
    SleepSchedule.wokeUpTime = myDateObj.getHour();
    SleepSchedule.hoursSlept = (double) (SleepSchedule.timeWentToSleep - SleepSchedule.wokeUpTime);
    if (SleepSchedule.hoursSlept < 0) {
        SleepSchedule.hoursSlept = SleepSchedule.hoursSlept + 24;
    }
    }

    void ButtonReset() {
        // Reset the sleep timer
        SleepSchedule.timeWentToSleep = 0;
        SleepSchedule.wokeUpTime = 0;
        SleepSchedule.hoursSlept = 0;
    }

    void ButtonSave() {
        // Save the sleep timer data
        
    }

    void ButtonLoad() {
        // Load the sleep timer data
    }

}
