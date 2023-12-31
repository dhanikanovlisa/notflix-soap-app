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

    public List<RequestFilm> getAllRequestFilmByStatus(Status state) throws SQLException{
        List<RequestFilm> listRequestFilm = new ArrayList<>();
        String query = "SELECT * FROM "+this.table+" WHERE status = '"+state.getStatusCode().toUpperCase()+"'";

        PreparedStatement pstmt = this.db.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

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
            this.db.bind(1, user_id);
                    this.db.bind(2, filmName);
                    this.db.bind(3, description);
                    this.db.bind(4, film_path);
                    this.db.bind(5, film_poster);
                    this.db.bind(6, film_header);
                    this.db.bind(7, date_release);
                    this.db.bind(8, duration);
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
                    this.db.bind(1, user_id);
                    this.db.bind(2, filmName);
                    this.db.bind(3, description);
                    this.db.bind(4, film_path);
                    this.db.bind(5, film_poster);
                    this.db.bind(6, film_header);
                    this.db.bind(7, date_release);
                    this.db.bind(8, duration);
                    this.db.bind(9, requestFilm_id);
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

    public String updateRequestFilmState(int requestFilm_Id, Status new_state) throws SQLException {
        String query = "UPDATE "+this.table+" SET status = '"+new_state.getStatusCode()+"' WHERE requestFilm_id = ?";

        PreparedStatement pstmt = this.db.prepareStatement(query);
        this.db.bind(requestFilm_Id);

        int rowsAffected = pstmt.executeUpdate();
        return rowsAffected + " rows affected";
    }
}