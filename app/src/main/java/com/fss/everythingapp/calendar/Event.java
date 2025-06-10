package com.fss.everythingapp.calendar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

public class Event extends Date {

    private ArrayList<Event> eventList;

    Event(char paramType, String paramDate) { // 'M' = Month & Year | 'D' = Day, Month & Year

    }

    Event(ArrayList<Event> eventList) { // Blank constructor
    }

    @Override
    protected ArrayList loadAptDates(char paramType, String paramDate) {
        // loads all dates with a specific parameter
        eventList = new ArrayList<Event>();
        String[] paramParts = paramDate.split("/");
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new File(getClass().getResource("/com/fss/everythingapp/calendar/DateList.txt").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return eventList;
        }

        while (scanner.hasNextLine()) {
            Event loadedEvent = new Event(eventList);

            String line = scanner.nextLine();
            String[] parts = line.split(",");
            dateName = parts[1];

            if (parts[0].charAt(0) == 'E') { // If the current date is an event
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
                    eventList.add(loadedEvent);
                } else if (paramType == 'D'
                        && (Integer.parseInt(paramParts[0]) <= startDateInfo[0]
                                && Integer.parseInt(paramParts[0]) >= endDateInfo[0])
                        && (Integer.parseInt(paramParts[1]) <= startDateInfo[1]
                                && Integer.parseInt(paramParts[1]) >= endDateInfo[1])
                        && (Integer.parseInt(paramParts[2]) <= startDateInfo[2]
                                && Integer.parseInt(paramParts[2]) >= endDateInfo[2])) {
                    eventList.add(loadedEvent);
                }
            }
        }
        scanner.close();
        return eventList;
    }

    ArrayList<Event> getEventList() {
        return this.eventList;
    }

}