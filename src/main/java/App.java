import database.Database;
import javax.xml.ws.*;
import service.RequestFilmWS;
import service.SubscriptionWS;

public class App {
    public static void main(String[] args) {
        try{
            Endpoint.publish("http://0.0.0.0:3030/ws/subscription", new SubscriptionWS());
            Endpoint.publish("http://0.0.0.0:3030/ws/requestFilm", new RequestFilmWS());
            Database db = new Database();
            db.createTables();
            System.out.println("Start");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
