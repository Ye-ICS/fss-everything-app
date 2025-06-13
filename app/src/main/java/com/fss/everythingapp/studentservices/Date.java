package com.fss.everythingapp.studentServices;

import java.util.ArrayList;
import java.util.List;

class Date {
    String date;
    List<Timeslot> times = new ArrayList<Timeslot>();

    private void fillTimes(){
        for(int i = 0; i < times.size(); i++){
            Timeslot timeslot = new Timeslot(0.0, 0.0);
            Timeslot temp = times.get(i);
            temp = timeslot;
        }
    }

    Date(List<Timeslot> times) {
        this.times = times;
        fillTimes();
    }

    List<Timeslot> getTimes() {
        return this.times;
    }

    void updateTimes(List<Timeslot> times) {
        this.times = times;
    }

}
