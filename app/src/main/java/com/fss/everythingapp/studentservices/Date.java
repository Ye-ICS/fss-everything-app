package com.fss.everythingapp.studentservices;

import java.util.ArrayList;
import java.util.List;

<<<<<<< Updated upstream
    void Date(int[] times) {
        this.times = times;
    }

    int[] getTimes() {return this.times;}

    
=======
class Date  {
    List<Timeslot> times = new ArrayList<Timeslot>();

    //TODO fill arraylist with timeslot instances,give each timeslot a designation for the time it represents

    Date(List<Timeslot> times) {
        this.times = times;
    }

    List<Timeslot> getTimes() {return this.times;}
>>>>>>> Stashed changes
}
