package org.example.servletsindespensa.dao;

import org.example.servletsindespensa.util.DbConnection;
import org.example.servletsindespensa.util.GetCepId;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
public class AddressDAO {
    GetCepId getCepId=new GetCepId();
    private ResultSet rs;
    private Connection conn;
    private final DbConnection dbConnection = new DbConnection(); // Usando dbConnection para gerenciar a conexão
    private PreparedStatement pstmt;
    private final Random rd = new Random();

    public int inserir(String cep){
        conn=dbConnection.connect();
        try {

            boolean idExists;
            int id=rd.nextInt(10000)+1;
            do {
                pstmt=conn.prepareStatement("SELECT COUNT(*) FROM ADDRESS WHERE ADDRESS_ID = ?");
                pstmt.setInt(1,id);
                ResultSet rs= pstmt.executeQuery();
                rs.next();
                idExists=rs.getInt(1)>(0);
                pstmt.close();
                rs.close();
            }while (idExists);
            pstmt = conn.prepareStatement("INSERT INTO ADDRESS (address_id,cep_id)VALUES (?,?)");
            pstmt.setInt(1,id);
            int cep_id=getCepId.getCepId(cep);
            pstmt.setInt(2,cep_id);
            if (pstmt.executeUpdate()>0){
                return 1;
            }
            else return 0;
        }catch (SQLException sqe){
            sqe.printStackTrace();
            return -1;
        }
        finally {
            dbConnection.disconnect();
        }
    }
    public int remove(int id){
        conn=dbConnection.connect();

        try {
            boolean idExists;
            do {
                id=rd.nextInt(10000)+1;
                pstmt=conn.prepareStatement("SELECT COUNT(*) FROM ADDRESS WHERE ADDRESS_ID = ?");
                pstmt.setInt(1,id);
                ResultSet rs= pstmt.executeQuery();
                rs.next();
                idExists=rs.getInt(1)==(0);
            }while (idExists);
            String remover = "DELETE FROM ADDRESS WHERE ADDRRESS_ID = ?";
            pstmt = conn.prepareStatement(remover);
            pstmt.setInt(1,id);
            if (pstmt.executeUpdate()>0){
                return 1;
            }
            else return 0;
        } catch (SQLException sqe){
            sqe.printStackTrace();
            return -1;
        }
        finally {
            dbConnection.disconnect();
        }
    }
    public ResultSet consult() {
        conn=dbConnection.connect();

        try {
            pstmt = conn.prepareStatement("SELECT * FROM ADDRESS ORDER BY ADDRESS_ID");
            ResultSet rset = pstmt.executeQuery();
            dbConnection.disconnect();
            return rset;
        } catch (SQLException sqe) {
            sqe.printStackTrace();
        }
        return null;
    }
    public int atualizar(String cep,int address_id) {
        conn=dbConnection.connect();

        try {
            pstmt = conn.prepareStatement("SELECT COUNT(*) FROM ADRESS WHERE oddress_id = ?");
            pstmt.setInt(1, address_id);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            if (rs.getInt(1) == 0) {
                return 0;
            }
            pstmt = conn.prepareStatement("UPDATE order SET cep=? WHERE oddres_id = ?");
            pstmt.setString(1,cep);
            pstmt.setInt(2,address_id);
            if (pstmt.executeUpdate() > 0) {
                return 1;  // Funcionou
            } else {
                return 0;  // Não teve alteração, order_id não existe
            }
        } catch (SQLException sqe) {
            sqe.printStackTrace();  // Em caso de erro SQL, imprime a pilha de execução
            return -1;  // Retorna -1 indicando que ocorreu um erro
        } finally {
            // Garante que a conexão com o banco de dados será encerrada ao final
            dbConnection.disconnect();
        }
    }

}


