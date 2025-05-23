package com.fss.everythingapp.studentservices;

class Counsellor {
    private String name;
    private String email;
    private String calendar; //replace with proper variable when time comes

    Counsellor(String name, String email) {
        this.name = name;
        this.email = email;
    }

    private void calendarConnect(){
        CalendarRetrieval calRet = new CalendarRetrieval();
        calendar = calRet.getCalendar();
    }

    String getName() {
        return this.name;
    }
    String getCalendar() {
        return this.calendar;
    }
    

}
