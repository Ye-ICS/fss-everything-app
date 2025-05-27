package com.fss.everythingapp.calendar;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DateManager {

    DateManager(String elementsFile) throws FileNotFoundException {
    }

    static void loadDate() throws FileNotFoundException {
        Scanner scanner = new Scanner("DateList");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            String eventName = parts[1];
            boolean isTask;
            if (parts[0].charAt(0) == 'T') { // If the current event is a task
                isTask = true;
                String dueDate = parts[2];

                String[] times = dueDate.split("/");
                int dueYear = Integer.parseInt(times[0]);
                int dueMonth = Integer.parseInt(times[1]);
                int dueDay = Integer.parseInt(times[2]);
                int dueTime = Integer.parseInt(times[3]);

            } else { // If the current event is an event
                isTask = false;
                String startDate = parts[2];
                String endDate = parts[3];

                String[] startTimes = startDate.split("/");
                int startYear = Integer.parseInt(startTimes[0]);
                int startMonth = Integer.parseInt(startTimes[1]);
                int startDay = Integer.parseInt(startTimes[2]);
                int startTime = Integer.parseInt(startTimes[3]);

                String[] endTimes = startDate.split("/");
                int endYear = Integer.parseInt(endTimes[0]);
                int endMonth = Integer.parseInt(endTimes[1]);
                int endDay = Integer.parseInt(endTimes[2]);
                int endTime = Integer.parseInt(endTimes[3]);
            }

        }
        scanner.close();
    }

    static void createDate() { // Basic date creation, user selects whether to create an event or task
    }

    static void shareDate() { // User can share dates to other users

    }

    static void saveToList() { // Saves date information to DateList.txt
    }
}