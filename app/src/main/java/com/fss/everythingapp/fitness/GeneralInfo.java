package com.fss.everythingapp.fitness;

import java.util.List;
import java.util.Scanner;

import java.nio.file.Files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;

public class GeneralInfo {


    public static void writeToFile () throws IOException{
         PrintWriter writer = new PrintWriter(new FileWriter("UserInfo.txt"));
            writer.println();
            writer.print(name);
            writer.println();
            writer.print(age);
            writer.println();
            writer.print(height);
            writer.println();
            writer.print(weight);
            writer.println();
            writer.print(bodyfat);
            writer.println();
            writer.print(isMale);
            writer.println();
            writer.print(isFemale);
            writer.println();
            writer.print(isPhysicallyActive);
            writer.println();
            writer.println();
            writer.println();
            writer.print(SleepSchedule.hoursSlept);
            writer.println();
            writer.print(SleepSchedule.timeWentToSleep);
            writer.println();
            writer.print(SleepSchedule.wokeUpTime);
            writer.println();
            writer.print(SleepSchedule.hoursSleptTarget);
            writer.println();
            writer.println();
            writer.println();
            writer.print(Diet.caloriesEaten);
            writer.println();
            writer.print(Diet.calorieTarget);
            writer.println();
            writer.print(Diet.proteinEaten);
            writer.println();
            writer.print(Diet.carbsEaten);
            writer.println();
            writer.print(Diet.fatsEaten);
            writer.println();
            writer.print(Diet.proteinTarget);
            writer.println();
            writer.print(Diet.carbsTarget);
            writer.println();
            writer.print(Diet.fatsTarget);
            writer.println();
            writer.print(Diet.caloriesEaten);
            writer.println();
            writer.print(Diet.basalMetabolicRate);
            writer.close();
            
    }

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
