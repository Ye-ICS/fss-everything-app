package com.fss.everythingapp.businfo;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import com.google.transit.realtime.GtfsRealtime.FeedEntity;
import com.google.transit.realtime.GtfsRealtime.FeedMessage;
import com.google.transit.realtime.GtfsRealtime.TripUpdate;

public class GtfsRealtimeReader {
  public static ArrayList<RealtimeStop> getUpdate(String id) {
    int first = 0;
    int tracker = 0;
    int innerTracker = 0;

    ArrayList<RealtimeStop> stops = new ArrayList<RealtimeStop>();

    URL url;
    FeedMessage feed;
    try {
        url = new URI("https://api.cityofkingston.ca/gtfs-realtime/tripupdates.pb").toURL();
        feed = FeedMessage.parseFrom(url.openStream());
    } catch (URISyntaxException | IOException e) {
        e.printStackTrace();
        return null;
    }
    

    for (FeedEntity entity : feed.getEntityList()) {
      if (entity.getTripUpdate().getTrip().getRouteId().equals(id)) {
        for (TripUpdate.StopTimeUpdate stopTimeUpdate : entity.getTripUpdate().getStopTimeUpdateList()) {

          String stopId = stopTimeUpdate.getStopId();
          int stopNum = stopTimeUpdate.getStopSequence();

          if (first == 0) {
            first = stopNum;
          }
          if (innerTracker == first && tracker == 1) {
            break;
          }

          int delay = 0;
          delay = stopTimeUpdate.getArrival().getDelay();

          if (tracker == 1) {
            stops.add(innerTracker, new RealtimeStop(stopNum, stopId, delay));
            innerTracker++;
          }

          stops.add(new RealtimeStop(stopNum, stopId, delay));

        }

        tracker++;
        if (tracker == 1)
          break;
      }
    }
    return stops;
  }
}
