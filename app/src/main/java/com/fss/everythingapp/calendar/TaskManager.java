package com.fss.everythingapp.calendar;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TaskManager extends DateManager {

    TaskManager(String taskName, String dueDate) throws FileNotFoundException {
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

    static void saveTask(String taskName, String dueDate) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("DateList.txt");
        writer.println("T," + taskName + "," + dueDate);
    }

}