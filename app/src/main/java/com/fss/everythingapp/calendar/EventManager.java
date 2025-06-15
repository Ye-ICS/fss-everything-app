package com.fss.everythingapp.calendar;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class EventManager extends DateManager {
    ArrayList<EventManager> eventList;

    EventManager() {
        loadDates();
    }

    EventManager(String eventName, LocalDateTime startDate, LocalDateTime endDate) {
        saveEvent(eventName, startDate, endDate);
    }

    EventManager(ArrayList<EventManager> eventList) { // Blank constructor
    }

    @Override
    protected ArrayList loadDates() { // Loads all events
        eventList = new ArrayList<EventManager>();
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
            loadedEvent.dateName = parts[1];

            if (parts[0].charAt(0) == 'E') { // If the current date is an event
                loadedEvent.startDate = LocalDateTime.parse(parts[2]);
                loadedEvent.endDate = LocalDateTime.parse(parts[3]);

            }
            eventList.add(loadedEvent);
        }
        scanner.close();
        return eventList;
    }

    protected void saveEvent(String eventName, LocalDateTime startDate, LocalDateTime endDate) {
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
        writer.println("E," + eventName + "," + startDate + "," + endDate);
        writer.close();
    }

    static void selectEvent() { // Displays event information
    }
}