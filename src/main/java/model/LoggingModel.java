package model;
import com.mysql.cj.log.Log;
import database.Database;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import object.ApiKeys;
import object.Logging;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public List<Logging> getAllLogging() throws SQLException{
        List<Logging> listLogging = new ArrayList<>();
        ResultSet rs = this.db.executeQuery("SELECT * FROM " + this.table + ";");
        while(rs.next()){
            Logging log = new Logging(
                    rs.getInt("id"),
                    rs.getString("description"),
                    rs.getString("ip"),
                    rs.getString("endpoint"),
                    rs.getDate("requested_at")
            );
            listLogging.add(log);
        }

        return listLogging;
    }

    public String createLog(String description, String ip, String endpoint, Timestamp requested_At) throws SQLException{

        String query = "INSERT INTO " + this.table + " (description, ip, endpoint, requested_at)" +
                "VALUES ( ?, ?, ?, ?)";

        try (PreparedStatement pstmt = this.db.prepareStatement(query)) {
            pstmt.setString(1, description);
            pstmt.setString(2, ip);
            pstmt.setString(3, endpoint);
            pstmt.setTimestamp(4, requested_At);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected + " rows affected";
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }
}
