package com.fss.everythingapp.businfo;

public class RealStop {
    public int num;
    public String id;
    public long predictedTime;
    public int delay;

    public RealStop(int num, String id, long predictedTime, int delay ){
        this.num = num;
        this.id = id;
        this.predictedTime = predictedTime;
        this.delay = delay;
    }
    

}
