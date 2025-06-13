package com.fss.everythingapp.studentServices;

import java.util.ArrayList;
import java.util.List;

class StudentServices {
    ArrayList<Counsellor> counsellors = new ArrayList<>();
    SSCalendar calendar;
    int id;

    private ArrayList<Counsellor> fillCounsellors() {
        for (int i = 0; i < 4; i++) {
            Counsellor counsellor = new Counsellor(i);
            counsellors.add(counsellor);
        }
        return counsellors;
    }

    public void programRunner() {
        counsellors = fillCounsellors();
        id = AppointmentController.grade - 9;
        calendar = counsellors.get(id).getCalendar();
        TimeslotsController.timeslotController(calendar.getAvailableDates());
    }

    void update(List<Date> dates) {
        calendar.updateDate(dates);
        counsellors.get(id).updateCalendar(calendar);
    }

}
