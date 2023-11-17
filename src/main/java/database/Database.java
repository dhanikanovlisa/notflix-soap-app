package database;

import java.sql.*;
import java.util.Date;

import enums.Status;

public class Database {
    private String host = System.getenv("DB_HOST");
    private String port = System.getenv("DB_PORT");
    private String db = System.getenv("DB_NAME");
    private String user  = System.getenv("DB_USER");
    private String pass = System.getenv("DB_PASSWORD");
    private Connection conn;
    private Statement stmt;
    private PreparedStatement psmt;

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
        this.psmt = conn.prepareStatement(query);
        return this.psmt;
    }

    public void bind(Object... args) throws SQLException{
        int i = 1;
        for(Object arg: args){
            this.bind(i, arg);
            i++;
        }
    }

    public void bind(int index, Object value) throws SQLException{
        if(value instanceof java.sql.Timestamp){
            this.psmt.setTimestamp(index, (java.sql.Timestamp)value);
        }
        else if(value instanceof java.sql.Date){
            this.psmt.setDate(index, (java.sql.Date)value);
        }
        else if(value instanceof java.util.Date){
            Date dval = (Date) value;
            this.psmt.setDate(index, new java.sql.Date(dval.getTime()));
        }
        else if(value instanceof Integer){
            this.psmt.setInt(index, (Integer) value);
        }
        else if(value instanceof String){
            this.psmt.setString(index, (String) value);
        }
        else if(value instanceof Boolean){
            this.psmt.setBoolean(index, (Boolean) value);
        }else if(value instanceof Status){
            Status val = (Status) value;
            this.psmt.setString(index, "'"+val.getStatusCode().toUpperCase()+"'");
        }
    }

}
