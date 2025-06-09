package com.fss.everythingapp.businfo;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.onebusaway.gtfs.impl.GtfsDaoImpl;
import org.onebusaway.gtfs.model.AgencyAndId;
import org.onebusaway.gtfs.model.Route;
import org.onebusaway.gtfs.serialization.GtfsReader;

public class OdkInfoUtils {
    public static String getRouteName(String routeId) throws IOException {
        GtfsReader reader = new GtfsReader();
        reader.setInputLocation(new File("routes.txt"));
        GtfsDaoImpl store = new GtfsDaoImpl();
        reader.setEntityStore(store);
        return store.getRouteForId(new AgencyAndId("agency_id", routeId)).getLongName();
    }

    public static String[] getRouteIds() {
        GtfsReader reader = new GtfsReader();
        try {
            reader.setInputLocation(new File(OdkInfoUtils.class.getResource("gtfs.zip").toURI()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        GtfsDaoImpl store = new GtfsDaoImpl();
        reader.setEntityStore(store);
        return store.getAllRoutes().stream()
                .map(Route::getId)
                .map(AgencyAndId::getId)
                .toArray(String[]::new);
    }
}
