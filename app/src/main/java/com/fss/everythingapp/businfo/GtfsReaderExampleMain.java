package com.fss.everythingapp.businfo;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import com.google.transit.realtime.GtfsRealtime.FeedEntity;
import com.google.transit.realtime.GtfsRealtime.FeedMessage;
import com.google.transit.realtime.GtfsRealtime.TripUpdate;


public class GtfsReaderExampleMain {
  private int first;
  private int tracker;
  public ArrayList<RealStop> getUpdate(String id) throws Exception {
    first = 0;
    tracker = 0;
    int innerTracker = 0;
    
    ArrayList<RealStop> stops = new ArrayList<RealStop>();
  
    
    
    URL url = new URI("https://api.cityofkingston.ca/gtfs-realtime/tripupdates.pb").toURL();
    FeedMessage feed = FeedMessage.parseFrom(url.openStream());
  
    
    for (FeedEntity entity : feed.getEntityList()) {


      if (entity.getTripUpdate().getTrip().getRouteId().equals(id)) {
        for (TripUpdate.StopTimeUpdate stopTimeUpdate : entity.getTripUpdate().getStopTimeUpdateList()) {

          String stopId = stopTimeUpdate.getStopId();
          int stopNum = stopTimeUpdate.getStopSequence();
          long arrivalTime = stopTimeUpdate.getArrival().getTime();
          if(first == 0){
            first = stopNum;
          }
          if(innerTracker == first && tracker == 1){
            break;
          }
          int delay = 0;

          if (stopTimeUpdate.hasArrival() && stopTimeUpdate.getArrival().hasDelay()) {
            delay = stopTimeUpdate.getArrival().getDelay();
          }
          else if (stopTimeUpdate.hasDeparture() && stopTimeUpdate.getDeparture().hasDelay()) {
            delay = stopTimeUpdate.getDeparture().getDelay();
          }
          if(tracker == 1){
            stops.add(innerTracker, new RealStop(stopNum, stopId, arrivalTime, delay));
            innerTracker++;
          }
          stops.add(new RealStop(stopNum, stopId, arrivalTime, delay));

        }
        tracker++;
        if(tracker == 1){
          break;
        }
        
        
      }
    }
    return stops;
  }
   
  
}
