package com.fss.everythingapp.studentServices;

public class Timeslot {
    boolean available = true;
    double startTime;
    double endTime;

    Timeslot(Double startTime, Double endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    boolean setAvailable() {
        available = false;
        return available;
    }
}
