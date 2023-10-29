import jakarta.xml.ws.Endpoint;
import service.SubscriptionWS;

public class App {
    public static void main(String[] args) {
        try{
            Endpoint.publish("http://soap-service:3030/ws/subscription", new SubscriptionWS());
            System.out.println("Start");
        }
        catch (Exception e){
            System.out.println("Ooopsies");
        }
    }
}
