package com.fss.everythingapp.fitness;

import java.util.Scanner;

public class SleepManager {
    Scanner scanner = new Scanner(System.in);

    void newSleepRoutine() {
        System.out.println("What time did you go to bed?"); //24 hour clock
        SleepSchedule.slept = scanner.nextInt();
        System.out.println("What time did you wake up?");
        SleepSchedule.woke = scanner.nextInt();
        if (SleepSchedule.slept + 8 >= SleepSchedule.woke) {
            System.out.println("You sleep a healthy amount of hours");
            if (SleepSchedule.slept >= 20 && SleepSchedule.woke >= 20 && SleepSchedule.slept <= 9 && SleepSchedule.woke <= 9) {
                System.out.println("\nwe recommend sleeping between the hours of 20:00 and 9:00 to maintain a consistent healthy sleep cycle.");
                }
            else {
                
            }
        }           
        else {
            System.out.println("You seem to be undersleeping, perhaps adjust your sleep schedule accordingly");
            System.out.println("\nwe recommend sleeping between the hours of 20:00 and 9:00 to maintain a consistent healthy sleep cycle.");
        }
        
    }

    void viewSleepRoutine() {
        // view sleeproutine
    }

    void editSleepRoutine() {
        // edit sleep routine
    }

    
}
