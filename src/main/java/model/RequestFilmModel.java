package model;
import database.Database;
import enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import object.RequestFilm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestFilmModel {
    private Database db;
    private String table;
    private static RequestFilmModel instance;

    public static RequestFilmModel getInstance() {
        try {
            if (instance == null) {
                instance = new RequestFilmModel(new Database(), "request_film" );
            }
            return instance;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    /** Get All Request Film*/
    public List<RequestFilm> getAllRequestFilm() throws SQLException {
        List<RequestFilm> listRequestFilm = new ArrayList<>();
        ResultSet rs = this.db.executeQuery("SELECT * FROM " + this.table + ";");
        while(rs.next()){
            RequestFilm rf = new RequestFilm(
                    rs.getInt("requestFilm_id"),
                    rs.getInt("user_id"),
                    rs.getString("filmName"),
                    rs.getString("description"),
                    rs.getString("film_path"),
                    rs.getString("film_poster"),
                    rs.getString("film_header"),
                    rs.getDate("date_release"),
                    rs.getInt("duration"),
                    Status.fromStatusCode(rs.getString("status"))
                    );
            listRequestFilm.add(rf);
        }

        return listRequestFilm;
    }

    /**Get all request film by id */
    public List<RequestFilm> getAllRequestFilmById(int user_id) throws SQLException {
        List<RequestFilm> listRequestFilm = new ArrayList<>();

        String query = "SELECT * FROM " + this.table + " WHERE user_id = ?";
        try (PreparedStatement pstmt = this.db.prepareStatement(query)) {
            pstmt.setInt(1, user_id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                RequestFilm rf = new RequestFilm(
                        rs.getInt("requestFilm_id"),
                        rs.getInt("user_id"),
                        rs.getString("filmName"),
                        rs.getString("description"),
                        rs.getString("film_path"),
                        rs.getString("film_poster"),
                        rs.getString("film_header"),
                        rs.getDate("date_release"),
                        rs.getInt("duration"),
                        Status.fromStatusCode(rs.getString("status"))
                );
                listRequestFilm.add(rf);
            }
        }

        return listRequestFilm;
    }

    /**Create Request Film*/
    public String createRequestFilm( int user_id, String filmName, String description, String film_path,
                                  String film_poster, String film_header, Date date_release, int duration){

        String query = "INSERT INTO " + this.table + " (user_id, filmName, description, film_path, " +
                "film_poster, film_header, date_release, duration) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = this.db.prepareStatement(query)) {
            pstmt.setInt(1, user_id);
            pstmt.setString(2, filmName);
            pstmt.setString(3, description);
            pstmt.setString(4, film_path);
            pstmt.setString(5, film_poster);
            pstmt.setString(6, film_header);
            pstmt.setDate(7, new java.sql.Date(date_release.getTime()));
            pstmt.setInt(8, duration);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected + " rows affected";
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String editRequestFilm(int requestFilm_id, int user_id, String filmName, String description, String film_path,
                                  String film_poster, String film_header, Date date_release, int duration){
        String query = "UPDATE " + this.table + " SET " +
                "user_id = ?, filmName = ?, description = ?, film_path = ?, " +
                "film_poster = ?, film_header = ?, date_release = ?, duration = ? " +
                "WHERE requestFilm_id = ?";

        try (PreparedStatement pstmt = this.db.prepareStatement(query)) {
            pstmt.setInt(1, requestFilm_id);
            pstmt.setInt(2, user_id);
            pstmt.setString(3, filmName);
            pstmt.setString(4, description);
            pstmt.setString(5, film_path);
            pstmt.setString(6, film_poster);
            pstmt.setString(7, film_header);
            pstmt.setDate(8, new java.sql.Date(date_release.getTime()));
            pstmt.setInt(9, duration);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected + "rows affected";
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }



    public String deleteRequestFilm(int requestFilm_id, int user_id) throws SQLException {
        String query = "DELETE FROM " + this.table + " WHERE requestFilm_id = ?";

        try (PreparedStatement pstmt = this.db.prepareStatement(query)) {
            pstmt.setInt(1, requestFilm_id);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected + " rows affected";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error deleting request film";
        }
    }



}
