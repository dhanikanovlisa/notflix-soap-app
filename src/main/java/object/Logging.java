package object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Logging {
    private int id;
    private String description;
    private String ip;
    private String endpoint;
    private Date requested_at;
}
