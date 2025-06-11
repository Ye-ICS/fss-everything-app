package com.fss.everythingapp.calendar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

public class Task extends Date {
    protected int[] dueDateInfo;

    Task(String dateName, char dateType, int[] dueDateInfo, int[] startDateInfo, int[] endDateInfo) {
        super(dateName, dateType, dueDateInfo);
    }

    // @Override
    // protected ArrayList loadAptDates(char paramType, String paramDate) {
    // // loads all dates with a specific parameter
    // ArrayList<Task> taskList = new ArrayList<Task>();
    // String[] paramParts = paramDate.split("/");
    // Scanner scanner;

    // try {
    // scanner = new Scanner(
    // new
    // File(getClass().getResource("/com/fss/everythingapp/calendar/DateList.txt").toURI()));
    // } catch (IOException | URISyntaxException e) {
    // e.printStackTrace();
    // return taskList;
    // }
    // while (scanner.hasNextLine()) {
    // Task loadedTask = new Task();

    // String line = scanner.nextLine();
    // String[] parts = line.split(",");
    // dateName = parts[1];

    // if (parts[0].charAt(0) == 'T') { // If the current date is a task
    // String dueDate = parts[2];

    // String[] dueDateParts = dueDate.split("/");
    // dueYear = Integer.parseInt(dueDateParts[0]);
    // dueMonth = Integer.parseInt(dueDateParts[1]);
    // dueDay = Integer.parseInt(dueDateParts[2]);
    // String[] dueTimeParts = (dueDateParts[3]).split(":");
    // dueHour = Integer.parseInt(dueTimeParts[0]);
    // dueMins = Integer.parseInt(dueTimeParts[1]);

    // if (paramType == 'M' && Integer.parseInt(paramParts[0]) == dueYear
    // && Integer.parseInt(paramParts[1]) == dueMonth) {
    // taskList.add(loadedTask);
    // } else if (paramType == 'D' && Integer.parseInt(paramParts[0]) == dueYear
    // && Integer.parseInt(paramParts[1]) == dueMonth &&
    // Integer.parseInt(paramParts[1]) == dueDay) {
    // taskList.add(loadedTask);
    // }
    // }
    // }
    // scanner.close();
    // return taskList;
    // }
}