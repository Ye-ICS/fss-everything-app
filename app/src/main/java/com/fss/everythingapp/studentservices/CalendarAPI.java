package com.fss.everythingapp.studentservices;

// AI MADE MOCK API CLASS

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class CalendarAPI {

    private Random random = new Random();

    /**
     * Attempts to return a mock SSCalendar instance for a counsellor with the given
     * ID.
     * Has a 60% chance of returning a successful calendar.
     *
     * @param id the ID of the counsellor
     * @return an instance of SSCalendar or null (40% of the time)
     */
    public SSCalendar getCounsellorCalendar(long id) {

            // Creating a mock SSCalendar with dummy data
        List<Date> dates = new ArrayList<Date>();

        if (random.nextDouble() < 0.6) {
            // Example: Adding a few sample dates with timeslots
            Timeslot timeslot1 = new Timeslot(9.0, 10.0);
            Timeslot timeslot2 = new Timeslot(10.3, 11.3);
            ArrayList<Timeslot> slotsDay1 = new ArrayList<>();
            slotsDay1.add(timeslot1);
            slotsDay1.add(timeslot2);

            Date date1 = new Date(slotsDay1);
            dates.add(date1);
        } else {
            // Return null to indicate failure to retrieve calendar
            Date date1 = new Date(null);
            dates.add(date1);
        }
            return new SSCalendar(id, dates);
        
    }
}
