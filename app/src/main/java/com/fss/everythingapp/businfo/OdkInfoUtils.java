package com.fss.everythingapp.businfo;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.onebusaway.gtfs.impl.GtfsDaoImpl;
import org.onebusaway.gtfs.model.Route;
import org.onebusaway.gtfs.serialization.GtfsReader;

public class OdkInfoUtils {

    private GtfsReader reader;
    private GtfsDaoImpl store;

    public OdkInfoUtils() {
        try {
            reader = new GtfsReader();
            reader.setInputLocation(new File(OdkInfoUtils.class.getResource("gtfs.zip").toURI()));
            store = new GtfsDaoImpl();
            reader.setEntityStore(store);
            reader.run();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public String getColour(String routeId){
        Route route = getRouteById(routeId);
        System.out.println(route.getColor());

        return null;
    }

    public Route getRouteById(String routeId) {
        for (Route route : store.getAllRoutes()) {
            if (route.getId().getId().equals(routeId)) {
                return route;
            }
        }
        return null; // or throw an exception if preferred
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
