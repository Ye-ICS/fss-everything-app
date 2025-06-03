package com.fss.everythingapp.studentservices;

import java.util.ArrayList;
import java.util.List;

class Date {
    Timeslot[] times = new Timeslot[6];

    private void fillTimes(){
        for(int i = 0; i < times.length; i++){
            Timeslot timeslot = new Timeslot();
            times[i] = timeslot;
        }
    }

    Date(Timeslot[] times) {
        this.times = times;
    }

    Timeslot[] getTimes() {
        return this.times;
    }
}
