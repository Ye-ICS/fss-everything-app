package com.fss.everythingapp.fitness;
public class WorkoutManager {

    static private boolean skinnyBuild;
    static private boolean averageBuild;
    static private boolean fitBuild;
    static private boolean bigBuild;
    private boolean athleteFat;
    private boolean acceptableFat;
    static private boolean underweight;
    static private boolean healthyweight;
    static private boolean overweight;
    static private boolean obesityFat;
    private double bmiIndex;

    void newRoutine() {
        // create a new routine
        if (Workout.desiredPhysique == "Skinny" && WorkoutManager.getAverageBuild() || WorkoutManager.getFitBuild()) {
            System.out.println("Do 30 minutes of cardio like running/jogging everyday.");
            System.out.println("Do 2-3 sets of things like squats, push-ups, and lunges everyday.");
            System.out.println("Do 20 minutes of repeated short bursts of things like sprints, burpees, and pull ups.");
        } else if (Workout.desiredPhysique == "Lean" || Workout.desiredPhysique == "Bulk" && WorkoutManager.getSkinnyBuild() || WorkoutManager.getAverageBuild()) {
            
        }
    }

    void viewRoutine() {
        // view routine
    }

    void editRoutine() {
        // edit routine
    }
    

    void calculateBmiAndWeight () {
        bmiIndex = (GeneralInfo.weight) / (Math.sqrt((GeneralInfo.height / 100)));
        if (bmiIndex < 18.5) {
            underweight = true;
        } else if (bmiIndex > 18.4 && bmiIndex < 25) {
            healthyweight = true;
        } else {
            overweight = true;        
        } 
    }
    
    void calculatePhysique () {
            if (underweight == true && athleteFat == true) {
                fitBuild = true;
            } else if (underweight == true && acceptableFat) {
                averageBuild = true;
            } else if (underweight == true && obesityFat) {
                skinnyBuild = true;
            } else if (healthyweight && athleteFat) {
                fitBuild = true;
            } else if (healthyweight && acceptableFat) {
                averageBuild = true;
            } else if (healthyweight && obesityFat) {
                averageBuild = true;
            } else if (overweight && athleteFat) {
                fitBuild = true;
            } else if (overweight && acceptableFat) {
                bigBuild = true;
            } else if (overweight && obesityFat) {
                bigBuild = true;
            }
        }
    


    static boolean getSkinnyBuild () {
       if (skinnyBuild == true) {
        return true;
       } else {
        return false;
       }
    }

    static boolean getAverageBuild () {
        if (averageBuild == true) {
            return true;
        } else {
            return false;
        }
    }

    static boolean getFitBuild () {
        if (fitBuild == true) {
            return true;
        } else {
            return false;
        }
    }

    static boolean getBigBuild () {
        if (bigBuild == true) {
            return true;
        } else {
            return false;
        }
    }

}

    

