package com.fss.everythingapp.calendar;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class EventManager {
    ArrayList<Event> events = new ArrayList<Event>();

    EventManager(String eventTitle, String startDate, String endDate) throws FileNotFoundException {
        saveEvent(eventTitle, startDate, endDate);
    }


    //need to fix stuff with scanner
    private static Event createEvent(Scanner scanner, ArrayList<Event> events) {
        System.out.println("Enter name of event:");
        String name = scanner.nextLine();
        System.out.println("Enter starting date month then date");
        int monthStart = Integer.parseInt(scanner.nextLine());
        int dayStart = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter ending date month then date");
        int monthEnd = Integer.parseInt(scanner.nextLine());
        int dayEnd = Integer.parseInt(scanner.nextLine());
        int index = events.size() + 1;
        Event newEvent = new Event(name, index, index, index, index, index);
        events.add(newEvent);
        System.out.println("Account created successfully. Event index: " + index);
        return newEvent;
    }

static void saveEvent(String eventTitle, String startDate, String endDate) throws FileNotFoundException { // Basic date creation, user selects whether to create an event or task
        PrintWriter writer = new PrintWriter("DateList.txt");
        writer.println(eventTitle + "," + startDate + "," + endDate);



    }

    static void selectEvent() { // Displays event information
    }
}
