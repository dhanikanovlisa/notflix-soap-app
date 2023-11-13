package object;
import enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RequestFilm {
    private int requestFilm_id;
    private int user_id;
    private String filmName;
    private String description;
    private String film_path;
    private String film_poster;
    private String film_header;
    private Date date_release;
    private int duration;
    private Status status;

    public RequestFilm(ResultSet rs) throws SQLException{
        this(
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
    }
}
