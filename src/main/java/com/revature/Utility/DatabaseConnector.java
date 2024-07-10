package com.revature.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    public static Connection createConnection() throws SQLException {
        // this will be the url that connects to our database
       return DriverManager.getConnection("jdbc:sqlite:src/main/resources/bank.db");
    }

    public static void main(String[] args) {
        try{
            try(Connection connection = createConnection()){
                System.out.println(createConnection());
            }

    }catch(SQLException e){
            System.out.println(e.getMessage());}
    }
}