package com.fss.everythingapp.calendar;

public class Date {
    private String name;
    private int monthStart;
    private int dayStart;
    private int monthEnd;
    private int dayEnd;
    private int index;

    Date(String name, int monthStart, int dayStart, int monthEnd, int dayEnd, int index) {
        this.name = name;
        this.monthStart = monthStart;
        this.dayStart = dayStart;
        this.monthEnd = monthEnd;
        this.dayEnd = dayEnd;
        this.index = index;
    }

    String getName(){return this.name;}
    int getMonthStart(){return this.monthStart;}
    int getDayStart(){return this.dayStart;}
    int getMonthEnd(){return this.monthEnd;}
    int getDayEnd(){return this.dayEnd;}
    int getIndex(){return this.index;}
}