package org.example.servletsindespensa.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetAddressIdNew {
    private DbConnection connection = new DbConnection();
    private PreparedStatement pstmt;
    private Connection conn;
    public Integer getAddressIdNew(String cep) {
        try {
            conn = connection.connect();
            pstmt = conn.prepareStatement("select address_id from address where cep_id=(select cep_id from cep where cep=?)");
            pstmt.setString(1, cep);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("address_id");
            }
            else return null;
        } catch (SQLException sqe) {
            sqe.printStackTrace(); // Adicionando log de erro
            return null;
        } finally {
            connection.disconnect();
        }
    }

}
