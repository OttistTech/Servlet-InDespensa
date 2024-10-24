package org.example.servletsindespensa.dao;

import org.example.servletsindespensa.util.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CepDAO {
   private DbConnection connection;
   private PreparedStatement pstmt;

   // Method to insert a new CEP (Postal Code)
   public int insert(String id) {  // Alterado de int para String
      try {
         java.sql.Connection conn = connection.connect();  // Establishes the database connection

         // Check if the ID already exists
         pstmt = conn.prepareStatement("SELECT COUNT(*) FROM CEP WHERE CEP_ID = ?");
         pstmt.setString(1, id);  // Usando setString para String
         ResultSet rs = pstmt.executeQuery();
         rs.next();

         if (rs.getInt(1) > 0) {
            rs.close();
            return 0; // ID already exists, return 0
         }
         rs.close();

         // Insert new CEP
         pstmt = conn.prepareStatement("INSERT INTO CEP (CEP_ID) VALUES (?)");
         pstmt.setString(1, id);  // Usando setString para String
         return pstmt.executeUpdate() > 0 ? 1 : 0; // Return 1 if successful, otherwise 0
      } catch (SQLException sqe) {
         sqe.printStackTrace();
         return -1; // Return -1 in case of error
      } finally {
         connection.disconnect(); // Disconnect from the database
      }
   }

   // Method to delete a CEP by its ID
   public int delete(String id) {  // Alterado de int para String
      try {
         java.sql.Connection conn = connection.connect();  // Establishes the database connection

         // Check if the ID exists
         pstmt = conn.prepareStatement("SELECT COUNT(*) FROM CEP WHERE CEP_ID = ?");
         pstmt.setString(1, id);  // Usando setString para String
         ResultSet rs = pstmt.executeQuery();
         rs.next();

         if (rs.getInt(1) == 0) {
            rs.close();
            return 0; // ID doesn't exist, return 0
         }
         rs.close();

         // Delete the CEP
         pstmt = conn.prepareStatement("DELETE FROM CEP WHERE CEP_ID = ?");
         pstmt.setString(1, id);  // Usando setString para String
         return pstmt.executeUpdate() > 0 ? 1 : 0; // Return 1 if successful, otherwise 0
      } catch (SQLException sqe) {
         sqe.printStackTrace();
         return -1; // Return -1 in case of error
      } finally {
         connection.disconnect(); // Disconnect from the database
      }
   }

   // Method to update an existing CEP ID
   public int update(String oldCep, String newCep) {  // Alterado de int para String
      try {
         java.sql.Connection conn = connection.connect();  // Establishes the database connection

         // Check if the old CEP exists
         pstmt = conn.prepareStatement("SELECT COUNT(*) FROM CEP WHERE CEP_ID = ?");
         pstmt.setString(1, oldCep);  // Usando setString para String
         ResultSet rs = pstmt.executeQuery();
         rs.next();

         if (rs.getInt(1) == 0) {
            rs.close();
            return 0; // Old CEP doesn't exist, return 0
         }
         rs.close();

         // Update the CEP
         pstmt = conn.prepareStatement("UPDATE CEP SET CEP_ID = ? WHERE CEP_ID = ?");
         pstmt.setString(1, newCep);  // Usando setString para String
         pstmt.setString(2, oldCep);  // Usando setString para String
         return pstmt.executeUpdate() > 0 ? 1 : 0; // Return 1 if successful, otherwise 0
      } catch (SQLException sqle) {
         sqle.printStackTrace();
         return -1; // Return -1 in case of error
      } finally {
         connection.disconnect(); // Disconnect from the database
      }
   }

   // Method to retrieve all CEPs
   public ResultSet read() {
      try {
         java.sql.Connection conn = connection.connect();  // Establishes the database connection

         // Retrieve all CEPs
         pstmt = conn.prepareStatement("SELECT * FROM CEP ORDER BY CEP_ID");
         return pstmt.executeQuery();
      } catch (SQLException sqe) {
         sqe.printStackTrace();
         return null;
      } finally {
         connection.disconnect(); // Disconnect from the database
      }
   }
}
