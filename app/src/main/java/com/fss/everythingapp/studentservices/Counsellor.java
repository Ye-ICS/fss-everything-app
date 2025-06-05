package com.fss.everythingapp.studentservices;

import java.util.ArrayList;

class Counsellor {
    private int id;

    Counsellor(int id) {
        this.id = id;
    }

    private SSCalendar calendar = CalendarAPI.getCounsellorCalendar(id);

    SSCalendar getCalendar() {return this.calendar;}
    
}
