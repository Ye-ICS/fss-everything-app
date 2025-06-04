import java.util.Random;
import java.util.ArrayList;

public class CalendarAPI {

    private Random random = new Random();

    /**
     * Attempts to return a mock SSCalendar instance for a counsellor with the given ID.
     * Has a 60% chance of returning a successful calendar.
     *
     * @param id the ID of the counsellor
     * @return an instance of SSCalendar or null (40% of the time)
     */
    public SSCalendar getCounsellorCalendar(long id) {
        if (random.nextDouble() < 0.6) {
            // Creating a mock SSCalendar with dummy data
            ArrayList<Date> dates = new ArrayList<>();

            // Example: Adding a few sample dates with timeslots
            Timeslot timeslot1 = new Timeslot("09:00", "10:00");
            Timeslot timeslot2 = new Timeslot("10:30", "11:30");
            ArrayList<Timeslot> slotsDay1 = new ArrayList<>();
            slotsDay1.add(timeslot1);
            slotsDay1.add(timeslot2);

            Date date1 = new Date("2025-06-10", slotsDay1);
            dates.add(date1);

            return new SSCalendar(id, dates);
        } else {
            // Return null to indicate failure to retrieve calendar
            return null;
        }
    }
}
