import database.Database;
import javax.xml.ws.*;
import service.RequestFilmWS;
import service.SubscriptionWS;
import service.LikesWS;

public class App {
    public static void main(String[] args) {
        try{
            Endpoint.publish("http://0.0.0.0:3030/ws/subscription", new SubscriptionWS());
            Endpoint.publish("http://0.0.0.0:3030/ws/requestFilm", new RequestFilmWS());
            Endpoint.publish("http://0.0.0.0:3030/ws/likes", new LikesWS());
            System.out.println("Startyu");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
