package org.example.servletsindespensa.dao;

import org.example.servletsindespensa.util.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TagDAO {
   // Atributos da classe: Conexão, PreparedStatement e classe de conexão personalizada
   private DbConnection connection = new DbConnection();
   private PreparedStatement pstmt;

   // Método para inserir uma nova tag no banco de dados
   public int insertTag(int id, String description) {
      try {
         java.sql.Connection conn = connection.connect(); // Conecta ao banco de dados

         // Verifica se o ID da tag já existe
         pstmt = conn.prepareStatement("SELECT COUNT(*) FROM TAG WHERE ID = ?");
         pstmt.setInt(1, id); // Define o parâmetro do ID da tag
         ResultSet rs = pstmt.executeQuery(); // Executa a consulta
         rs.next();

         if (rs.getInt(1) > 0) {
            rs.close(); // Fecha o ResultSet
            return 0; // ID da tag já existe, retorna 0 para indicar falha
         }
         rs.close(); // Fecha o ResultSet se o ID não existir

         // Prepara a instrução SQL para inserir uma nova tag
         pstmt = conn.prepareStatement("INSERT INTO TAG (id, description) VALUES (?, ?)");
         pstmt.setInt(1, id); // Define o ID da tag
         pstmt.setString(2, description); // Define a descrição da tag

         // Retorna 1 se a inserção for bem-sucedida, caso contrário retorna 0
         return pstmt.executeUpdate() > 0 ? 1 : 0;
      } catch (SQLException sqe) {
         sqe.printStackTrace(); // Imprime erros SQL durante a inserção
         return -1; // Retorna -1 para indicar um erro no banco de dados
      } finally {
         connection.disconnect(); // Desconecta do banco de dados
      }
   }

   // Método para remover uma tag pelo seu ID
   public int deleteTag(int id) {
      try {
         java.sql.Connection conn = connection.connect(); // Conecta ao banco de dados

         // Verifica se o ID da tag existe antes de tentar deletar
         pstmt = conn.prepareStatement("SELECT COUNT(*) FROM TAG WHERE ID = ?");
         pstmt.setInt(1, id); // Define o parâmetro do ID da tag
         ResultSet rs = pstmt.executeQuery(); // Executa a consulta
         rs.next();

         if (rs.getInt(1) == 0) {
            rs.close(); // Fecha o ResultSet
            return 0; // ID da tag não existe, retorna 0 para indicar falha
         }
         rs.close(); // Fecha o ResultSet se o ID existir

         // Prepara a instrução SQL para deletar a tag
         pstmt = conn.prepareStatement("DELETE FROM TAG WHERE ID = ?");
         pstmt.setInt(1, id); // Define o ID da tag

         // Retorna 1 se a deleção for bem-sucedida, caso contrário retorna 0
         return pstmt.executeUpdate() > 0 ? 1 : 0;
      } catch (SQLException sqe) {
         sqe.printStackTrace(); // Imprime erros SQL durante a deleção
         return -1; // Retorna -1 para indicar um erro no banco de dados
      } finally {
         connection.disconnect(); // Desconecta do banco de dados
      }
   }

   // Método para atualizar a descrição de uma tag pelo seu ID
   public int updateTag(String description, int id) {
      try {
         java.sql.Connection conn = connection.connect(); // Conecta ao banco de dados

         // Verifica se o ID da tag existe antes de tentar atualizar
         pstmt = conn.prepareStatement("SELECT COUNT(*) FROM TAG WHERE ID = ?");
         pstmt.setInt(1, id); // Define o parâmetro do ID da tag
         ResultSet rs = pstmt.executeQuery(); // Executa a consulta
         rs.next();

         if (rs.getInt(1) == 0) {
            rs.close(); // Fecha o ResultSet
            return 0; // ID da tag não existe, retorna 0 para indicar falha
         }
         rs.close(); // Fecha o ResultSet se o ID existir

         // Prepara a instrução SQL para atualizar a descrição da tag
         pstmt = conn.prepareStatement("UPDATE TAG SET DESCRIPTION = ? WHERE ID = ?");
         pstmt.setString(1, description); // Define a nova descrição
         pstmt.setInt(2, id); // Define o ID da tag

         // Retorna 1 se a atualização for bem-sucedida, caso contrário retorna 0
         return pstmt.executeUpdate() > 0 ? 1 : 0;
      } catch (SQLException sqle) {
         sqle.printStackTrace(); // Imprime erros SQL durante a atualização
         return -1; // Retorna -1 para indicar um erro no banco de dados
      } finally {
         connection.disconnect(); // Desconecta do banco de dados
      }
   }

   // Método para buscar todas as tags do banco de dados
   public ResultSet readTag() {
      ResultSet rset = null;
      try {
         java.sql.Connection conn = connection.connect(); // Conecta ao banco de dados

         // Prepara a consulta SQL para selecionar todas as tags ordenadas pelo ID
         pstmt = conn.prepareStatement("SELECT * FROM TAG ORDER BY ID");
         rset = pstmt.executeQuery(); // Executa a consulta e armazena o conjunto de resultados
      } catch (SQLException sqe) {
         sqe.printStackTrace(); // Imprime erros SQL durante a execução da consulta
      } finally {
         connection.disconnect(); // Desconecta do banco de dados
         return rset; // Retorna o conjunto de resultados contendo as tags
      }
   }
}
