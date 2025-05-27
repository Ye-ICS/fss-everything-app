package com.fss.everythingapp.fitness;
import java.util.Scanner;

public class DietManager {
    Scanner scanner = new Scanner(System.in);
    
   // boolean isDietHealthy() {
     //   if () {
       //     return false;
        //} else if ()
        //return true;
        // check if diet is healthy
    //}

    void viewDiet() {
        //  view diet
    }

    void editDiet() {
        // edit diet
    }

    void newDiet() {
        //  create a new diet
        if (Workout.desiredPhysique == "Skinny" && WorkoutManager.getAverageBuild() || WorkoutManager.getBigBuild() || WorkoutManager.getFitBuild()) {
            System.out.println("Protein intake daily: around" + GeneralInfo.weight * 1.8 + ".");
            System.out.println("Carbs intake daily: 45-55% of total daily calories should be carbs.");
            System.out.println("Fats intake daily: 20-30 % of total daily calories should be fats.");
        } else if (Workout.desiredPhysique == "Lean" || Workout.desiredPhysique == "Bulk" && WorkoutManager.getSkinnyBuild() || WorkoutManager.getAverageBuild()) {
            System.out.println("Add 250-500 daily calorie intake.");
            System.out.println("Protein intake daily: around " + GeneralInfo.weight * 2.4 + ".");
            System.out.println("Carbs intake daily: around " + GeneralInfo.weight * 6.6 + ".");
            System.out.println("Fats intake daily: around " + GeneralInfo.weight * 0.8 + ".");
        }
            
        }
        
        
    }

    


