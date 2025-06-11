package com.fss.everythingapp.calendar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class EventManager extends DateManager {
    ArrayList<Event> events = new ArrayList<Event>();
    String eventTitle;
    String startDate;
    String endDate;

    EventManager(String eventTitle, String startDate, String endDate) {
        this.eventTitle = eventTitle;
        this.startDate = startDate;
        this.endDate = endDate;

        saveEvent(eventTitle, startDate, endDate);
    }

    EventManager() { // Blank constructor
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

            EventManager loadedEvent = new EventManager();

            String line = scanner.nextLine();
            String[] parts = line.split(",");
            dateName = parts[1];

            if (parts[0].charAt(0) == 'E') { // If the current date is an event
                String startDate = parts[2];
                String endDate = parts[3];

                String[] startDateParts = startDate.split("/");
                startYear = Integer.parseInt(startDateParts[0]);
                startMonth = Integer.parseInt(startDateParts[1]);
                startDay = Integer.parseInt(startDateParts[2]);
                String[] startTimeParts = (startDateParts[3]).split(":");
                startHour = Integer.parseInt(startTimeParts[0]);
                startMins = Integer.parseInt(startTimeParts[1]);

                String[] endDateParts = endDate.split("/");
                endYear = Integer.parseInt(endDateParts[0]);
                endMonth = Integer.parseInt(endDateParts[1]);
                endDay = Integer.parseInt(endDateParts[2]);
                String[] endTimeParts = (endDateParts[3]).split(":");
                endHour = Integer.parseInt(endTimeParts[0]);
                endMins = Integer.parseInt(endTimeParts[1]);

            }
            eventList.add(loadedEvent);
        }
        scanner.close();
        return eventList;
    }

    // // need to fix stuff with scanner
    // private static Event createEvent(Scanner scanner, ArrayList<Event> events) {
    // System.out.println("Enter name of event:");
    // String name = scanner.nextLine();
    // System.out.println("Enter starting date month then date");
    // int monthStart = Integer.parseInt(scanner.nextLine());
    // int dayStart = Integer.parseInt(scanner.nextLine());
    // System.out.println("Enter ending date month then date");
    // int monthEnd = Integer.parseInt(scanner.nextLine());
    // int dayEnd = Integer.parseInt(scanner.nextLine());
    // int index = events.size() + 1;
    // Event newEvent = new Event(name, index, index, index, index, index);
    // events.add(newEvent);
    // System.out.println("Account created successfully. Event index: " + index);
    // return newEvent;
    // }

    void saveEvent(String eventTitle, String startDate, String endDate) {
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
        writer.println();
        writer.print("E," + eventTitle + "," + startDate + "," + endDate);
        writer.close();
    }

    static void selectEvent() { // Displays event information
    }
}