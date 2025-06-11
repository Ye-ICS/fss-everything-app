package com.fss.everythingapp.calendar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

public class DateManager {

    protected String dateName;
    protected char dateType;

    protected int[] dueDateInfo;
    protected int[] startDateInfo;
    protected int[] endDateInfo;

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
            dateType = parts[0].charAt(0);
            dateName = parts[1];

            if (dateType == 'T') { // If the current date is a task
                String dueDate = parts[2];

                String[] dueDateParts = dueDate.split("/");
                dueDateInfo = new int[5];
                dueDateInfo[0] = Integer.parseInt(dueDateParts[0]);
                dueDateInfo[1] = Integer.parseInt(dueDateParts[1]);
                dueDateInfo[2] = Integer.parseInt(dueDateParts[2]);
                String[] dueTimeParts = (dueDateParts[3]).split(":");
                dueDateInfo[3] = Integer.parseInt(dueTimeParts[0]);
                dueDateInfo[4] = Integer.parseInt(dueTimeParts[1]);

            } else { // If the current date is an event
                String startDate = parts[2];
                String endDate = parts[3];

                startDateInfo = new int[5];
                endDateInfo = new int[5];
                String[] startDateParts = startDate.split("/");
                startDateInfo[0] = Integer.parseInt(startDateParts[0]);
                startDateInfo[1] = Integer.parseInt(startDateParts[1]);
                startDateInfo[2] = Integer.parseInt(startDateParts[2]);
                String[] startTimeParts = (startDateParts[3]).split(":");
                startDateInfo[3] = Integer.parseInt(startTimeParts[0]);
                startDateInfo[4] = Integer.parseInt(startTimeParts[1]);

                String[] endDateParts = endDate.split("/");
                endDateInfo[0] = Integer.parseInt(endDateParts[0]);
                endDateInfo[1] = Integer.parseInt(endDateParts[1]);
                endDateInfo[2] = Integer.parseInt(endDateParts[2]);
                String[] endTimeParts = (endDateParts[3]).split(":");
                endDateInfo[3] = Integer.parseInt(endTimeParts[0]);
                endDateInfo[4] = Integer.parseInt(endTimeParts[1]);

            }
            dateList.add(loadedDate);
            System.out.println(dateList.size());
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

    int[] getDueDateInfo() {
        return this.dueDateInfo;
    }

    int[] getStartDateInfo() {
        return this.startDateInfo;
    }

    int[] getEndDateInfo() {
        return this.endDateInfo;
    }

    ArrayList<DateManager> getDateList() {
        return this.dateList;
    }
}