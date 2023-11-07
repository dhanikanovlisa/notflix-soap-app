package client;

import java.net.URL;

public class RestClient {
    private final String notflix_rest_url = "http://localhost:8000";

    private RestClient(){
        try {
            URL url = new URL(notflix_rest_url);

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
