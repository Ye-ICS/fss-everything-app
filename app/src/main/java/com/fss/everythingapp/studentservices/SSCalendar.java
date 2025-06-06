package com.fss.everythingapp.studentservices;

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
}
