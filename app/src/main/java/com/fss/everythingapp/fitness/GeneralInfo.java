package com.fss.everythingapp.fitness;

public class GeneralInfo {
static public String name;
    static public int age;
    static public double height; // in cm
    static public double weight; // in kg
    static public double bodyfat; // %
    static public boolean isMale;
    static public boolean isFemale; // true is male, female is false
    //static protected boolean activityLevel; // not active, active, super active
    private boolean athleteFat;
    private boolean acceptableFat;
    static private boolean obesityFat;
    public static int bodyFat;
    public static boolean isPhysicallyActive = false; //true means person is active, false is opposite

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
