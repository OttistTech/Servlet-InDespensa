package org.example.servletsindespensa.dao;

import org.example.servletsindespensa.util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdmDAO {
   // Attributes for managing database connection and statements

   private DbConnection connection; // Custom connection class
   private PreparedStatement pstmt;
   private Connection conn = connection.connect();

   // Method to insert a new record
   public int insertAdm(String name, String pswd, String email) {
      try {
         // Connect to the database
         // Check if the password already exists
         pstmt = conn.prepareStatement("SELECT COUNT(*) FROM ADM WHERE NAME = ?");
         pstmt.setString(1, name);
         ResultSet rs = pstmt.executeQuery();
         rs.next();
         boolean passwordExists = rs.getInt(1) > 0; // Verify if the password is already registered

         if (passwordExists) {
            return 0; // Return 0 if the password already exists
         }

         // Regex to validate the name format (First Last)
         String regexName = "^[A-Z][a-z]* [A-Z][a-z]*$";
         if (!validateInput(name, regexName)) {
            return 0; // Return 0 if the name is invalid
         }

         // Regex to validate the password format
         String regexPassword = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$&])[A-Z0-9a-z%.@#_-]{8,}$";
         if (!validateInput(pswd, regexPassword)) {
            return 0; // Return 0 if the password is invalid
         }

         // Regex to validate the email format
         String regexEmail = "^[a-z]*[^A-Z]*@(gmail|germinare)\\.(com|org)(\\.br)?$";
         if (!validateInput(email, regexEmail)) {
            return 0; // Return 0 if the email is invalid
         }

         // Prepare and execute the insertion
         pstmt = conn.prepareStatement("INSERT INTO ADM (name, pswd, email) VALUES (?, ?, ?)");
         pstmt.setString(1, name);
         pstmt.setString(2, pswd);
         pstmt.setString(3, email);

         return pstmt.executeUpdate() > 0 ? 1 : 0; // Return 1 if insertion is successful, otherwise 0
      } catch (SQLException sqe) {
         sqe.printStackTrace(); // Print the error if insertion fails
         return -1; // Return -1 in case of error
      } finally {
         connection.disconnect(); // Ensure disconnection from the database
      }
   }

   // Method to delete a record
   public int deleteAdm(int id) {
      try {
         // Check if the name exists
         pstmt = conn.prepareStatement("SELECT COUNT(*) FROM ADM WHERE ADM_ID = ?");
         pstmt.setInt(1, id);
         ResultSet rs = pstmt.executeQuery();
         rs.next();
         boolean nameExists = rs.getInt(1) > 0; // Verify if the name exists

         if (!nameExists) {
            return 0; // Return 0 if the name does not exist
         }

         // Delete the corresponding record
         pstmt = conn.prepareStatement("DELETE FROM ADM WHERE ADM_ID = ?");
         pstmt.setInt(1, id);
         return pstmt.executeUpdate() > 0 ? 1 : 0; // Return 1 if deletion is successful, otherwise 0
      } catch (SQLException sqe) {
         sqe.printStackTrace(); // Print the error if deletion fails
         return -1; // Return -1 in case of error
      } finally {
         connection.disconnect(); // Ensure disconnection from the database
      }
   }

   // Method to update a record
   public int updateAdm(String name, String newPassword) {
      try {
         // Check if the name exists
         pstmt = conn.prepareStatement("SELECT COUNT(*) FROM ADM WHERE NAME = ?");
         pstmt.setString(1, name);
         ResultSet rs = pstmt.executeQuery();
         rs.next();
         boolean nameExists = rs.getInt(1) > 0; // Verify if the name exists

         if (!nameExists) {
            return 0; // Return 0 if the name does not exist
         }

         // Validate the new password format
         String regexPassword = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$&])[A-Z0-9a-z%.@#_-]{8,}$";
         if (!validateInput(newPassword, regexPassword)) {
            return 0; // Return 0 if the new password is invalid
         }

         // Update the password
         pstmt = conn.prepareStatement("UPDATE ADM SET PASSWORD = ? WHERE NAME = ?");
         pstmt.setString(1, newPassword);
         pstmt.setString(2, name);

         return pstmt.executeUpdate() > 0 ? 1 : 0; // Return 1 if update is successful, otherwise 0
      } catch (SQLException sqle) {
         sqle.printStackTrace(); // Print the error if update fails
         return -1; // Return -1 in case of error
      } finally {
         connection.disconnect(); // Ensure disconnection from the database
      }
   }

   // Method to read records
   public ResultSet readAdm() {
      try {
         pstmt = conn.prepareStatement("SELECT ADM_ID, NOME FROM ADM ORDER BY NAME");
         return pstmt.executeQuery(); // Return the ResultSet (use with caution to avoid disconnection issues)
      } catch (SQLException sqe) {
         sqe.printStackTrace(); // Print the error if reading fails
         return null; // Return null in case of error
      }finally {
         connection.disconnect(); // Disconnect from the database
      }
   }

   // Helper method to validate input against a regex pattern
   private boolean validateInput(String input, String regex) {
      Pattern pattern = Pattern.compile(regex);
      Matcher matcher = pattern.matcher(input);
      return matcher.matches(); // Return true if the input matches the regex
   }
}