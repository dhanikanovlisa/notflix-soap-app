package model;
import com.google.protobuf.Api;
import database.Database;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiKeysModel {
    private Database db;
    private String table;
    private static ApiKeysModel instance;

    public static ApiKeysModel getInstance() {
        try {
            if (instance == null) {
                instance = new ApiKeysModel(new Database(), "api_keys" );
            }
            return instance;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
