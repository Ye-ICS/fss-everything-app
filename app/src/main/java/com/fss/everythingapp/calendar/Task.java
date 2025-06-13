package com.fss.everythingapp.calendar;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Task extends Date {
    protected int[] dueDateInfo;

    ArrayList<Task> taskList;

    Task(char paramType, LocalDateTime paramDate) { // 'M' = Month & Year | 'D' = Day, Month & Year

    }

    Task(ArrayList<Task> taskList) {
    }

    @Override
    protected ArrayList loadAptDates(char paramType, LocalDateTime paramDate) {
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
            Task loadedTask = new Task(taskList);

            String line = scanner.nextLine();
            String[] parts = line.split(",");
            loadedTask.dateName = parts[1];

            if (parts[0].charAt(0) == 'T') { // If the current date is a task
                loadedTask.dueDate = LocalDateTime.parse(parts[2]);

                if (paramType == 'M'
                        && paramDate.getYear() == loadedTask.dueDate.getYear()
                        && paramDate.getMonth() == loadedTask.dueDate.getMonth()) {
                    taskList.add(loadedTask);
                } else if (paramType == 'D'
                        && paramDate.getYear() == loadedTask.dueDate.getYear()
                        && paramDate.getMonth() == loadedTask.dueDate.getMonth()
                        && paramDate.getDayOfMonth() == loadedTask.dueDate.getDayOfMonth()) {
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