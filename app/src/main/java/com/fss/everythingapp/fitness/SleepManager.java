package com.fss.everythingapp.fitness;

import java.util.Scanner;

public class SleepManager {
    Scanner scanner = new Scanner(System.in);

    public static void newSleepRoutine() {
       if (GeneralInfo.age > 14 && GeneralInfo.age < 19) {
        SleepSchedule.hoursSleptTarget = 8;
       } else if (GeneralInfo.age > 18) {
        SleepSchedule.hoursSleptTarget = 7;
       }
                
    }
            

    void viewSleepRoutine() {
        // view sleeproutine
        
    }

    void editSleepRoutine() {
        // edit sleep routine
    }

    
}
