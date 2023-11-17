package object;

import enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {
    private int creator_id;
    private int subscriber_id;
    private Status status;

    public Subscription(ResultSet rs) throws SQLException{
        this(
            rs.getInt("creator_id"),
            rs.getInt("subscriber_id"),
            Status.fromStatusCode(rs.getString("status"))
        );
    }
}
