package com.fss.everythingapp.studentservices;

import java.util.ArrayList;
import java.util.List;

class Date  {
    Timeslot[] times = new Timeslot[6];

    //TODO fill arraylist with timeslot instances,give each timeslot a designation for the time it represents

    Date(Timeslot[] times) {
        this.times = times;
    }

    Timeslot[] getTimes() {return this.times;}
}
