package com.fss.everythingapp.fitness;

import java.util.Scanner;

public class SleepManager {
    Scanner scanner = new Scanner(System.in);

    void newSleepRoutine() {
        while (SleepSchedule.tSlept > 24 || SleepSchedule.tSlept > 24) {
            System.out.println("What time do you normally go to bed?"); //24 hour clock
            int choice = scanner.nextInt();
            SleepSchedule.tSlept = choice;
            System.out.println("What time do you normally wake up?");
            choice = scanner.nextInt();
            SleepSchedule.tWoke = choice;
            if (SleepSchedule.tSlept > 24 || SleepSchedule.tSlept > 24) {
                System.out.println("Invalid time inputed");
                }
                else if (SleepSchedule.tSlept + 8 >= SleepSchedule.tWoke) {
                    System.out.println("You sleep a healthy amount of hours");
                }
                    else if (SleepSchedule.tSlept >= 20 && SleepSchedule.tWoke >= 20 && SleepSchedule.tSlept <= 9 && SleepSchedule.tWoke <= 9) {
                        System.out.println("\nwe recommend sleeping between the hours of 20:00 and 9:00 to maintain a consistent healthy sleep cycle. \n Perhaps establishing a routine will help with this.");
                        }
                    else {
                
                    }
            //if (/* confirm button code */) {
             //   SleepSchedule.woke = SleepSchedule.tWoke;
             //   SleepSchedule.slept = SleepSchedule.tSlept;
            //}
                
        }           
        //else {
            //System.out.println("You seem to be undersleeping, perhaps adjust your sleep schedule accordingly");
            //System.out.println("\nwe recommend sleeping between the hours of 20:00 and 9:00 to maintain a consistent healthy sleep cycle.");
        //}
        
    }

    void viewSleepRoutine() {
        // view sleeproutine
        
    }

    void editSleepRoutine() {
        // edit sleep routine
    }

    
}
