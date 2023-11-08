package client;

import java.net.URL;

public class RestClient {
    public static RestClient instance;
    private final String notflix_rest_url = "http://localhost:8000";

    public static RestClient getInstance() {
        if (instance == null) {
            instance = new RestClient();
        }
        return instance;
    }
}
