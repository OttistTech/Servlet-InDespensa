package org.example.servletsindespensa.dao;

import org.example.servletsindespensa.util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;
import java.util.Random;

public class CepDAO {
   private DbConnection connection = new DbConnection();
   private PreparedStatement pstmt;
   private Connection conn;
   Random rd=new Random();

   // Method to insert a new CEP (Postal Code)
   public int insert(String cep) {
      conn=connection.connect();  // Estabelece a conexão com o banco
      try {
         int id = rd.nextInt(10000)+1;
         boolean IDexists;
         do {
            // Verifica se o ID já existe
            pstmt = conn.prepareStatement("SELECT COUNT(*) FROM CEP WHERE CEP_ID= ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            IDexists = rs.getInt(1) > 0;
            pstmt.close();
            rs.close();
         } while (IDexists);

         // Verifica se o CEP já exist
         PreparedStatement pstmt1;
         pstmt1 = conn.prepareStatement("SELECT COUNT(*) FROM CEP WHERE CEP= ?");
         pstmt1.setString(1, cep);
         ResultSet rs1 = pstmt1.executeQuery();
         rs1.next();
         Boolean cepExists = rs1.getInt(1) > 0;
         if (cepExists) {
            return 0;  // CEP já existe, retorna 0
         }

         // Verifica se o formato do CEP está correto
         String regex = "^\\S?[0-9]{5}-?[0-9]{3}\\S?$";
         if (!Pattern.matches(regex, cep)) {
            return 2;  // Formato inválido de CEP, retorna 2
         }

         // Insere o novo CEP
         pstmt = conn.prepareStatement("INSERT INTO CEP (CEP_ID, CEP) VALUES (?, ?)");
         pstmt.setInt(1, id);  // Usando setInt para o ID
         pstmt.setString(2, cep);
         // Chamando o método de inserir endereço
         return pstmt.executeUpdate() > 0 ? 1 : 0;  // Retorna 1 se for bem-sucedido, 0 caso contrário
      } catch (SQLException sqe) {
         sqe.printStackTrace();
         return -1;  // Retorna -1 em caso de erro
      } finally {
         connection.disconnect();  // Desconecta do banco de dados
      }
   }

   // Method to delete a CEP by its ID
   public int deleteCep(String id) {  // Alterado de int para String
      try {
         java.sql.Connection conn = connection.connect();  // Establishes the database connection

         // Check if the ID exists
         pstmt = conn.prepareStatement("SELECT COUNT(*) FROM CEP WHERE CEP_ID = ?");
         pstmt.setString(1, id);  // Usando setString para String
         ResultSet rs = pstmt.executeQuery();
         rs.next();

         if (rs.getInt(1) == 0) {
            rs.close();
            return 0; // ID doesn't exist, return 0
         }
         rs.close();

         // Delete the CEP
         pstmt = conn.prepareStatement("DELETE FROM CEP WHERE CEP_ID = ?");
         pstmt.setString(1, id);  // Usando setString para String
         return pstmt.executeUpdate() > 0 ? 1 : 0; // Return 1 if successful, otherwise 0
      } catch (SQLException sqe) {
         sqe.printStackTrace();
         return -1; // Return -1 in case of error
      } finally {
         connection.disconnect(); // Disconnect from the database
      }
   }

   // Method to update an existing CEP ID
   public int updateCep(String oldCep, String newCep) {  // Alterado de int para String
      try {
         java.sql.Connection conn = connection.connect();  // Establishes the database connection

         // Check if the old CEP exists
         pstmt = conn.prepareStatement("SELECT COUNT(*) FROM CEP WHERE CEP_ID = ?");
         pstmt.setString(1, oldCep);  // Usando setString para String
         ResultSet rs = pstmt.executeQuery();
         rs.next();

         if (rs.getInt(1) == 0) {
            rs.close();
            return 0; // Old CEP doesn't exist, return 0
         }
         rs.close();

         // Update the CEP
         pstmt = conn.prepareStatement("UPDATE CEP SET CEP_ID = ? WHERE CEP_ID = ?");
         pstmt.setString(1, newCep);  // Usando setString para String
         pstmt.setString(2, oldCep);  // Usando setString para String
         return pstmt.executeUpdate() > 0 ? 1 : 0; // Return 1 if successful, otherwise 0
      } catch (SQLException sqle) {
         sqle.printStackTrace();
         return -1; // Return -1 in case of error
      } finally {
         connection.disconnect(); // Disconnect from the database
      }
   }

   // Method to retrieve all CEPs
   public ResultSet readCep() {
      try {
         java.sql.Connection conn = connection.connect();  // Establishes the database connection

         // Retrieve all CEPs
         pstmt = conn.prepareStatement("SELECT * FROM CEP ORDER BY CEP_ID");
         return pstmt.executeQuery();
      } catch (SQLException sqe) {
         sqe.printStackTrace();
         return null;
      } finally {
         connection.disconnect(); // Disconnect from the database
      }
   }
}
