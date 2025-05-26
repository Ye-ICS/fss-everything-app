package com.fss.everythingapp.fitness;

public class GeneralInfo {
    private String name;
    private int age;
    static protected double height; // in cm
    static protected double weight; // in kg
    private double bodyfat; // %
    private boolean isMale;
    private boolean isFemale; // true is male, female is false
    //static protected boolean activityLevel; // not active, active, super active
    private boolean athleteFat;
    private boolean acceptableFat;
    static private boolean obesityFat;

    boolean isActive () {
        if (Diet.calBurned < 1600) {
            return false;
        } else {
            return true;
        }
    }

    void calculateBodyfat () {
        if (isMale == true) {
            
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

   

}
