package com.fss.everythingapp.studentservices;

public class Timeslot {
    private boolean available = true;

    Timeslot(boolean available) {
        this.available = available;
    }

    boolean setAvailable() {
        available = false;
        return available;
    }

    boolean getAvailable() {return this.available;}
}
