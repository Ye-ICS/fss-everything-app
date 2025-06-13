package com.fss.everythingapp.studentservices;


class Counsellor {
    private int id;
    private SSCalendar calendar = CalendarAPI.getCounsellorCalendar(id);

    Counsellor(int id) {
        this.id = id;
    }
    
    SSCalendar getCalendar() {return this.calendar;}

    void updateCalendar(SSCalendar calendar) {
        this.calendar = calendar;
    }
    
}
