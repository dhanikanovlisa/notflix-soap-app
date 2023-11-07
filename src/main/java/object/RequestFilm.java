package object;
import enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RequestFilm {
    private int creator_id;
    private int requestFilm_id;
    private String filmName;
    private String description;
    private String film_poster;
    private String film_path;
    private String film_header;
    private Date date_release;
    private int duration;
    private Status status;


}
