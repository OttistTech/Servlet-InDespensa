package org.example.servletsindespensa.dao;

import org.example.servletsindespensa.util.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAO {
    // Class attributes: PreparedStatement, and the custom connection class
    private DbConnection connection;
    private PreparedStatement pstmt;

    // Method to insert a new product into the database
    public int insert(int id, String desc, long barcode, String brand, String name, String type, double weight_volume) {
        try {

            java.sql.Connection conn = connection.connect(); // Open connection to the database

            // Check if the product_id already exists in the database
            pstmt = conn.prepareStatement("SELECT COUNT(*) FROM PRODUCT WHERE PRODUCT_ID = ?");
            pstmt.setInt(1, id); // Set product ID parameter
            ResultSet rs = pstmt.executeQuery(); // Execute the query
            rs.next();

            // If the product ID exists, return 0 to indicate failure
            if (rs.getInt(1) > 0) {
                rs.close(); // Close ResultSet
                return 0;
            }
            rs.close(); // Close ResultSet if the ID doesn't exist

            // Prepare the SQL statement to insert the new product
            pstmt = conn.prepareStatement("INSERT INTO PRODUCT (PRODUCT_ID, DESCRIPTION, BARCODE, BRAND, NAME, TYPE, WEIGHT_VOLUME) VALUES (?, ?, ?, ?, ?, ?, ?)");
            pstmt.setInt(1, id);
            pstmt.setString(2, desc);
            pstmt.setLong(3, barcode);
            pstmt.setString(4, brand);
            pstmt.setString(5, name);
            pstmt.setString(6, type);
            pstmt.setDouble(7, weight_volume);

            // Execute the insertion and return success (1) or failure (0)
            if (pstmt.executeUpdate() > 0) {
                return 1;  // Insert was successful
            } else {
                return 0;  // Insert failed
            }
        } catch (SQLException sqe) {
            sqe.printStackTrace(); // Print SQL errors
            return -1;  // Return -1 to indicate a database error
        } finally {
            connection.disconnect(); // Disconnect from the database
        }
    }

    // Method to delete a product from the database by its ID
    public int delete(int id) {
        try {
            java.sql.Connection conn = connection.connect(); // Open connection to the database

            // Check if the product_id exists
            pstmt = conn.prepareStatement("SELECT COUNT(*) FROM PRODUCT WHERE PRODUCT_ID = ?");
            pstmt.setInt(1, id); // Set product ID parameter
            ResultSet rs = pstmt.executeQuery(); // Execute the query
            rs.next();

            // If the product ID does not exist, return 0 to indicate failure
            if (rs.getInt(1) == 0) {
                rs.close(); // Close ResultSet
                return 0;
            }
            rs.close(); // Close ResultSet if the ID exists

            // Prepare SQL statement to delete the product
            String remover = "DELETE FROM PRODUCT WHERE PRODUCT_ID = ?";
            pstmt = conn.prepareStatement(remover);
            pstmt.setInt(1, id);

            // Execute the deletion and return success (1) or failure (0)
            if (pstmt.executeUpdate() > 0) {
                return 1;  // Deletion was successful
            } else {
                return 0;  // No rows were deleted
            }
        } catch (SQLException sqe) {
            sqe.printStackTrace(); // Print SQL errors
            return -1;  // Return -1 to indicate a database error
        } finally {
            connection.disconnect(); // Disconnect from the database
        }
    }

    // Method to update the weight_volume of a product in the database by its ID
    public int update(double weight_volume, int id) {
        try {
            java.sql.Connection conn = connection.connect(); // Open connection to the database

            // Check if the product_id exists in the database
            pstmt = conn.prepareStatement("SELECT COUNT(*) FROM PRODUCT WHERE PRODUCT_ID = ?");
            pstmt.setInt(1, id); // Set product ID parameter
            ResultSet rs = pstmt.executeQuery(); // Execute the query
            rs.next();

            // If the product ID does not exist, return 0 to indicate failure
            if (rs.getInt(1) == 0) {
                rs.close(); // Close ResultSet
                return 0;
            }
            rs.close(); // Close ResultSet if the ID exists

            // Prepare SQL statement to update the product's weight_volume
            pstmt = conn.prepareStatement("UPDATE PRODUCT SET WEIGHT_VOLUME = ? WHERE PRODUCT_ID = ?");
            pstmt.setDouble(1, weight_volume); // Set the new weight_volume
            pstmt.setInt(2, id); // Set the product ID

            // Execute the update and return success (1) or failure (0)
            if (pstmt.executeUpdate() > 0) {
                return 1;  // Update was successful
            } else {
                return 0;  // No rows were updated
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace(); // Print SQL errors
            return -1;  // Return -1 to indicate a database error
        } finally {
            connection.disconnect(); // Disconnect from the database
        }
    }

    // Method to read all products from the database
    public ResultSet read() {
        ResultSet rset = null;
        try {
            java.sql.Connection conn = connection.connect(); // Open connection to the database

            // Prepare SQL query to select all products ordered by their ID
            pstmt = conn.prepareStatement("SELECT * FROM PRODUCT ORDER BY PRODUCT_ID");
            rset = pstmt.executeQuery(); // Execute the query and return the result set
        } catch (SQLException sqe) {
            sqe.printStackTrace(); // Print SQL errors
        }finally {
            connection.disconnect(); // Disconnect from the database
        }
            return rset;  // Return the result set containing the products
    }
}
