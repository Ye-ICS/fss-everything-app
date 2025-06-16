package com.fss.everythingapp.calendar;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

public class Task extends Date {
    Task() {
    }

    Task(String taskName, LocalDateTime dueDate) {
        saveTask(taskName, dueDate);
    }

    private void saveTask(String taskName, LocalDateTime dueDate) {
        PrintWriter writer;
        try {
            writer = new PrintWriter(new FileWriter(
                    new File(getClass().getResource("/com/fss/everythingapp/calendar/DateList.txt").toURI()), true));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return;
        }
        writer.println("T," + taskName + "," + dueDate);
        writer.close();
    }
}