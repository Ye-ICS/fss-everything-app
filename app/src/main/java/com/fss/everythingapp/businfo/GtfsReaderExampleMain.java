package com.fss.everythingapp.businfo;

import java.net.URL;

import com.google.transit.realtime.GtfsRealtime.FeedEntity;
import com.google.transit.realtime.GtfsRealtime.FeedMessage;

public class GtfsReaderExampleMain {
  public GtfsReaderExampleMain(String id) throws Exception {
    
    URL url = new URL("https://api.cityofkingston.ca/gtfs-realtime/vehicleupdates.pb");
    FeedMessage feed = FeedMessage.parseFrom(url.openStream());
    System.out.println("test2");
    
    for (FeedEntity entity : feed.getEntityList()) {
      if (entity.hasVehicle() && entity.getVehicle().getVehicle().getId().equals("3490100053")) {
      System.out.println("Vehicle ID: " + entity.getVehicle().getVehicle().getId());
      System.out.println("Position: " + entity.getVehicle().getPosition());
      }
    }
   
  }
}
