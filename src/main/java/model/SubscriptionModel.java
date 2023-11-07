package model;
import database.Database;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SubscriptionModel {
    private Database db;
    private String table;
    private static SubscriptionModel instance;

    public static SubscriptionModel getInstance() {
        try {
            if (instance == null) {
                instance = new SubscriptionModel(new Database(), "subscription" );
            }
            return instance;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
