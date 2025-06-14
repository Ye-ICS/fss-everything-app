package com.fss.everythingapp.businfo;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.onebusaway.gtfs.impl.GtfsDaoImpl;
import org.onebusaway.gtfs.model.Route;
import org.onebusaway.gtfs.model.Stop;
import org.onebusaway.gtfs.model.StopTime;
import org.onebusaway.gtfs.serialization.GtfsReader;

public class GtfsStaticReader {

    private GtfsReader reader;
    private GtfsDaoImpl store;

    public GtfsStaticReader() {
        try {
            reader = new GtfsReader();
            reader.setInputLocation(new File(GtfsStaticReader.class.getResource("gtfs.zip").toURI()));
            store = new GtfsDaoImpl();
            reader.setEntityStore(store);
            reader.run();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public Route getRouteById(String routeId) {
        for (Route route : store.getAllRoutes()) {
            if (route.getId().getId().equals(routeId)) {
                return route;
            }
        }
        return null;
    }

    public Stop getStopById(String stopId){
        for (Stop stop : store.getAllStops()) {
            if (stop.getId().getId().equals(stopId)) {
                return stop;
            }
        }
        return null;
    }

    public int getStopTimeById(String stopId){
        for (StopTime stopTime : store.getAllStopTimes()) {
            if (stopTime.getStop().getId().getId().equals(stopId)) {
                return stopTime.getArrivalTime();
            }
        }
        return -1;
    }

    public ArrayList<String> getRouteNames() {
        ArrayList<String> routeNames = new ArrayList<>();

        for (Route route : store.getAllRoutes()) {
            routeNames.add(route.getLongName());
        }

        return routeNames;
    }

    public ArrayList<String> getRouteIds() {
        ArrayList<String> routeNames = new ArrayList<>();

        for (Route route : store.getAllRoutes()) {
            routeNames.add(route.getId().getId());
        }

        return routeNames;
    }
}
