package com.fss.everythingapp.calendar;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class DateManager {

    protected String dateName;
    protected char dateType;

    protected LocalDateTime dueDate;
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;

    private ArrayList<DateManager> dateList;

    DateManager() {
        loadDates();
    }

    DateManager(ArrayList<DateManager> dateList) { // Blank constructor
    }

    protected ArrayList loadDates() { // Loads all dates
        dateList = new ArrayList<DateManager>();
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new File(getClass().getResource("/com/fss/everythingapp/calendar/DateList.txt").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return dateList;
        }

        while (scanner.hasNextLine()) {

            DateManager loadedDate = new DateManager(dateList);

            String line = scanner.nextLine();
            String[] parts = line.split(",");
            loadedDate.dateType = parts[0].charAt(0);
            loadedDate.dateName = parts[1];

            if (loadedDate.dateType == 'T') {
                loadedDate.dueDate = LocalDateTime.parse(parts[2]);
            } else {
                loadedDate.startDate = LocalDateTime.parse(parts[2]);
                loadedDate.endDate = LocalDateTime.parse(parts[3]);

            }
            dateList.add(loadedDate);
        }
        scanner.close();
        return dateList;
    }

    void shareDate() { // User can share dates to other users
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

    ArrayList<DateManager> getDateList() {
        return this.dateList;
    }
}