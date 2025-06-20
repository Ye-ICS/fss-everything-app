package com.fss.everythingapp.calendar;

import java.io.File;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import javafx.scene.control.ScrollPane;

public class EventManager extends DateManager {
    ArrayList<Event> eventList;

    EventManager(ScrollPane dateListPane) {
        loadDates();
    }

    EventManager(ArrayList<EventManager> eventList) { // Blank constructor
    }

    @Override
    protected ArrayList loadDates() { // Loads all events
        eventList = new ArrayList<Event>();
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new File(getClass().getResource("/com/fss/everythingapp/calendar/DateList.txt").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return eventList;
        }

        while (scanner.hasNextLine()) {

            Event loadedEvent = new Event();

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

    // @Override
    protected ArrayList loadAptDates(char paramType, LocalDate paramDate) {
        // loads all dates with a specific parameter
        eventList = new ArrayList<Event>();
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new File(getClass().getResource("/com/fss/everythingapp/calendar/DateList.txt").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return eventList;
        }

        while (scanner.hasNextLine()) {
            Event loadedEvent = new Event();

            String line = scanner.nextLine();
            String[] parts = line.split(",");
            loadedEvent.dateName = parts[1];

            TemporalField weekOfYear = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
            int paramWeek = paramDate.get(weekOfYear);

            if (parts[0].charAt(0) == 'E') { // If the current date is an event
                loadedEvent.startDate = LocalDateTime.parse(parts[2]);
                loadedEvent.endDate = LocalDateTime.parse(parts[3]);

                int startWeek = loadedEvent.dueDate.get(weekOfYear);
                int endWeek = loadedEvent.dueDate.get(weekOfYear);

                if (paramType == 'M'
                        && paramDate.getYear() >= loadedEvent.startDate.getYear()
                        && paramDate.getYear() <= loadedEvent.endDate.getYear()
                        && paramDate.getMonthValue() >= loadedEvent.startDate.getMonthValue()
                        && paramDate.getMonthValue() <= loadedEvent.endDate.getMonthValue()) {
                    eventList.add(loadedEvent);
                } else if (paramType == 'W'
                        && paramDate.getYear() >= loadedEvent.startDate.getYear()
                        && paramDate.getYear() <= loadedEvent.endDate.getYear()
                        && paramWeek >= startWeek
                        && paramWeek <= endWeek) {
                    eventList.add(loadedEvent);
                } else if (paramType == 'D'
                        && paramDate.getYear() >= loadedEvent.startDate.getYear()
                        && paramDate.getYear() <= loadedEvent.endDate.getYear()
                        && paramDate.getDayOfYear() >= loadedEvent.startDate.getDayOfYear()
                        && paramDate.getDayOfYear() <= loadedEvent.endDate.getDayOfYear()) {
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