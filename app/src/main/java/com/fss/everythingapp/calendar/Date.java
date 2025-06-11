package com.fss.everythingapp.calendar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

public class Date extends DateManager {
    protected String dateName;

    protected int dueYear;
    protected int dueMonth;
    protected int dueDay;
    protected int dueHour;
    protected int dueMins;

    protected int startYear;
    protected int startMonth;
    protected int startDay;
    protected int startHour;
    protected int startMins;

    protected int endYear;
    protected int endMonth;
    protected int endDay;
    protected int endHour;
    protected int endMins;

    Date(char paramType, String paramDate) { // 'M' = Month & Year | 'D' = Day, Month & Year

    }

    Date() { // Blank constructor
    }

    // loads all dates with a specific parameter
    protected ArrayList loadAptDates(char paramType, String paramDate) {
        ArrayList<Date> dateList = new ArrayList<Date>();
        String[] paramParts = paramDate.split("/");
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new File(getClass().getResource("/com/fss/everythingapp/calendar/DateList.txt").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return dateList;
        }

        while (scanner.hasNextLine()) {
            Date loadedDate = new Date();

            String line = scanner.nextLine();
            String[] parts = line.split(",");
            dateName = parts[1];

            if (parts[0].charAt(0) == 'T') { // If the current date is a task
                String dueDate = parts[2];

                String[] dueDateParts = dueDate.split("/");
                dueYear = Integer.parseInt(dueDateParts[0]);
                dueMonth = Integer.parseInt(dueDateParts[1]);
                dueDay = Integer.parseInt(dueDateParts[2]);
                String[] dueTimeParts = (dueDateParts[3]).split(":");
                dueHour = Integer.parseInt(dueTimeParts[0]);
                dueMins = Integer.parseInt(dueTimeParts[1]);

                if (paramType == 'M' && Integer.parseInt(paramParts[0]) == dueYear
                        && Integer.parseInt(paramParts[1]) == dueMonth) {
                    dateList.add(loadedDate);
                } else if (paramType == 'D' && Integer.parseInt(paramParts[0]) == dueYear
                        && Integer.parseInt(paramParts[1]) == dueMonth && Integer.parseInt(paramParts[1]) == dueDay) {
                    dateList.add(loadedDate);
                }
            } else { // If the current date is an event
                String startDate = parts[2];
                String endDate = parts[3];

                String[] startDateParts = startDate.split("/");
                startYear = Integer.parseInt(startDateParts[0]);
                startMonth = Integer.parseInt(startDateParts[1]);
                startDay = Integer.parseInt(startDateParts[2]);
                String[] startTimeParts = (startDateParts[3]).split(":");
                startHour = Integer.parseInt(startTimeParts[0]);
                startMins = Integer.parseInt(startTimeParts[1]);

                String[] endDateParts = endDate.split("/");
                endYear = Integer.parseInt(endDateParts[0]);
                endMonth = Integer.parseInt(endDateParts[1]);
                endDay = Integer.parseInt(endDateParts[2]);
                String[] endTimeParts = (endDateParts[3]).split(":");
                endHour = Integer.parseInt(endTimeParts[0]);
                endMins = Integer.parseInt(endTimeParts[1]);

                if (paramType == 'M'
                        && (Integer.parseInt(paramParts[0]) <= startYear && Integer.parseInt(paramParts[0]) >= endYear)
                        && (Integer.parseInt(paramParts[1]) <= startMonth
                                && Integer.parseInt(paramParts[1]) >= endMonth)) {
                    dateList.add(loadedDate);
                } else if (paramType == 'D'
                        && (Integer.parseInt(paramParts[0]) <= startYear && Integer.parseInt(paramParts[0]) >= endYear)
                        && (Integer.parseInt(paramParts[1]) <= startMonth
                                && Integer.parseInt(paramParts[1]) >= endMonth)
                        && (Integer.parseInt(paramParts[2]) <= startDay && Integer.parseInt(paramParts[2]) >= endDay)) {
                    dateList.add(loadedDate);
                }
            }
        }
        scanner.close();
        return dateList;
    }

    String getDateName() {
        return this.dateName;
    }

    int getDueYear() {
        return this.dueYear;
    }

    int getDueMonth() {
        return this.dueMonth;
    }

    int getdueDay() {
        return this.dueDay;
    }

    int getDueHour() {
        return this.dueHour;
    }

    int getDueMins() {
        return this.dueMins;
    }

    int getStartYear() {
        return this.startYear;
    }

    int getStartMonth() {
        return this.startMonth;
    }

    int getStartDay() {
        return this.startDay;
    }

    int getStartHour() {
        return this.startHour;
    }

    int getStartMins() {
        return this.startMins;
    }

    int getEndYear() {
        return this.endYear;
    }

    int getEndMonth() {
        return this.endMonth;
    }

    int getEndDay() {
        return this.endDay;
    }

    int getEndHour() {
        return this.endHour;
    }

    int getEndMins() {
        return this.endMins;
    }

}