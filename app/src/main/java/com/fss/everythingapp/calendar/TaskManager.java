package com.fss.everythingapp.calendar;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager extends DateManager {
    private String taskName;
    private String dueDate;

    TaskManager() {
    }

    TaskManager(String taskName, String dueDate) {
        this.taskName = taskName;
        this.dueDate = dueDate;

        saveTask(taskName, dueDate);
    }

    TaskManager(ArrayList<TaskManager> taskList) { // Blank constructor
    }

    @Override
    protected ArrayList loadDates() { // Loads all tasks
        ArrayList<TaskManager> taskList = new ArrayList<TaskManager>();
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new File(getClass().getResource("/com/fss/everythingapp/calendar/DateList.txt").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return taskList;
        }

        while (scanner.hasNextLine()) {

            TaskManager loadedTask = new TaskManager(taskList);

            String line = scanner.nextLine();
            String[] parts = line.split(",");
            taskName = parts[1];

            if (parts[0].charAt(0) == 'T') { // If the current event is a task
                String dueDate = parts[2];

                String[] dueDateParts = dueDate.split("/");
                dueDateInfo = new int[5];
                dueDateInfo[0] = Integer.parseInt(dueDateParts[0]);
                dueDateInfo[1] = Integer.parseInt(dueDateParts[1]);
                dueDateInfo[2] = Integer.parseInt(dueDateParts[2]);
                String[] dueTimeParts = (dueDateParts[3]).split(":");
                dueDateInfo[3] = Integer.parseInt(dueTimeParts[0]);
                dueDateInfo[4] = Integer.parseInt(dueTimeParts[1]);

            }

            taskList.add(loadedTask);
        }
        scanner.close();
        return taskList;
    }

    void saveTask(String taskName, String dueDate) {
        PrintWriter writer;
        try {
            writer = new PrintWriter(new FileWriter(new File(getClass().getResource("/com/fss/everythingapp/calendar/DateList.txt").toURI()), true));
            // writer = new PrintWriter(new FileWriter(new File("C:/Users/gd1kt07/OneDrive -
            // Limestone DSB/Documents/testFile.txt"), true));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return;
        }
        writer.println();
        writer.print("T," + taskName + "," + dueDate);
        writer.close();
    }

    static void selectEvent() { // Displays event information
    }

}