package com.fss.everythingapp.businfo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class OdkApiService {
    public StaticData organise() {
        return new StaticData();

    }

    public static void get() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.cityofkingston.ca/gtfs-realtime/vehicleupdates.pb")).build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        System.out.println("response body: " + response.body());

    }
}
