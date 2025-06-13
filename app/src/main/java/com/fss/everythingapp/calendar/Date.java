package com.fss.everythingapp.calendar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

public class Date {
    protected String dateName;
    protected char dateType;

    protected int[] dueDateInfo;
    protected int[] startDateInfo;
    protected int[] endDateInfo;

    private ArrayList<Date> dateList;

    Date() { // Not sure why we need this, but our code is broken without it ¯\_(ツ)_/¯
    }

    Date(char paramType, String paramDate) { // 'M' = Month & Year | 'D' = Day, Month & Year

    }

    Date(ArrayList<Date> dateList) { // Blank constructor
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
            Date loadedDate = new Date(dateList);

            String line = scanner.nextLine();
            String[] parts = line.split(",");
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

                if (paramType == 'M' && Integer.parseInt(paramParts[0]) == dueDateInfo[0]
                        && Integer.parseInt(paramParts[1]) == dueDateInfo[1]) {
                    dateList.add(loadedDate);
                } else if (paramType == 'D' && Integer.parseInt(paramParts[0]) == dueDateInfo[0]
                        && Integer.parseInt(paramParts[1]) == dueDateInfo[1]
                        && Integer.parseInt(paramParts[1]) == dueDateInfo[2]) {
                    dateList.add(loadedDate);
                }
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

                if (paramType == 'M'
                        && (Integer.parseInt(paramParts[0]) <= startDateInfo[0]
                                && Integer.parseInt(paramParts[0]) >= endDateInfo[0])
                        && (Integer.parseInt(paramParts[1]) <= startDateInfo[1]
                                && Integer.parseInt(paramParts[1]) >= endDateInfo[1])) {
                    dateList.add(loadedDate);
                } else if (paramType == 'D'
                        && (Integer.parseInt(paramParts[0]) <= startDateInfo[0]
                                && Integer.parseInt(paramParts[0]) >= endDateInfo[0])
                        && (Integer.parseInt(paramParts[1]) <= startDateInfo[1]
                                && Integer.parseInt(paramParts[1]) >= endDateInfo[1])
                        && (Integer.parseInt(paramParts[2]) <= startDateInfo[2]
                                && Integer.parseInt(paramParts[2]) >= endDateInfo[2])) {
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

    int[] getDueDateInfo() {
        return this.dueDateInfo;
    }

    int[] getStartDateInfo() {
        return this.startDateInfo;
    }

    int[] getEndDateInfo() {
        return this.endDateInfo;
    }

    ArrayList<Date> getDateList() {
        return this.dateList;
    }

}