package com.fss.everythingapp.fitness;

public class DietManager {
    
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

    public static void newDiet() {
        bmrCalculation();
        //  create a new diet
        if (Workout.desiredPhysique.equals("skinny")) {
            Diet.calorieTarget = Diet.bmr - 750;
            Diet.proteinTarget = GeneralInfo.weight * 1.8;
            Diet.carbsTarget = (Diet.bmr * 0.55) / 4;
            Diet.fatsTarget = (Diet.bmr / 45);
        } else if (Workout.desiredPhysique.equals("lean")) {
            Diet.calorieTarget = Diet.bmr * 1.1;
            Diet.proteinTarget = GeneralInfo.weight * 2.4;
            Diet.carbsTarget = (Diet.bmr * 0.45) / 4;
            Diet.fatsTarget = GeneralInfo.weight * 0.8;
        } else if (Workout.desiredPhysique.equals("bulk")) {
            Diet.calorieTarget = Diet.bmr * 1.2;
            Diet.proteinTarget = GeneralInfo.weight * 1.8;
            Diet.carbsTarget = Diet.bmr * 1.65;
            Diet.fatsTarget = GeneralInfo.weight * 0.7;
        }
           
        }


        static void bmrCalculation() {
            if (GeneralInfo.isFemale) {
                if (GeneralInfo.isActive) {
                Diet.bmr = (10 * GeneralInfo.weight + 6.25 * GeneralInfo.height - 5 * GeneralInfo.age - 161) * 1.7;
                } else {
                Diet.bmr = (10 * GeneralInfo.weight + 6.25 * GeneralInfo.height - 5 * GeneralInfo.age - 161) * 1.35;
                }
            } else {
                if (GeneralInfo.isActive) {
                 Diet.bmr = (10 * GeneralInfo.weight + 6.25 * GeneralInfo.height - 5 * GeneralInfo.age + 5) * 1.7;
            } else {
                Diet.bmr = (10 * GeneralInfo.weight + 6.25 * GeneralInfo.height - 5 * GeneralInfo.age + 5) * 1.35;
            }
        }
    }
}



