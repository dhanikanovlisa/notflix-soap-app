package model;
import database.Database;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoggingModel {
    private Database db;
    private String table;
    private static LoggingModel instance;

    public static LoggingModel getInstance() {
        try {
            if (instance == null) {
                instance = new LoggingModel(new Database(), "logging" );
            }
            return instance;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
