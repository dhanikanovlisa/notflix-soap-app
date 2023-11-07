package model;
import database.Database;
import enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import object.RequestFilm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public List<RequestFilm> getAllRequestFilm() throws SQLException {
        List<RequestFilm> listRequestFilm = new ArrayList<>();
        ResultSet rs = this.db.executeQuery("SELECT * FROM " + this.table + ";");
        while(rs.next()){
            RequestFilm rf = new RequestFilm(rs.getInt("creator_id"),
                    rs.getInt("requestFilm_id"),
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

}
