package org.example.servletsindespensa.dao;

import org.example.servletsindespensa.util.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class CategoriesDAO {
   Random rd = new Random();
   // Attributes for managing database connection and prepared statements
   private DbConnection connection = new DbConnection();
   private PreparedStatement pstmt;

   // Method to insert a new category
   public int insert(String name) {
      int id = rd.nextInt(1,10000);
      try {
         java.sql.Connection conn = connection.connect(); // Connect to the database

         // Check if the ID already exists
         pstmt = conn.prepareStatement("SELECT COUNT(*) FROM CATEGORIES WHERE CATEGORY_ID = ?");
         pstmt.setInt(1, id);
         ResultSet rs = pstmt.executeQuery();
         rs.next();
         if (rs.getInt(1) > 0) {
            return 0; // ID already exists
         }

         // Prepare and execute the insertion into the database
         pstmt = conn.prepareStatement("INSERT INTO CATEGORIES (CATEGORY_NAME, CATEGORY_ID) VALUES (?, ?)");
         pstmt.setString(1, name);
         pstmt.setInt(2, id);
         return pstmt.executeUpdate() > 0 ? 1 : 0; // Return 1 if successful, otherwise 0
      } catch (SQLException sqe) {
         sqe.printStackTrace(); // Print the error if an exception occurs
         return -1; // Return -1 in case of error
      } finally {
         connection.disconnect(); // Ensure disconnection from the database
      }
   }

   // Method to delete a category by its ID
   public int delete(int id) {
      try {
         java.sql.Connection conn = connection.connect();  // Connect to the database

         // Check if the ID exists
         pstmt = conn.prepareStatement("SELECT COUNT(*) FROM CATEGORIES WHERE CATEGORY_ID = ?");
         pstmt.setInt(1, id);
         ResultSet rs = pstmt.executeQuery();
         rs.next();
         if (rs.getInt(1) == 0) {
            return 0; // ID does not exist
         }

         // Prepare and execute the deletion
         pstmt = conn.prepareStatement("DELETE FROM CATEGORIES WHERE CATEGORY_ID = ?");
         pstmt.setInt(1, id);
         return pstmt.executeUpdate() > 0 ? 1 : 0; // Return 1 if successful, otherwise 0
      } catch (SQLException sqe) {
         sqe.printStackTrace(); // Print the error if an exception occurs
         return -1; // Return -1 in case of error
      } finally {
         connection.disconnect(); // Ensure disconnection from the database
      }
   }

   // Method to update a category's name by its ID
   public int update(String name, int id) {
      try {
         java.sql.Connection conn = connection.connect();  // Connect to the database

         // Check if the ID exists
         pstmt = conn.prepareStatement("SELECT COUNT(*) FROM CATEGORIES WHERE CATEGORY_ID = ?");
         pstmt.setInt(1, id);
         ResultSet rs = pstmt.executeQuery();
         rs.next();
         if (rs.getInt(1) == 0) {
            return 0; // ID does not exist
         }

         // Prepare and execute the update
         pstmt = conn.prepareStatement("UPDATE CATEGORIES SET CATEGORY_NAME = ? WHERE CATEGORY_ID = ?");
         pstmt.setString(1, name); // Set the new category name
         pstmt.setInt(2, id); // Set the ID of the category to update
         return pstmt.executeUpdate() > 0 ? 1 : 0; // Return 1 if successful, otherwise 0
      } catch (SQLException sqle) {
         sqle.printStackTrace(); // Print the error if an exception occurs
         return -1; // Return -1 in case of error
      } finally {
         connection.disconnect(); // Disconnect from the database
      }
   }

   // Method to read and retrieve all categories
   public ResultSet read() {
      try {
         java.sql.Connection conn = connection.connect();  // Connect to the database

         pstmt = conn.prepareStatement("SELECT * FROM CATEGORIES ORDER BY CATEGORY_ID");
         return pstmt.executeQuery(); // Return the result set for further use
      } catch (SQLException sqe) {
         sqe.printStackTrace(); // Print the error if an exception occurs
      return null; // Return null in case of error
      } finally {
         connection.disconnect(); // Disconnect from the database
      }
   }
}
