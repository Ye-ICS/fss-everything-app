package com.fss.everythingapp.studentservices;

import java.util.ArrayList;
import java.util.List;

class Date {
    String date;
    List<Timeslot> times = new ArrayList<Timeslot>();

    private void fillTimes(){
        for(int i = 0; i < times.size(); i++){
            Timeslot timeslot = new Timeslot("0", "0");
            times.get(i) = timeslot;
        }
    }

    Date(Timeslot[] times) {
        this.times = times;
        fillTimes();
    }

    Timeslot[] getTimes() {
        return this.times;
    }
}
