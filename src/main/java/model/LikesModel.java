package model;
import com.google.protobuf.Api;
import database.Database;
import enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import object.ApiKeys;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LikesModel {
    private Database db;
    private final String table = "likes";
    private static LikesModel instance;

    public static LikesModel getInstance() {
        try {
            if (instance == null) {
                instance = new LikesModel(new Database());
            }
            return instance;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**is User Like Film */
    public boolean isUserLikeFilm(int film_id, int user_id) throws SQLException {
        String query = "SELECT 1 FROM " + this.table + " WHERE film_id = ? AND user_id = ?";

        try (PreparedStatement pstmt = this.db.prepareStatement(query)) {
            pstmt.setInt(1, film_id);
            pstmt.setInt(2, user_id);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    /* Add Likes */
    public boolean addLikes(int film_id, int user_id) throws SQLException {
        String query = "INSERT INTO " + this.table + " (film_id, user_id) VALUES (?, ?)";

        try (PreparedStatement pstmt = this.db.prepareStatement(query)) {
            pstmt.setInt(1, film_id);
            pstmt.setInt(2, user_id);

            return pstmt.executeUpdate() > 0;
        }
    }

    /* Delete Likes */
    public boolean deleteLikes(int film_id, int user_id) throws SQLException {
        String query = "DELETE FROM " + this.table + " WHERE film_id = ? AND user_id = ?";

        try (PreparedStatement pstmt = this.db.prepareStatement(query)) {
            pstmt.setInt(1, film_id);
            pstmt.setInt(2, user_id);

            return pstmt.executeUpdate() > 0;
        }
    }
}
