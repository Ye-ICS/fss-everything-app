package com.fss.everythingapp.businfo;

public class RealtimeStop {
    public int num;
    public String id;
    public long predictedTime;
    public int delay;

    public RealtimeStop(int num, String id, long predictedTime, int delay ){
        this.num = num;
        this.id = id;
        this.predictedTime = predictedTime;
        this.delay = delay;
    }
    

}
