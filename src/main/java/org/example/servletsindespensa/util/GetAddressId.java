package org.example.servletsindespensa.util;

import org.example.servletsindespensa.dao.CepDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class GetAddressId {

    private DbConnection connection = new DbConnection();
    private PreparedStatement pstmt;
    private Connection conn;
    CepDAO cepDAO=new CepDAO();
    GetAddressIdNew getNew = new GetAddressIdNew();
    GetCepId getCepId1=new GetCepId();

    public Integer getAddressId(String cep) {
        try {
            conn = connection.connect();
            pstmt = conn.prepareStatement("select address_id from address where cep_id=?");
            pstmt.setInt(1,getCepId1.getCepId(cep));
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("address_id");
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


