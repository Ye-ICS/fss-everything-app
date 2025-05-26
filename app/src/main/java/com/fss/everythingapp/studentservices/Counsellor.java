package com.fss.everythingapp.studentservices;

import java.util.ArrayList;

class Counsellor {
    private String name;
    private String email;
    private String calendar; //replace with proper variable when time comes
    ArrayList<Date> dates = new ArrayList(30);

    Counsellor(String name, String email) {
        this.name = name;
        this.email = email;
    }

    
    
    

    String getName() {
        return this.name;
    }
    String getCalendar() {
        return this.calendar;
    }
    

}
