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

import javafx.scene.control.ScrollPane;

public class DateManager {
    protected ArrayList<Date> dateList;

    DateManager() { // Superclass constructor
    }

    DateManager(char paramType, LocalDate paramDate) {
        loadAptDates(paramType, paramDate);
    }

    DateManager(ScrollPane dateListPane) {
        loadDates();
    }

    protected ArrayList<Date> loadDates() { // Loads all dates
        dateList = new ArrayList<Date>();
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new File(getClass().getResource("/com/fss/everythingapp/calendar/DateList.txt").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return dateList;
        }

        while (scanner.hasNextLine()) {

            String line = scanner.nextLine();
            String[] parts = line.split(",");
            char dateType = parts[0].charAt(0);
            
            Date loadedDate;
            if (dateType == 'T') {
                loadedDate = new Task();
                loadedDate.dueDate = LocalDateTime.parse(parts[2]);
            } else {
                loadedDate = new Event();
                loadedDate.startDate = LocalDateTime.parse(parts[2]);
                loadedDate.endDate = LocalDateTime.parse(parts[3]);
            }
            loadedDate.dateName = parts[1];
            dateList.add(loadedDate);
        }
        scanner.close();
        return dateList;
    }

    protected ArrayList<Date> loadAptDates(char paramType, LocalDate paramDate) {
        dateList = new ArrayList<Date>();
        Scanner scanner;

        System.out.println(paramType);
        System.out.println(paramDate.toString());

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
                    System.out.println(loadedDate.toString());
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

                int startWeek = loadedDate.startDate.get(weekOfYear);
                int endWeek = loadedDate.endDate.get(weekOfYear);

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

    ArrayList<Date> getDateList() {
        return this.dateList;
    }
}