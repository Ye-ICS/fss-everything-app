package com.fss.everythingapp.calendar;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

public class Task extends Date {

    ArrayList<Task> taskList;

    Task(char paramType, String paramDate) { // 'M' = Month & Year | 'D' = Day, Month & Year

    }

    Task(ArrayList<Task> taskList) {
    }

    @Override
    protected ArrayList loadAptDates(char paramType, String paramDate) {
        // loads all dates with a specific parameter
        taskList = new ArrayList<Task>();
        String[] paramParts = paramDate.split("/");
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
            dateName = parts[1];

            if (parts[0].charAt(0) == 'T') { // If the current date is a task
                String dueDate = parts[2];

                String[] dueDateParts = dueDate.split("/");
                dueDateInfo = new int[5];
                dueDateInfo[0] = Integer.parseInt(dueDateParts[0]);
                dueDateInfo[1] = Integer.parseInt(dueDateParts[1]);
                dueDateInfo[2] = Integer.parseInt(dueDateParts[2]);
                String[] dueTimeParts = (dueDateParts[3]).split(":");
                dueDateInfo[3] = Integer.parseInt(dueTimeParts[0]);
                dueDateInfo[4] = Integer.parseInt(dueTimeParts[1]);

                if (paramType == 'M' && Integer.parseInt(paramParts[0]) == dueDateInfo[0]
                        && Integer.parseInt(paramParts[1]) == dueDateInfo[1]) {
                    taskList.add(loadedTask);
                } else if (paramType == 'D' && Integer.parseInt(paramParts[0]) == dueDateInfo[0]
                        && Integer.parseInt(paramParts[1]) == dueDateInfo[1]
                        && Integer.parseInt(paramParts[1]) == dueDateInfo[2]) {
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