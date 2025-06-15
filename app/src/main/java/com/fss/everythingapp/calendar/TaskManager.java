package com.fss.everythingapp.calendar;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager extends DateManager {
    ArrayList<TaskManager> taskList;

    TaskManager() {
    }

    TaskManager(String taskName, LocalDateTime dueDate) {
        saveTask(taskName, dueDate);
    }

    TaskManager(ArrayList<TaskManager> taskList) { // Blank constructor
    }

    @Override
    protected ArrayList loadDates() { // Loads all tasks
        taskList = new ArrayList<TaskManager>();
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
            loadedTask.dateName = parts[1];

            if (parts[0].charAt(0) == 'T') {
                loadedTask.dueDate = LocalDateTime.parse(parts[2]);
            }

            taskList.add(loadedTask);
        }
        scanner.close();
        return taskList;
    }

    void saveTask(String taskName, LocalDateTime dueDate) {
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
        writer.println("T," + taskName + "," + dueDate);
        writer.close();
    }

}