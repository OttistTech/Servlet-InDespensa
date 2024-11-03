package org.example.servletsindespensa.util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetCategoryId {
    public Integer getCategoryId(String category_name) {
        DbConnection connection = new DbConnection();
        PreparedStatement pstmt;
        Connection conn;
        try {
            conn=connection.connect();

            // Consulta para pegar o address_id
            pstmt = conn.prepareStatement("SELECT category_id FROM categories WHERE category_name = ?");
            pstmt.setString(1, category_name);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("category_id");
            }
            return null;
        } catch (SQLException sqe) {
            return null; // Retorna null em caso de erro
        } finally {
            connection.disconnect();
        }
    }
}

