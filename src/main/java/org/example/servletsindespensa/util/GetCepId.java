package org.example.servletsindespensa.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetCepId {
    private DbConnection connection = new DbConnection();
    private PreparedStatement pstmt;
    private Connection conn;
    public Integer getCepId(String cep) {
        try {
            conn = connection.connect();
            pstmt = conn.prepareStatement("select cep_id from cep where cep=?");
            pstmt.setString(1, cep);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("cep_id");
            }
            else return 0;
        } catch (SQLException sqe) {
            sqe.printStackTrace(); // Adicionando log de erro
            return null;
        } finally {
            connection.disconnect();
        }
    }
}

