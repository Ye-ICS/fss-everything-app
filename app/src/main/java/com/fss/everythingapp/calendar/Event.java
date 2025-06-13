package com.fss.everythingapp.calendar;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Event extends Date {

    private ArrayList<Event> eventList;

    Event(char paramType, LocalDateTime paramDate) { // 'M' = Month & Year | 'D' = Day, Month & Year

    }

    Event(ArrayList<Event> eventList) { // Blank constructor
    }

    @Override
    protected ArrayList loadAptDates(char paramType, LocalDateTime paramDate) {
        // loads all dates with a specific parameter
        eventList = new ArrayList<Event>();
        Scanner scanner;

        // while (scanner.hasNextLine()) {
        // Event loadedEvent = new Event();

        while (scanner.hasNextLine()) {
            Event loadedEvent = new Event(eventList);

            String line = scanner.nextLine();
            String[] parts = line.split(",");
            loadedEvent.dateName = parts[1];

            if (parts[0].charAt(0) == 'E') { // If the current date is an event
                loadedEvent.startDate = LocalDateTime.parse(parts[2]);
                loadedEvent.endDate = LocalDateTime.parse(parts[3]);

                if (paramType == 'M'
                        && paramDate.getYear() <= loadedEvent.startDate.getYear()
                        && paramDate.getYear() <= loadedEvent.endDate.getYear()
                        && paramDate.getMonthValue() >= loadedEvent.startDate.getMonthValue()
                        && paramDate.getMonthValue() >= loadedEvent.endDate.getMonthValue()) {
                    eventList.add(loadedEvent);
                } else if (paramType == 'D'
                        && paramDate.getYear() <= loadedEvent.startDate.getYear()
                        && paramDate.getYear() <= loadedEvent.endDate.getYear()
                        && paramDate.getMonthValue() >= loadedEvent.startDate.getMonthValue()
                        && paramDate.getMonthValue() >= loadedEvent.endDate.getMonthValue()
                        && paramDate.getDayOfMonth() <= loadedEvent.startDate.getDayOfMonth()
                        && paramDate.getDayOfMonth() <= loadedEvent.endDate.getDayOfMonth()) {
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