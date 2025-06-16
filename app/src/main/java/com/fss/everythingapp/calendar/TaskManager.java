package com.fss.everythingapp.calendar;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class TaskManager extends DateManager {
    ArrayList<Task> taskList;

    TaskManager() {
    }

    TaskManager(char paramType, LocalDate paramDate) { // 'M' = Month & Year | 'W' = Week & Year | 'D' = DayOfYear &
                                                       // Year
        loadAptDates(paramType, paramDate);
    }

    @Override
    protected ArrayList loadDates() { // Loads all tasks
        taskList = new ArrayList<Task>();
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new File(getClass().getResource("/com/fss/everythingapp/calendar/DateList.txt").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return taskList;
        }

        while (scanner.hasNextLine()) {

            Task loadedTask = new Task();

            String line = scanner.nextLine();
            String[] parts = line.split(",");
            loadedTask.dateName = parts[1];

            if (parts[0].charAt(0) == 'T') {
                loadedTask.dueDate = LocalDateTime.parse(parts[2]);
            }

            taskList.add(loadedTask);
        }
        scanner.close();
        return taskList;
    }

    @Override
    protected ArrayList loadAptDates(char paramType, LocalDate paramDate) {
        // loads all dates with a specific parameter
        taskList = new ArrayList<Task>();
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new File(getClass().getResource("/com/fss/everythingapp/calendar/DateList.txt").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return taskList;
        }
        while (scanner.hasNextLine()) {
            Task loadedTask = new Task();

            String line = scanner.nextLine();
            String[] parts = line.split(",");
            loadedTask.dateName = parts[1];

            TemporalField weekOfYear = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
            int paramWeek = paramDate.get(weekOfYear);

            if (parts[0].charAt(0) == 'T') { // If the current date is a task
                loadedTask.dueDate = LocalDateTime.parse(parts[2]);

                int dueWeek = loadedTask.dueDate.get(weekOfYear);

                if (paramType == 'M'
                        && paramDate.getYear() == loadedTask.dueDate.getYear()
                        && paramDate.getMonthValue() == loadedTask.dueDate.getMonthValue()) {
                    taskList.add(loadedTask);
                } else if (paramType == 'W'
                        && paramDate.getYear() == loadedTask.dueDate.getYear()
                        && paramWeek == dueWeek) {
                    taskList.add(loadedTask);
                } else if (paramType == 'D'
                        && paramDate.getYear() == loadedTask.dueDate.getYear()
                        && paramDate.getDayOfYear() == loadedTask.dueDate.getDayOfYear()) {
                    taskList.add(loadedTask);
                }
            }
        }
        scanner.close();
        return taskList;
    }

    ArrayList<Task> getTaskList() {
        return this.taskList;
    }
}