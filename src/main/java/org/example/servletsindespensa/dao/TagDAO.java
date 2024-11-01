package org.example.servletsindespensa.dao;

import org.example.servletsindespensa.util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class TagDAO {
   private final Random rd = new Random();
   private final DbConnection connection = new DbConnection();
   private PreparedStatement pstmt;

   // Método para inserir uma nova tag no banco de dados
   public int insertTag(String description) {
      Connection conn = null;
      try {
         int id = rd.nextInt(10000) + 1;
         conn = connection.connect(); // Conecta ao banco de dados
         boolean idExists;

         // Verifica se o ID da tag já existe
         do {
            pstmt = conn.prepareStatement("SELECT COUNT(*) FROM TAG WHERE ID = ?");
            pstmt.setInt(1, id); // Define o parâmetro do ID da tag
            ResultSet rs = pstmt.executeQuery(); // Executa a consulta
            if (rs.next()) {
               idExists = rs.getInt(1) > 0;
            } else {
               idExists = false; // Solução alternativa em caso de resultado vazio
            }
            rs.close(); // Fecha o ResultSet
         } while (idExists);

         // Prepara a declaração SQL para inserir uma nova tag
         pstmt = conn.prepareStatement("INSERT INTO TAG (id, description) VALUES (?, ?)");
         pstmt.setInt(1, id); // Define o ID da tag
         pstmt.setString(2, description); // Define a descrição da tag

         // Retorna 1 se a inserção for bem-sucedida, caso contrário retorna 0
         return pstmt.executeUpdate() > 0 ? 1 : 0;
      } catch (SQLException sqe) {
         sqe.printStackTrace(); // Imprime erros SQL durante a inserção
         return -1; // Retorna -1 para indicar um erro no banco de dados
      } finally {
         if (conn != null) {
            connection.disconnect(); // Desconecta do banco de dados
         }
      }
   }

   // Método para remover uma tag pelo seu ID
   public int deleteTag(int id) {
      Connection conn = null;
      try {
         conn = connection.connect(); // Conecta ao banco de dados

         // Verifica se o ID da tag existe antes de tentar deletar
         pstmt = conn.prepareStatement("SELECT COUNT(*) FROM TAG WHERE ID = ?");
         pstmt.setInt(1, id); // Define o parâmetro do ID da tag
         ResultSet rs = pstmt.executeQuery(); // Executa a consulta
         if (rs.next() && rs.getInt(1) == 0) {
            rs.close(); // Fecha o ResultSet
            return 0; // O ID da tag não existe, retorna 0 para indicar falha
         }
         rs.close(); // Fecha o ResultSet se o ID existir

         // Prepara a declaração SQL para deletar a tag
         pstmt = conn.prepareStatement("DELETE FROM TAG WHERE ID = ?");
         pstmt.setInt(1, id); // Define o ID da tag

         // Retorna 1 se a deleção for bem-sucedida, caso contrário retorna 0
         return pstmt.executeUpdate() > 0 ? 1 : 0;
      } catch (SQLException sqe) {
         sqe.printStackTrace(); // Imprime erros SQL durante a deleção
         return -1; // Retorna -1 para indicar um erro no banco de dados
      } finally {
         if (conn != null) {
            connection.disconnect(); // Desconecta do banco de dados
         }
      }
   }

   // Método para atualizar a descrição de uma tag pelo seu ID
   public int updateTag(String description, int id) {
      Connection conn = null;
      try {
         conn = connection.connect(); // Conecta ao banco de dados

         // Verifica se o ID da tag existe antes de tentar atualizar
         pstmt = conn.prepareStatement("SELECT COUNT(*) FROM TAG WHERE ID = ?");
         pstmt.setInt(1, id); // Define o parâmetro do ID da tag
         ResultSet rs = pstmt.executeQuery(); // Executa a consulta
         if (rs.next() && rs.getInt(1) == 0) {
            rs.close(); // Fecha o ResultSet
            return 0; // O ID da tag não existe, retorna 0 para indicar falha
         }
         rs.close(); // Fecha o ResultSet se o ID existir

         // Prepara a declaração SQL para atualizar a descrição da tag
         pstmt = conn.prepareStatement("UPDATE TAG SET DESCRIPTION = ? WHERE ID = ?");
         pstmt.setString(1, description); // Define a nova descrição
         pstmt.setInt(2, id); // Define o ID da tag

         // Retorna 1 se a atualização for bem-sucedida, caso contrário retorna 0
         return pstmt.executeUpdate() > 0 ? 1 : 0;
      } catch (SQLException sqle) {
         sqle.printStackTrace(); // Imprime erros SQL durante a atualização
         return -1; // Retorna -1 para indicar um erro no banco de dados
      } finally {
         if (conn != null) {
            connection.disconnect(); // Desconecta do banco de dados
         }
      }
   }

   // Método para buscar todas as tags no banco de dados
   public ResultSet readTag() {
      Connection conn = null;
      try {
         conn = connection.connect(); // Conecta ao banco de dados
         if (conn == null) {
            System.out.println("Falha ao estabelecer a conexão com o banco de dados.");
            return null; // Retorna null se não conseguir conectar
         }

         // Prepara a consulta SQL para contar as tags
         pstmt = conn.prepareStatement("SELECT COUNT(*) FROM TAG");
         ResultSet rset = pstmt.executeQuery(); // Executa a consulta
         return rset;
      } catch (SQLException sqe) {
         sqe.printStackTrace(); // Imprime erros SQL durante a execução da consulta
         return null; // Retorna null em caso de erro
      } finally {
         if (conn != null) {
            connection.disconnect(); // Garante a desconexão se a conexão foi estabelecida
         }
      }
   }
}
