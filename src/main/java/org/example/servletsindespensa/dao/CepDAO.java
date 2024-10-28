package org.example.servletsindespensa.dao;

import org.example.servletsindespensa.util.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CepDAO {
   private DbConnection connection = new DbConnection();
   private PreparedStatement pstmt;

   // Método para inserir um novo CEP (Código Postal)
   public int insertCep(String id) {  // Alterado de int para String
      try {
         java.sql.Connection conn = connection.connect();  // Estabelece a conexão com o banco de dados

         // Verificar se o ID já existe
         pstmt = conn.prepareStatement("SELECT COUNT(*) FROM CEP WHERE CEP_ID = ?");
         pstmt.setString(1, id);  // Usando setString para String
         ResultSet rs = pstmt.executeQuery();
         rs.next();

         if (rs.getInt(1) > 0) {
            rs.close();
            return 0; // ID já existe, retorna 0
         }
         rs.close();

         // Inserir novo CEP
         pstmt = conn.prepareStatement("INSERT INTO CEP (CEP_ID) VALUES (?)");
         pstmt.setString(1, id);  // Usando setString para String
         return pstmt.executeUpdate() > 0 ? 1 : 0; // Retornar 1 se bem-sucedido, caso contrário 0
      } catch (SQLException sqe) {
         sqe.printStackTrace();
         return -1; // Retornar -1 em caso de erro
      } finally {
         connection.disconnect(); // Desconectar do banco de dados
      }
   }

   // Método para deletar um CEP pelo seu ID
   public int deleteCep(String id) {  // Alterado de int para String
      try {
         java.sql.Connection conn = connection.connect();  // Estabelece a conexão com o banco de dados

         // Verificar se o ID existe
         pstmt = conn.prepareStatement("SELECT COUNT(*) FROM CEP WHERE CEP_ID = ?");
         pstmt.setString(1, id);  // Usando setString para String
         ResultSet rs = pstmt.executeQuery();
         rs.next();

         if (rs.getInt(1) == 0) {
            rs.close();
            return 0; // ID não existe, retorna 0
         }
         rs.close();

         // Deletar o CEP
         pstmt = conn.prepareStatement("DELETE FROM CEP WHERE CEP_ID = ?");
         pstmt.setString(1, id);  // Usando setString para String
         return pstmt.executeUpdate() > 0 ? 1 : 0; // Retornar 1 se bem-sucedido, caso contrário 0
      } catch (SQLException sqe) {
         sqe.printStackTrace();
         return -1; // Retornar -1 em caso de erro
      } finally {
         connection.disconnect(); // Desconectar do banco de dados
      }
   }

   // Método para atualizar um ID de CEP existente
   public int updateCep(String oldCep, String newCep) {  // Alterado de int para String
      try {
         java.sql.Connection conn = connection.connect();  // Estabelece a conexão com o banco de dados

         // Verificar se o CEP antigo existe
         pstmt = conn.prepareStatement("SELECT COUNT(*) FROM CEP WHERE CEP_ID = ?");
         pstmt.setString(1, oldCep);  // Usando setString para String
         ResultSet rs = pstmt.executeQuery();
         rs.next();

         if (rs.getInt(1) == 0) {
            rs.close();
            return 0; // CEP antigo não existe, retorna 0
         }
         rs.close();

         // Atualizar o CEP
         pstmt = conn.prepareStatement("UPDATE CEP SET CEP_ID = ? WHERE CEP_ID = ?");
         pstmt.setString(1, newCep);  // Usando setString para String
         pstmt.setString(2, oldCep);  // Usando setString para String
         return pstmt.executeUpdate() > 0 ? 1 : 0; // Retornar 1 se bem-sucedido, caso contrário 0
      } catch (SQLException sqle) {
         sqle.printStackTrace();
         return -1; // Retornar -1 em caso de erro
      } finally {
         connection.disconnect(); // Desconectar do banco de dados
      }
   }

   // Método para recuperar todos os CEPs
   public ResultSet readCep() {
      try {
         java.sql.Connection conn = connection.connect();  // Estabelece a conexão com o banco de dados

         // Recuperar todos os CEPs
         pstmt = conn.prepareStatement("SELECT * FROM CEP ORDER BY CEP_ID");
         return pstmt.executeQuery();
      } catch (SQLException sqe) {
         sqe.printStackTrace();
         return null;
      } finally {
         connection.disconnect(); // Desconectar do banco de dados
      }
   }
}
