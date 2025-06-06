package com.fss.everythingapp.studentservices;

// THIS IS MOSTLY AI GENERATED

import java.util.Random;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class CalendarAPI {

    private static Random random = new Random();

    /**
     * Attempts to return a mock SSCalendar instance for a counsellor with the given
     * ID.
     * Has a 60% chance of returning a successful calendar.
     *
     * @param id the ID of the counsellor
     * @return an instance of SSCalendar or null (40% of the time)
     */
    public static SSCalendar getCounsellorCalendar(long id) {

        // Creating a mock SSCalendar with dummy data
        List<Date> dates = new ArrayList<Date>();
        int weekday = 1 + (int) (Math.random() * (7 - 1 + 1));

        for (int i = 0; i < 30; i++) {
            if (weekday == 1 || weekday == 7) {

                if (weekday == 1) {
                    Date date = new Date(null);
                    dates.add(date);
                    weekday++;
                } else if (weekday == 7) {
                    Date date = new Date(null);
                    dates.add(date);
                    weekday = 1;
                }

            } else {

                ArrayList<Timeslot> timeslots = new ArrayList<>();

                for (double j = 0; j < 8; j++) {
                    if (random.nextDouble() < 0.6) {
                        Timeslot timeslot = new Timeslot(j + 9 - (j * 0.5), j + 9.5 - (j * 0.5));
                        timeslots.add(timeslot);
                    } else {
                        Timeslot timeslot = new Timeslot(null, null);
                        timeslots.add(timeslot);
                    }
                }
                Date date = new Date(timeslots);
                dates.add(date);
                weekday++;
            }
        }
        return new SSCalendar(id, dates);

    }

}
