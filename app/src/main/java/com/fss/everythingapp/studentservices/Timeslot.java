package com.fss.everythingapp.studentservices;

public class Timeslot {
    boolean available = true;
    double startTime;
    double endTime;

    Timeslot(Double startTime, Double endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    boolean setAvailable(boolean available) {
        available = false;
        return available;
    }
}
