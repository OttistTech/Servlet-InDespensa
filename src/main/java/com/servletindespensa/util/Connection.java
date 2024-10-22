package com.servletindespensa.util;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connection {
   private java.sql.Connection conn; // Connection object to interact with the database
   private PreparedStatement pstmt;
   private ResultSet rs;

   // Method to connect to the PostgreSQL database
   public java.sql.Connection connect() {
      try {
         // Load the PostgreSQL driver
         Class.forName("org.postgresql.Driver");
         // Establish a connection using environment variables for host, user, and password
         conn = DriverManager.getConnection(
                 System.getenv("DB_HOST"), // Database host (URL)
                 System.getenv("DB_USER"), // Database user
                 System.getenv("DB_PASSWORD") // Database password
         );
      } catch (ClassNotFoundException | SQLException e) {
         e.printStackTrace(); // Print any error that occurs during the connection attempt
      }finally {
         return conn;
      }
   }

   // Method to disconnect from the PostgreSQL database
   public void disconnect() {
      try {
         if (conn != null && !conn.isClosed()) {
            // Close the connection if it is open
            conn.close();
         }
      } catch (SQLException e) {
         e.printStackTrace(); // Print any error that occurs during disconnection
      }
   }
}
