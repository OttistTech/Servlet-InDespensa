package org.example.servletsindespensa.dao;

import org.example.servletsindespensa.util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class CategoriesDAO {
   private final Random rd = new Random();
   private final DbConnection connection = new DbConnection();

   // Method to insert a new category
   public int insert(String name) {
      int id = rd.nextInt(10000)+1;
      try (Connection conn = connection.connect()) {
         // Check if the ID already exists
         try (PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM CATEGORIES WHERE CATEGORY_ID = ?")) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
               return 0; // ID already exists
            }
         }

         // Prepare and execute the insertion into the database
         try (PreparedStatement pstmt = conn.prepareStatement("INSERT INTO CATEGORIES (CATEGORY_NAME, CATEGORY_ID) VALUES (?, ?)")) {
            pstmt.setString(1, name);
            pstmt.setInt(2, id);
            return pstmt.executeUpdate() > 0 ? 1 : 0; // Return 1 if successful, otherwise 0
         }
      } catch (SQLException sqe) {
         sqe.printStackTrace(); // Print the error if an exception occurs
         return -1; // Return -1 in case of error
      }
   }

   // Method to delete a category by its ID
   public int delete(int id) {
      try (Connection conn = connection.connect()) {
         // Check if the ID exists
         try (PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM CATEGORIES WHERE CATEGORY_ID = ?")) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) {
               return 0; // ID does not exist
            }
         }

         // Prepare and execute the deletion
         try (PreparedStatement pstmt = conn.prepareStatement("DELETE FROM CATEGORIES WHERE CATEGORY_ID = ?")) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0 ? 1 : 0; // Return 1 if successful, otherwise 0
         }
      } catch (SQLException sqe) {
         sqe.printStackTrace(); // Print the error if an exception occurs
         return -1; // Return -1 in case of error
      }
   }

   // Method to update a category's name by its ID
   public int update(String name, int id) {
      try (Connection conn = connection.connect()) {
         // Check if the ID exists
         try (PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM CATEGORIES WHERE CATEGORY_ID = ?")) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) {
               return 0; // ID does not exist
            }
         }

         // Prepare and execute the update
         try (PreparedStatement pstmt = conn.prepareStatement("UPDATE CATEGORIES SET CATEGORY_NAME = ? WHERE CATEGORY_ID = ?")) {
            pstmt.setString(1, name); // Set the new category name
            pstmt.setInt(2, id); // Set the ID of the category to update
            return pstmt.executeUpdate() > 0 ? 1 : 0; // Return 1 if successful, otherwise 0
         }
      } catch (SQLException sqle) {
         sqle.printStackTrace(); // Print the error if an exception occurs
         return -1; // Return -1 in case of error
      }
   }

   // Method to read and retrieve all categories
   public ResultSet read() {
      try (Connection conn = connection.connect()) {
         PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM CATEGORIES ORDER BY CATEGORY_ID");
         return pstmt.executeQuery(); // Return the result set for further use
      } catch (SQLException sqe) {
         sqe.printStackTrace(); // Print the error if an exception occurs
         return null; // Return null in case of error
      }
   }
}
