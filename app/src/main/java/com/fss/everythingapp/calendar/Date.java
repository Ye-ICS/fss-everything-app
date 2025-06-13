package com.fss.everythingapp.calendar;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Date {
    protected String dateName;
    protected char dateType;

    protected LocalDateTime dueDate;
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;

    private ArrayList<Date> dateList;

    Date() { // Not sure why we need this, but our code is broken without it ¯\_(ツ)_/¯
    }

    Date(char paramType, LocalDateTime paramDate) { // 'M' = Month & Year | 'D' = Day, Month & Year

    }

    Date(ArrayList<Date> dateList) { // Blank constructor
    }

    // loads all dates with a specific parameter
    protected ArrayList loadAptDates(char paramType, LocalDateTime paramDate) {
        ArrayList<Date> dateList = new ArrayList<Date>();
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new File(getClass().getResource("/com/fss/everythingapp/calendar/DateList.txt").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return dateList;
        }

        while (scanner.hasNextLine()) {
            Date loadedDate = new Date(dateList);

            String line = scanner.nextLine();
            String[] parts = line.split(",");
            loadedDate.dateType = parts[0].charAt(0);
            loadedDate.dateName = parts[1];

            if (loadedDate.dateType == 'T') { // If the current date is a task
                loadedDate.dueDate = LocalDateTime.parse(parts[2]);

                if (paramType == 'M'
                        && paramDate.getYear() == loadedDate.dueDate.getYear()
                        && paramDate.getMonthValue() == loadedDate.dueDate.getMonthValue()) {
                    dateList.add(loadedDate);
                } else if (paramType == 'D'
                        && paramDate.getYear() == loadedDate.dueDate.getYear()
                        && paramDate.getMonthValue() == loadedDate.dueDate.getMonthValue()
                        && paramDate.getDayOfMonth() == loadedDate.dueDate.getDayOfMonth()) {
                    dateList.add(loadedDate);
                }
            } else { // If the current date is an event
                loadedDate.startDate = LocalDateTime.parse(parts[2]);
                loadedDate.endDate = LocalDateTime.parse(parts[3]);

                if (paramType == 'M'
                        && paramDate.getYear() <= loadedDate.startDate.getYear()
                        && paramDate.getYear() <= loadedDate.endDate.getYear()
                        && paramDate.getMonthValue() >= loadedDate.startDate.getMonthValue()
                        && paramDate.getMonthValue() >= loadedDate.endDate.getMonthValue()) {
                    dateList.add(loadedDate);
                } else if (paramType == 'D'
                        && paramDate.getYear() <= loadedDate.startDate.getYear()
                        && paramDate.getYear() <= loadedDate.endDate.getYear()
                        && paramDate.getMonthValue() >= loadedDate.startDate.getMonthValue()
                        && paramDate.getMonthValue() >= loadedDate.endDate.getMonthValue()
                        && paramDate.getDayOfMonth() <= loadedDate.startDate.getDayOfMonth()
                        && paramDate.getDayOfMonth() <= loadedDate.endDate.getDayOfMonth()) {
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

    char getDateType() {
        return this.dateType;
    }

    LocalDateTime getDueDate() {
        return this.dueDate;
    }

    LocalDateTime getStartDate() {
        return this.startDate;
    }

    LocalDateTime getEndDate() {
        return this.endDate;
    }

    ArrayList<Date> getDateList() {
        return this.dateList;
    }

}