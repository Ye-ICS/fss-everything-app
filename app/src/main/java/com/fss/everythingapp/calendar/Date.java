package com.fss.everythingapp.calendar;

public class Date extends DateManager {
    protected String dateName;
    protected char dateType;

    protected int[] dueDateInfo;
    protected int[] startDateInfo;
    protected int[] endDateInfo;

    Date(String dateName, char dateType, int[] dueDateInfo) { // Blank
        this.dateName = dateName;
        this.dateType = dateType;
        this.dueDateInfo = dueDateInfo;
        this.startDateInfo = startDateInfo;
        this.endDateInfo = endDateInfo; // constructor
    }

    // loads all dates with a specific parameter

    String getDateName() {
        return this.dateName;
    }

    char getDateType() {
        return this.dateType;
    }

    int[] getDueDateInfo() {
        return this.dueDateInfo;
    }

    int[] getStartDateInfo() {
        return this.startDateInfo;
    }

    int[] getEndDateInfo() {
        return this.endDateInfo;
    }

}