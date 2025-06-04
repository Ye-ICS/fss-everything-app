package com.fss.everythingapp.studentservices;

public class Timeslot {
    boolean available = true;
    String startTime;
    String endTime;

    Timeslot(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    boolean setAvailable(boolean available) {
        available = false;
        return available;
    }
}
