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
    private final String table = "request_film";
    private static RequestFilmModel instance;

    public static RequestFilmModel getInstance() {
        try {
            if (instance == null) {
                instance = new RequestFilmModel(new Database());
            }
            return instance;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /** Get All Request Film */
    public List<RequestFilm> getAllRequestFilm() throws SQLException {
        List<RequestFilm> listRequestFilm = new ArrayList<>();
        ResultSet rs = this.db.executeQuery("SELECT * FROM " + this.table + ";");
        while (rs.next()) {
            RequestFilm rf = new RequestFilm(rs);
            listRequestFilm.add(rf);
        }

        return listRequestFilm;
    }

    /** Get all request film by id */
    public List<RequestFilm> getAllRequestFilmById(int user_id) throws SQLException {
        List<RequestFilm> listRequestFilm = new ArrayList<>();

        String query = "SELECT * FROM " + this.table + " WHERE user_id = ?";
        try (PreparedStatement pstmt = this.db.prepareStatement(query)) {
            this.db.bind(user_id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                RequestFilm rf = new RequestFilm(rs);
                listRequestFilm.add(rf);
            }
        }

        return listRequestFilm;
    }

    /** Get Request Film by Film Id */
    public RequestFilm getRequestFilmByFilmId(int requestFilm_id) throws SQLException {
        RequestFilm rf = null;
        String query = "SELECT * FROM " + this.table + " WHERE requestFilm_id = ?";
        try (PreparedStatement pstmt = this.db.prepareStatement(query)) {
            this.db.bind(requestFilm_id);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                rf = new RequestFilm(rs);
            }
        }
        return rf;
    }

    /** Create Request Film */
    public String createRequestFilm(int user_id, String filmName, String description, String film_path,
            String film_poster, String film_header, Date date_release, int duration) {

        String query = "INSERT INTO " + this.table + " (user_id, filmName, description, film_path, " +
                "film_poster, film_header, date_release, duration) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
        System.out.println(query);
        try (PreparedStatement pstmt = this.db.prepareStatement(query)) {
            this.db.bind(user_id, filmName, description, film_path, film_poster, film_header, date_release, duration);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected + " rows affected";
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String editRequestFilm(int requestFilm_id, int user_id, String filmName, String description,
            String film_path, String film_poster, String film_header, Date date_release, int duration) {

        String query = "UPDATE " + this.table + " SET " +
                "user_id = ?, filmName = ?, description = ?, film_path = ?, " +
                "film_poster = ?, film_header = ?, date_release = ?, duration = ? " +
                "WHERE requestFilm_id = ?";

        try (PreparedStatement pstmt = this.db.prepareStatement(query)) {
            this.db.bind(user_id, filmName, description, film_path, film_poster, film_header, date_release, duration,
                    requestFilm_id);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected + " rows affected";
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String deleteRequestFilm(int requestFilm_id) throws SQLException {
        String query = "DELETE FROM " + this.table + " WHERE requestFilm_id = ?";

        try (PreparedStatement pstmt = this.db.prepareStatement(query)) {
            this.db.bind(1, requestFilm_id);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected + " rows affected";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error deleting request film";
        }
    }
}