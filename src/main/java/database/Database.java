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
