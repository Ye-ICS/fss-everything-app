package com.fss.everythingapp.studentservices;

import java.util.ArrayList;
import java.util.List;

public class SSCalendar {
    List<Date> dates = new ArrayList<Date>();

    public SSCalendar(List<Date> dates){
        this.dates = dates;
    }

    //TODO fill arraylist with date instances, give each date a designation for the date it represents

    public List<Date> getAvailableDates(){
        return dates;
    }
}
