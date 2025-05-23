package com.fss.everythingapp.fitness;
import javafx.application.Application;
import java.util.Scanner;

public class FalconFitnessMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        boolean questionaire = false;
        
        if (questionaire == false) {
            // run questionaire with UI when finished
        while (running && questionaire == true) {
            System.out.println("Welcome to Falcon Fitness!");
            System.out.println("1. Workout Manager");
            System.out.println("2. Sleep Manager");
            System.out.println("3. Diet Manager");
            System.out.println("4. Exit");
            System.out.print("Please select an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // call WorkoutManager methods
                case 2:
                    // call SleepSchedule methods
                case 3:
                    // call DietManager method
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}


    
}