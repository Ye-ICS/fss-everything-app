package com.fss.everythingapp.calendar;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

public class Event extends Date {
    Event() {
    }

    Event(String eventName, LocalDateTime startDate, LocalDateTime endDate) {
        saveEvent(eventName, startDate, endDate);
    }

    private void saveEvent(String eventName, LocalDateTime startDate, LocalDateTime endDate) {
        PrintWriter writer;
        try {
            writer = new PrintWriter(new FileWriter(
                    new File(getClass().getResource("/com/fss/everythingapp/calendar/DateList.txt").toURI()), true));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return;
        }
        writer.println("E," + eventName + "," + startDate + "," + endDate);
        writer.close();
    }
}