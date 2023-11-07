import database.Database;
import jakarta.xml.ws.Endpoint;
import service.SubscriptionWS;

public class App {
    public static void main(String[] args) {
        try{
            Endpoint.publish("http://0.0.0.0:3030/ws/subscription", new SubscriptionWS());
            Database db = new Database();
            db.createTables();
            System.out.println("Start");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
