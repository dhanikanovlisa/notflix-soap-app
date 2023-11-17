package model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import object.Subscription;

import database.Database;
import enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SubscriptionModel {
    private Database db;
    private String table;
    private static SubscriptionModel instance;
    
    public static SubscriptionModel getInstance() {
        try {
            if (instance == null) {
                instance = new SubscriptionModel(new Database(), "subscription" );
            }
            return instance;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Subscription> getAllSubscription() throws SQLException{
        List<Subscription> ls = new ArrayList<>();

        String query = "SELECT * FROM "+this.table;
        PreparedStatement pstmt = this.db.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        while(rs.next()){
            Subscription sub = new Subscription(rs);
            ls.add(sub);
        }

        return ls;
    }

    public Subscription getSubscriptionStatus(int user_id) throws SQLException{
        String query = "SELECT * FROM "+this.table+" WHERE creator_id = ?";
        PreparedStatement pstmt = this.db.prepareStatement(query);
        this.db.bind(user_id);

        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            return new Subscription(rs);
        }
        return null;
    }

    public List<Subscription> getSubscriptionsByStatus(Status state) throws SQLException{
        List<Subscription> ls = new ArrayList<>();

        String query = "SELECT * FROM "+this.table+" WHERE status = '"+state.getStatusCode().toUpperCase()+"'";
        PreparedStatement pstmt = this.db.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        while(rs.next()){
            Subscription sub = new Subscription(rs);
            ls.add(sub);
        }

        return ls;
    }
    
    public String requestSubscription(int user_id) throws SQLException{
        String queryCheck = "SELECT * FROM "+this.table+" WHERE creator_id = ?";
        PreparedStatement pstmtCheck = this.db.prepareStatement(queryCheck);
        this.db.bind(user_id);
        ResultSet rs = pstmtCheck.executeQuery();
        
        String query;
        if(rs.next()){
            query = "UPDATE "+this.table+" SET status = '"+Status.PENDING.getStatusCode()+"' WHERE creator_id = ?";
        }
        else{
            query = "INSERT INTO "+this.table+" (creator_id) VALUES (?)";
        }
        PreparedStatement pstmt = this.db.prepareStatement(query);
        this.db.bind(user_id);
        
        int rowsAffected = pstmt.executeUpdate();
        return rowsAffected + " rows affected";
    }

    public String updateSubscriptionState(int user_id, Status new_state) throws SQLException{
        String query = "UPDATE "+this.table+" SET status = '"+new_state.getStatusCode()+"' WHERE creator_id = ?";

        PreparedStatement pstmt = this.db.prepareStatement(query);
        this.db.bind(user_id);

        int rowsAffected = pstmt.executeUpdate();
        return rowsAffected + " rows affected";
    }

}
