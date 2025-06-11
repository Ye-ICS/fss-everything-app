package com.fss.everythingapp.calendar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

public class Event extends Date {

    Event(String dateName, char dateType, int[] dueDateInfo, int[] startDateInfo, int[] endDateInfo) {
        super(dateName, dateType, dueDateInfo, startDateInfo, endDateInfo);
    }

    // @Override
    // protected ArrayList loadAptDates(char paramType, String paramDate) {
    // // loads all dates with a specific parameter
    // ArrayList<Event> eventList = new ArrayList<Event>();
    // String[] paramParts = paramDate.split("/");
    // Scanner scanner;

    // try {
    // scanner = new Scanner(
    // new
    // File(getClass().getResource("/com/fss/everythingapp/calendar/DateList.txt").toURI()));
    // } catch (IOException | URISyntaxException e) {
    // e.printStackTrace();
    // return eventList;
    // }

    // while (scanner.hasNextLine()) {
    // Event loadedEvent = new Event();

    // String line = scanner.nextLine();
    // String[] parts = line.split(",");
    // dateName = parts[1];

    // if (parts[0].charAt(0) == 'E') { // If the current date is an event
    // String startDate = parts[2];
    // String endDate = parts[3];

    // String[] startDateParts = startDate.split("/");
    // startYear = Integer.parseInt(startDateParts[0]);
    // startMonth = Integer.parseInt(startDateParts[1]);
    // startDay = Integer.parseInt(startDateParts[2]);
    // String[] startTimeParts = (startDateParts[3]).split(":");
    // startHour = Integer.parseInt(startTimeParts[0]);
    // startMins = Integer.parseInt(startTimeParts[1]);

    // String[] endDateParts = endDate.split("/");
    // endYear = Integer.parseInt(endDateParts[0]);
    // endMonth = Integer.parseInt(endDateParts[1]);
    // endDay = Integer.parseInt(endDateParts[2]);
    // String[] endTimeParts = (endDateParts[3]).split(":");
    // endHour = Integer.parseInt(endTimeParts[0]);
    // endMins = Integer.parseInt(endTimeParts[1]);

    // if (paramType == 'M'
    // && (Integer.parseInt(paramParts[0]) <= startYear &&
    // Integer.parseInt(paramParts[0]) >= endYear)
    // && (Integer.parseInt(paramParts[1]) <= startMonth
    // && Integer.parseInt(paramParts[1]) >= endMonth)) {
    // eventList.add(loadedEvent);
    // } else if (paramType == 'D'
    // && (Integer.parseInt(paramParts[0]) <= startYear &&
    // Integer.parseInt(paramParts[0]) >= endYear)
    // && (Integer.parseInt(paramParts[1]) <= startMonth
    // && Integer.parseInt(paramParts[1]) >= endMonth)
    // && (Integer.parseInt(paramParts[2]) <= startDay &&
    // Integer.parseInt(paramParts[2]) >= endDay)) {
    // eventList.add(loadedEvent);
    // }
    // }
    // }
    // scanner.close();
    // return eventList;
    // }

}