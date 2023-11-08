package model;
import com.google.protobuf.Api;
import database.Database;
import enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import object.ApiKeys;
import object.RequestFilm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    public List<ApiKeys> findAllApiKeys() throws SQLException {
        List<ApiKeys> listApi = new ArrayList<>();
        ResultSet rs = this.db.executeQuery("SELECT * FROM " + this.table + ";");
        while(rs.next()){
            ApiKeys ak = new ApiKeys(
                    rs.getInt("id"),
                    rs.getString("api_keys"),
                    rs.getString("client")
            );
            listApi.add(ak);
        }

        return listApi;
    }

    public boolean verifyApiKey(String api_key) throws SQLException {
        String query = "SELECT 1 FROM " + this.table + " WHERE api_key = ?";

        try (PreparedStatement pstmt = this.db.prepareStatement(query)) {
            pstmt.setString(1, api_key);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
    }
}
