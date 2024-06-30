package service;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import model.MarsPhoto;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;
public class ConsumerApi {

    private static final String API_KEY = "g8doei03Y1Bza4tuNWgkncyqNOyhz2eCfbxG8ywZ";
    private static final String API_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos";

    public List<MarsPhoto> getAllMarsPhotos() throws Exception {
        HttpClient client = HttpClient.newBuilder().build();

        URI uri = new URI(String.format("%s?sol=1000&api_key=%s", API_URL, API_KEY));
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();


        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);
        List<MarsPhoto> photos = gson.fromJson(jsonObject.getAsJsonArray("photos"), new TypeToken<List<MarsPhoto>>() {}.getType());

        return Collections.unmodifiableList(photos);
    }

}
