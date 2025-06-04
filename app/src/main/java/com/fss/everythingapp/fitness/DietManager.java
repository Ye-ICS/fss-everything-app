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

    static public void newDiet() {
        //  create a new diet
        if (Workout.desiredPhysique == "skinny") {
            Diet.calTarget = (GeneralInfo.weight * 15) - 750;
            Diet.proteinTarget = GeneralInfo.weight * 1.8;
            Diet.carbsTarget = (Diet.calTarget * 0.55) / 4; 
            Diet.fatsTarget = (Diet.calTarget / 45);
        } else if (Workout.desiredPhysique == "lean") {
            Diet.calTarget = (GeneralInfo.weight * 15) + (GeneralInfo.weight * 15) * 0.1;
            Diet.proteinTarget = GeneralInfo.weight * 2.4;
            Diet.carbsTarget = GeneralInfo.weight * 6.6;
            Diet.fatsTarget = GeneralInfo.weight * 0.8;
        } else if (Workout.desiredPhysique == "bulk") {
            Diet.calTarget = (GeneralInfo.weight * 15) + (GeneralInfo.weight * 15) * 0.2;
            Diet.proteinTarget = GeneralInfo.weight * 1.8;
            Diet.carbsTarget = GeneralInfo.weight * 5;
            Diet.fatsTarget = GeneralInfo.weight * 0.7;
        }
            
        }
    }
        


