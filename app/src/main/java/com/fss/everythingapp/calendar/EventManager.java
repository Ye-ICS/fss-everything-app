package com.fss.everythingapp.calendar;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class EventManager extends DateManager {
    ArrayList<Event> events = new ArrayList<Event>();
    private String eventName;
    private String startDate;
    private String endDate;

    EventManager() {
        loadDates();
    }

    EventManager(String eventName, String startDate, String endDate) {
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;

        saveEvent(eventName, startDate, endDate);
    }

    EventManager(ArrayList<EventManager> eventList) { // Blank constructor
    }

    @Override
    protected ArrayList loadDates() { // Loads all events
        ArrayList<EventManager> eventList = new ArrayList<EventManager>();
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new File(getClass().getResource("/com/fss/everythingapp/calendar/DateList.txt").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return eventList;
        }

        while (scanner.hasNextLine()) {

            EventManager loadedEvent = new EventManager(eventList);

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

            }
            eventList.add(loadedEvent);
        }
        scanner.close();
        return eventList;
    }

    protected void saveEvent(String eventName, String startDate, String endDate) {
        PrintWriter writer;
        try {
            writer = new PrintWriter(new FileWriter(
                    new File(getClass().getResource("/com/fss/everythingapp/calendar/DateList.txt").toURI()), true));
            // writer = new PrintWriter(new FileWriter(new File("C:/Users/gd1kt07/OneDrive -
            // Limestone DSB/Documents/testFile.txt"), true));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return;
        }
        writer.print("E," + eventName + "," + startDate + "," + endDate);
        writer.close();
    }

    static void selectEvent() { // Displays event information
    }
}