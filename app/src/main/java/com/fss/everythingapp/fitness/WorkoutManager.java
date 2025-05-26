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

    

