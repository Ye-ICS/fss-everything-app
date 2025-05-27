package com.fss.everythingapp.studentservices;

class CalendarRetrieval extends Counsellor{

    CalendarRetrieval(String name, String email) {
        super(name, email);
        //TODO Auto-generated constructor stub
    }

    int date;
    int[] times = new int[7];

    private void getCalendarInfo() {
        //access councellor's calendar
        //getApi();
        dates.get(date).Date(times);
        
    }

    void sendTimes() {
        time = times;
    }

    
    private void sendCalendarInfo() {
        //update councellor's calendar
    }
}

 