package org.example.servletsindespensa.dao;

import org.example.servletsindespensa.util.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class TagDAO {
   Random rd=new Random();
   // Class attributes: Connection, PreparedStatement, and custom connection class
   private DbConnection connection = new DbConnection();
   private PreparedStatement pstmt;

   // Method to insert a new tag into the database
   public int insert(String description) {
      try {
         int id=rd.nextInt(10000)+1;
         java.sql.Connection conn = connection.connect(); // Connects to the database
         Boolean idExists;
         // Check if the tag ID already exists
         do {
            pstmt = conn.prepareStatement("SELECT COUNT(*) FROM TAG WHERE ID = ?");
            pstmt.setInt(1, id); // Set the tag ID parameter
            ResultSet rs = pstmt.executeQuery(); // Execute the query
            rs.next();
            idExists = rs.getInt(1) > 0;
            // Close ResultSet
            // Tag ID already exists, return 0 to indicate failure
            rs.close();
            // Close ResultSet if ID does not exist
         }while (idExists);
         // Prepare the SQL statement to insert a new tag
         pstmt = conn.prepareStatement("INSERT INTO TAG (id, description) VALUES (?, ?)");
         pstmt.setInt(1, id); // Set the tag ID
         pstmt.setString(2, description); // Set the tag description

         // Return 1 if insertion is successful, otherwise return 0
         return pstmt.executeUpdate() > 0 ? 1 : 0;
      } catch (SQLException sqe) {
         sqe.printStackTrace(); // Print SQL errors during insertion
         return -1; // Return -1 to indicate a database error
      } finally {
         connection.disconnect();// Disconnect from the database
      }
   }

   // Method to remove a tag by its ID
   public int delete(int id) {
      try {
         java.sql.Connection conn = connection.connect(); // Connects to the database

         // Check if the tag ID exists before attempting to delete
         pstmt = conn.prepareStatement("SELECT COUNT(*) FROM TAG WHERE ID = ?");
         pstmt.setInt(1, id); // Set the tag ID parameter
         ResultSet rs = pstmt.executeQuery(); // Execute the query
         rs.next();

         if (rs.getInt(1) == 0) {
            rs.close(); // Close ResultSet
            return 0; // Tag ID does not exist, return 0 to indicate failure
         }
         rs.close(); // Close ResultSet if ID exists

         // Prepare the SQL statement to delete the tag
         pstmt = conn.prepareStatement("DELETE FROM TAG WHERE ID = ?");
         pstmt.setInt(1, id); // Set the tag ID

         // Return 1 if deletion is successful, otherwise return 0
         return pstmt.executeUpdate() > 0 ? 1 : 0;
      } catch (SQLException sqe) {
         sqe.printStackTrace(); // Print SQL errors during deletion
         return -1; // Return -1 to indicate a database error
      } finally {
         connection.disconnect();// Disconnect from the database
      }
   }

   // Method to update a tag's description by its ID
   public int update(String description, int id) {
      try {
         java.sql.Connection conn = connection.connect(); // Connects to the database

         // Check if the tag ID exists before attempting to update
         pstmt = conn.prepareStatement("SELECT COUNT(*) FROM TAG WHERE ID = ?");
         pstmt.setInt(1, id); // Set the tag ID parameter
         ResultSet rs = pstmt.executeQuery(); // Execute the query
         rs.next();

         if (rs.getInt(1) == 0) {
            rs.close(); // Close ResultSet
            return 0; // Tag ID does not exist, return 0 to indicate failure
         }
         rs.close(); // Close ResultSet if ID exists

         // Prepare the SQL statement to update the tag description
         pstmt = conn.prepareStatement("UPDATE TAG SET DESCRIPTION = ? WHERE ID = ?");
         pstmt.setString(1, description); // Set the new description
         pstmt.setInt(2, id); // Set the tag ID

         // Return 1 if update is successful, otherwise return 0
         return pstmt.executeUpdate() > 0 ? 1 : 0;
      } catch (SQLException sqle) {
         sqle.printStackTrace(); // Print SQL errors during update
         return -1; // Return -1 to indicate a database error
      } finally {
         connection.disconnect();// Disconnect from the database
      }
   }

   // Method to fetch all tags from the database
   public ResultSet read() {
      ResultSet rset = null;
      try {
         java.sql.Connection conn = connection.connect(); // Connects to the database

         // Prepare the SQL query to select all tags ordered by ID
         pstmt = conn.prepareStatement("SELECT * FROM TAG ORDER BY ID");
         rset = pstmt.executeQuery(); // Execute the query and store the result set
      } catch (SQLException sqe) {
         sqe.printStackTrace(); // Print SQL errors during query execution
      }finally{
         connection.disconnect();// Disconnect from the database
         return rset; // Return the result set containing the tags
      }
   }
}
