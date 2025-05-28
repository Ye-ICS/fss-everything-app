package com.fss.everythingapp.calendar;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class EventManager extends DateManager {
    ArrayList<Event> events = new ArrayList<Event>();

    EventManager(String eventTitle, String startDate, String endDate) throws FileNotFoundException {
        saveEvent(eventTitle, startDate, endDate);
    }

    @Override
    protected void loadDate() throws FileNotFoundException {
        Scanner scanner = new Scanner("DateList");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            if (parts[0].charAt(0) == 'E') {
                String eventName = parts[1];
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
    }

    // need to fix stuff with scanner
    private static Event createEvent(Scanner scanner, ArrayList<Event> events) {
        System.out.println("Enter name of event:");
        String name = scanner.nextLine();
        System.out.println("Enter starting date month then date");
        int monthStart = Integer.parseInt(scanner.nextLine());
        int dayStart = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter ending date month then date");
        int monthEnd = Integer.parseInt(scanner.nextLine());
        int dayEnd = Integer.parseInt(scanner.nextLine());
        int index = events.size() + 1;
        Event newEvent = new Event(name, index, index, index, index, index);
        events.add(newEvent);
        System.out.println("Account created successfully. Event index: " + index);
        return newEvent;
    }

    PrintWriter writer = new PrintWriter("DateList.txt");

    static void saveEvent(String eventTitle, String startDate, String endDate) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("DateList.txt");
        writer.println("E," + eventTitle + "," + startDate + "," + endDate);
        writer.close();
    }

    static void selectEvent() { // Displays event information
    }
}
