package database;

import java.sql.*;

public class Database {
    private String host = System.getenv("DB_HOST");
    private String port = System.getenv("DB_PORT");
    private String db = System.getenv("DB_NAME");
    private String user  = System.getenv("DB_USER");
    private String pass = System.getenv("DB_PASSWORD");
    private Connection conn;
    private Statement stmt;

    public Database() throws SQLException {
        String dbURL = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.db
                + "?useSSL=false&allowPublicKeyRetrieval=true&autoReconnect=true";
        System.out.println(dbURL);
        this.conn = DriverManager.getConnection(dbURL, this.user, this.pass);
        this.stmt = this.conn.createStatement();
    }

    public void createTables() throws SQLException {
        // Table: api_keys
        this.stmt.executeUpdate("CREATE TABLE IF NOT EXISTS api_keys (" +
                "    id INT NOT NULL AUTO_INCREMENT," +
                "    api_key VARCHAR(255) NOT NULL," +
                "    client VARCHAR(255) NOT NULL," +
                "    PRIMARY KEY(id)" +
                ")");

        // Table: logging
        this.stmt.executeUpdate("CREATE TABLE IF NOT EXISTS logging (" +
                "    id INT NOT NULL AUTO_INCREMENT," +
                "    description TEXT NOT NULL," +
                "    ip CHAR(16) NOT NULL," +
                "    endpoint TEXT NOT NULL," +
                "    requested_at TIMESTAMP NOT NULL," +
                "    PRIMARY KEY(id)" +
                ")");

        // Table: subscription
        this.stmt.executeUpdate("CREATE TABLE IF NOT EXISTS subscription (" +
                "    creator_id INT NOT NULL," +
                "    subscriber_id INT NOT NULL," +
                "    status ENUM('pending', 'accepted', 'rejected') NOT NULL DEFAULT 'pending'," +
                "    PRIMARY KEY(creator_id, subscriber_id)" +
                ")");

        // Table: request_film
        this.stmt.executeUpdate("CREATE TABLE IF NOT EXISTS request_film (" +
                "    creator_id INT NOT NULL," +
                "    requestFilm_id INT NOT NULL," +
                "    filmName VARCHAR(255) NOT NULL," +
                "    description TEXT NOT NULL," +
                "    film_path VARCHAR(256) NOT NULL," +
                "    film_poster VARCHAR(256) NOT NULL," +
                "    film_header VARCHAR(256) NOT NULL," +
                "    date_release TIMESTAMP NOT NULL," +
                "    duration INT NOT NULL," +
                "    PRIMARY KEY(creator_id, requestFilm_id)" +
                ")");
    }


    public ResultSet executeQuery(String query) throws SQLException {
        return stmt.executeQuery(query);
    }

    public int executeUpdate(String query) throws SQLException {
        return stmt.executeUpdate(query);
    }

    public void closeConnection() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }

    public PreparedStatement prepareStatement(String query) throws SQLException {
        return conn.prepareStatement(query);
    }
}
