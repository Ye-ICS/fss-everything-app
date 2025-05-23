package com.fss.everythingapp.fitness;

public class GeneralInfo {
    private String name;
    private int age;
    private double height; // in cm
    static protected double weight; // in kg
    private double bodyfat; // %
    private boolean gender; // true is male, female is false
    //static protected boolean activityLevel; // not active, active, super active
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

    boolean isActive () {
        if (Diet.calories < 1600) {
            return false;
        } else {
            return true;
        }
    }

    void calculateBodyfat () {
        if (gender == true) {
            
             if (bodyfat > 5 && bodyfat < 13.1) {
                athleteFat = true;
            } else if (bodyfat > 13 && bodyfat < 20.1) {
                acceptableFat = true;
            } else if (bodyfat > 20) {
                obesityFat = true;
            } 
        } else {
            if (bodyfat > 13.9 && bodyfat < 20.1) {
                athleteFat = true;
            } else if (bodyfat > 20 && bodyfat < 27.1) {
                acceptableFat = true;
            } else if (bodyfat > 27) {
                obesityFat = true;
            }
        }
    }

    void calculateBmiAndWeight () {
        bmiIndex = (weight) / (Math.sqrt((height / 100)));
        if (bmiIndex < 18.5) {
            underweight = true;
        } else if (bmiIndex > 18.4 && bmiIndex < 25) {
            healthyweight = true;
        } else {
            overweight = true;        
        } 
    }
    
    void calculatePhysique () {
        if (gender == true) {
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
