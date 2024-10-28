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

   // Método para inserir uma nova categoria
   public int insertCategories(String name) {
      int id = rd.nextInt(10000) + 1;
      try (Connection conn = connection.connect()) {
         // Verificar se o ID já existe
         try (PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM CATEGORIES WHERE CATEGORY_ID = ?")) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
               return 0; // ID já existe
            }
         }

         // Preparar e executar a inserção no banco de dados
         try (PreparedStatement pstmt = conn.prepareStatement("INSERT INTO CATEGORIES (CATEGORY_NAME, CATEGORY_ID) VALUES (?, ?)")) {
            pstmt.setString(1, name);
            pstmt.setInt(2, id);
            return pstmt.executeUpdate() > 0 ? 1 : 0; // Retornar 1 se bem-sucedido, caso contrário 0
         }
      } catch (SQLException sqe) {
         sqe.printStackTrace(); // Imprimir o erro se uma exceção ocorrer
         return -1; // Retornar -1 em caso de erro
      }
   }

   // Método para deletar uma categoria pelo seu ID
   public int delete(int id) {
      try (Connection conn = connection.connect()) {
         // Verificar se o ID existe
         try (PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM CATEGORIES WHERE CATEGORY_ID = ?")) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) {
               return 0; // ID não existe
            }
         }

         // Preparar e executar a exclusão
         try (PreparedStatement pstmt = conn.prepareStatement("DELETE FROM CATEGORIES WHERE CATEGORY_ID = ?")) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0 ? 1 : 0; // Retornar 1 se bem-sucedido, caso contrário 0
         }
      } catch (SQLException sqe) {
         sqe.printStackTrace(); // Imprimir o erro se uma exceção ocorrer
         return -1; // Retornar -1 em caso de erro
      }
   }

   // Método para atualizar o nome de uma categoria pelo seu ID
   public int updateCategories(String name, int id) {
      try (Connection conn = connection.connect()) {
         // Verificar se o ID existe
         try (PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM CATEGORIES WHERE CATEGORY_ID = ?")) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) {
               return 0; // ID não existe
            }
         }

         // Preparar e executar a atualização
         try (PreparedStatement pstmt = conn.prepareStatement("UPDATE CATEGORIES SET CATEGORY_NAME = ? WHERE CATEGORY_ID = ?")) {
            pstmt.setString(1, name); // Definir o novo nome da categoria
            pstmt.setInt(2, id); // Definir o ID da categoria a ser atualizada
            return pstmt.executeUpdate() > 0 ? 1 : 0; // Retornar 1 se bem-sucedido, caso contrário 0
         }
      } catch (SQLException sqle) {
         sqle.printStackTrace(); // Imprimir o erro se uma exceção ocorrer
         return -1; // Retornar -1 em caso de erro
      }
   }

   // Método para ler e recuperar todas as categorias
   public ResultSet readCategories() {
      try (Connection conn = connection.connect()) {
         PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM CATEGORIES ORDER BY CATEGORY_ID");
         return pstmt.executeQuery(); // Retornar o conjunto de resultados para uso posterior
      } catch (SQLException sqe) {
         sqe.printStackTrace(); // Imprimir o erro se uma exceção ocorrer
         return null; // Retornar null em caso de erro
      }
   }
}
