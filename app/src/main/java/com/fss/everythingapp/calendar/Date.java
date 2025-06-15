package com.fss.everythingapp.calendar;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;
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

    Date(char paramType, LocalDate paramDate) { // 'M' = Month & Year | 'W' = Week & Year | 'D' = DayOfYear & Year
        loadAptDates(paramType, paramDate);
    }

    Date(ArrayList<Date> dateList) { // Blank constructor
    }

    // loads all dates with a specific parameter
    protected ArrayList loadAptDates(char paramType, LocalDate paramDate) {
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

            TemporalField weekOfYear = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
            int paramWeek = paramDate.get(weekOfYear);

            if (loadedDate.dateType == 'T') { // If the current date is a task
                loadedDate.dueDate = LocalDateTime.parse(parts[2]);

                int dueWeek = loadedDate.dueDate.get(weekOfYear);

                if (paramType == 'M'
                        && paramDate.getYear() == loadedDate.dueDate.getYear()
                        && paramDate.getMonthValue() == loadedDate.dueDate.getMonthValue()) {
                    dateList.add(loadedDate);
                } else if (paramType == 'W'
                        && paramDate.getYear() == loadedDate.dueDate.getYear()
                        && paramWeek == dueWeek) {
                    dateList.add(loadedDate);
                } else if (paramType == 'D'
                        && paramDate.getYear() == loadedDate.dueDate.getYear()
                        && paramDate.getDayOfYear() == loadedDate.dueDate.getDayOfYear()) {
                    dateList.add(loadedDate);
                }
            } else { // If the current date is an event
                loadedDate.startDate = LocalDateTime.parse(parts[2]);
                loadedDate.endDate = LocalDateTime.parse(parts[3]);

                int startWeek = loadedDate.dueDate.get(weekOfYear);
                int endWeek = loadedDate.dueDate.get(weekOfYear);

                if (paramType == 'M'
                        && paramDate.getYear() >= loadedDate.startDate.getYear()
                        && paramDate.getYear() <= loadedDate.endDate.getYear()
                        && paramDate.getMonthValue() >= loadedDate.startDate.getMonthValue()
                        && paramDate.getMonthValue() <= loadedDate.endDate.getMonthValue()) {
                    dateList.add(loadedDate);
                } else if (paramType == 'W'
                        && paramDate.getYear() >= loadedDate.startDate.getYear()
                        && paramDate.getYear() <= loadedDate.endDate.getYear()
                        && paramWeek >= startWeek
                        && paramWeek <= endWeek) {
                    dateList.add(loadedDate);
                } else if (paramType == 'D'
                        && paramDate.getYear() >= loadedDate.startDate.getYear()
                        && paramDate.getYear() <= loadedDate.endDate.getYear()
                        && paramDate.getDayOfYear() >= loadedDate.startDate.getDayOfYear()
                        && paramDate.getDayOfYear() <= loadedDate.endDate.getDayOfYear()) {
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