package com.fss.everythingapp.studentServices;

import java.util.ArrayList;

import java.util.List;

public class SSCalendar {
    Long id;
    List<Date> dates = new ArrayList<Date>();

    SSCalendar(Long id, List<Date> dates) {
        this.dates = dates;
    }

    public List<Date> getAvailableDates() {
        return dates;
    }

    void updateDate(List<Date> dates) {
        this.dates = dates;
    }
}
