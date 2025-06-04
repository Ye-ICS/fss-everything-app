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

    public void get() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://opendatakingston.cityofkingston.ca/documents/857a76bec55d429497e980a064add76e/explore")).build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        System.out.println("response body: " + response.body());
    }
}
