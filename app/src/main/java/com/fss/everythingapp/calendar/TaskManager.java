package com.fss.everythingapp.calendar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManager extends DateManager {
    String taskName;
    String dueDate;

    //ArrayList<Task> taskList = new ArrayList();
    TaskManager(String taskName, String dueDate) throws FileNotFoundException {
        this.taskName = taskName;
        this.dueDate = dueDate;

        saveTask(taskName, dueDate);
    }

    static void selectTask() { // Displays task information
    }

    @Override
    protected void loadDate() throws FileNotFoundException {
        Scanner scanner = new Scanner("DateList");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            if (parts[0].charAt(0) == 'T') {
                String dueDate = parts[2];

                String[] times = dueDate.split("/");
                int dueYear = Integer.parseInt(times[0]);
                int dueMonth = Integer.parseInt(times[1]);
                int dueDay = Integer.parseInt(times[2]);
                int dueTime = Integer.parseInt(times[3]);
            }
        }
        scanner.close();
    }

    void saveTask(String taskName, String dueDate) throws FileNotFoundException {
        PrintWriter writer;
        try {
            writer = new PrintWriter(new FileWriter(new File(getClass().getResource("/com/fss/everythingapp/calendar/DateList.txt").toURI()), true));
            // writer = new PrintWriter(new FileWriter(new File("C:/Users/gd1kt07/OneDrive - Limestone DSB/Documents/testFile.txt"), true));
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