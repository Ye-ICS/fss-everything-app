package com.fss.everythingapp.studentservices;

class CalendarRetrieval extends Counsellor{

    CalendarRetrieval(String name, String email) {
        super(email);
        //TODO Auto-generated constructor stub
    }

    int date;
    int[] times = new int[7];

    private void getCalendarInfo() {
        //access councellor's calendar
        //getApi();
        for (int i = 0; i < 31; i++) {
        dates.get(i).Date(times);
        }
        
    }

    void sendTimes() {
        time = times;
    }

    
    private void sendCalendarInfo() {
        //update councellor's calendar
    }
}

 