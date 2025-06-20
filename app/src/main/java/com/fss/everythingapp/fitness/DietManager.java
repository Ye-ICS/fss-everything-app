package com.fss.everythingapp.fitness;

public class DietManager {
    


    void viewDiet() {
        //  view diet
    }

    void editDiet() {
        // edit diet
    }

    public static void newDiet() {
        //  create a new diet
        getBasalMetabloicRate();
        if (Workout.desiredPhysique.equals("skinny")) {
            Diet.calorieTarget = Diet.basalMetabolicRate - 750;
            Diet.proteinTarget = GeneralInfo.weight * 1.8;
            Diet.carbsTarget = (Diet.basalMetabolicRate * 0.35) / 4;
            Diet.fatsTarget = (Diet.basalMetabolicRate / 45);
        } else if (Workout.desiredPhysique.equals("lean")) {
            Diet.calorieTarget = Diet.basalMetabolicRate * 1.1;
            Diet.proteinTarget = GeneralInfo.weight * 2.4;
            Diet.carbsTarget = (Diet.basalMetabolicRate * 0.45) / 4;
            Diet.fatsTarget = GeneralInfo.weight * 0.8;
        } else if (Workout.desiredPhysique.equals("bulk")) {
            Diet.calorieTarget = Diet.basalMetabolicRate * 1.2;
            Diet.proteinTarget = GeneralInfo.weight * 1.8;
            Diet.carbsTarget = (Diet.basalMetabolicRate * 0.5) / 4;
            Diet.fatsTarget = GeneralInfo.weight * 0.7;
        }
           
        }


        static double getBasalMetabloicRate() {
            if (GeneralInfo.isFemale) {
                if (GeneralInfo.isPhysicallyActive) {
                Diet.basalMetabolicRate = (10 * GeneralInfo.weight + 6.25 * GeneralInfo.height - 5 * GeneralInfo.age - 161) * 1.7;
                } else {
                Diet.basalMetabolicRate = (10 * GeneralInfo.weight + 6.25 * GeneralInfo.height - 5 * GeneralInfo.age - 161) * 1.35;
                }
            } else {
                if (GeneralInfo.isPhysicallyActive) {
                 Diet.basalMetabolicRate = (10 * GeneralInfo.weight + 6.25 * GeneralInfo.height - 5 * GeneralInfo.age + 5) * 1.7;
            } else {
                Diet.basalMetabolicRate = (10 * GeneralInfo.weight + 6.25 * GeneralInfo.height - 5 * GeneralInfo.age + 5) * 1.35;
            }
            }
            return Diet.basalMetabolicRate;
        }
}



