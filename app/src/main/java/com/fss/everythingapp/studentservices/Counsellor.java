package com.fss.everythingapp.studentservices;

import java.util.ArrayList;

class Counsellor {
    private String email;
    private int date;
    protected int[] time = new int[7];
    ArrayList<Date> dates = new ArrayList(31);

    Counsellor(String email) {
        this.email = email;
    }

    
    int[] getTime() {return this.time;}
    

}
