package org.example.servletsindespensa.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
   Connection conn; // Connection object to interact with the database

   // Method to connect to the PostgreSQL database
   public java.sql.Connection connect() {
      try {
         // Load the PostgreSQL driver
         Class.forName("org.postgresql.Driver");
         // Establish a connection using environment variables for host, user, and password
         conn = DriverManager.getConnection(
                 System.getenv("DB_HOST2"), // Database host (URL)
                 System.getenv("DB_USER2"), // Database user
                 System.getenv("DB_PASSWORD2") // Database password
         );

      } catch (ClassNotFoundException | SQLException e) {
         e.printStackTrace(); // Print any error that occurs during the connection attempt
         return null;
      }
      return conn; // Retorne a conexão corretamente
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
