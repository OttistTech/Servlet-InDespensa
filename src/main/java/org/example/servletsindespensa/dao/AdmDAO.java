package org.example.servletsindespensa.dao;

import org.example.servletsindespensa.util.Hash;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdmDAO {
   // Atributos para a conexão com o banco de dados e manipulação de dados
   Hash hash= new Hash();
   private Connection conn;
   private PreparedStatement pstmt;

   Random rd = new Random();

   // Método para conectar ao banco de dados
   public boolean connect() {
      try {
         Class.forName("org.postgresql.Driver"); // Registra o driver do PostgreSQL
         conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dbInDespensa", "postgres", "123456");
         return true; // Retorna true se a conexão for bem-sucedida
      } catch (SQLException sqe) {
         sqe.printStackTrace(); // Imprime o erro se a conexão falhar
         return false;
      } catch (ClassNotFoundException cnfe) {
         cnfe.printStackTrace(); // Imprime o erro se o driver não for encontrado
         return false;
      }
   }

   // Método para desconectar do banco de dados
   public void disconnect() {
      try {
         if (conn != null && !conn.isClosed()) {
            conn.close(); // Fecha a conexão se estiver aberta
         }
      } catch (SQLException sqe) {
         sqe.printStackTrace(); // Imprime o erro ao fechar a conexão
      }
   }

   // Método para inserir um novo registro
   public int insert_Adm(String name, String pswd, String email) {
      try {
         connect(); // Conecta ao banco de dados

         int id = rd.nextInt(10000) + 1; // Gera um ID aleatório entre 1 e 10000
         PreparedStatement pstmID = conn.prepareStatement("SELECT COUNT(*) FROM ADM WHERE ADM_ID = ?");
         pstmID.setInt(1, id);
         ResultSet rs = pstmID.executeQuery();
         boolean idExists = false;
         if (rs.next()) {
            idExists = rs.getInt(1) > 0;
         }
         pstmID.close();
         rs.close();

         PreparedStatement pstmEmail = conn.prepareStatement("SELECT COUNT(*) FROM customer WHERE email_login = ?");
         pstmEmail.setString(1, email);
         ResultSet rs1 = pstmEmail.executeQuery();
         boolean emailExists = false;
         if (rs1.next()) {
            emailExists = rs1.getInt(1) > 0;
         }
         pstmEmail.close();
         rs1.close();
         if (idExists || emailExists){
            return 0;
         }
         // Regex para verificar o nome
         String regexNome = "^[A-Z][a-z]* [A-Z][a-z]*$";
         Pattern pattern = Pattern.compile(regexNome);
         Matcher matcher = pattern.matcher(name);
         if (matcher.matches()) {
            pstmt = conn.prepareStatement("INSERT INTO ADM (name, pswd, email) VALUES (?, ?, ?)"); // Prepara a inserção
            pstmt.setString(1, name);
         } else {
            return 0; // Retorna 0 se o nome não for válido
         }
         // Regex para verificar a senha
         String regexSenha = "^(?=.[A-Z])(?=.[0-9])(?=.*[!@#$&])[A-Z0-9a-z%.@#_-]{8,}$";
         Pattern pattern1 = Pattern.compile(regexSenha);
         Matcher matcher1 = pattern1.matcher(pswd);
         if (matcher1.matches()) {
            pstmt.setString(2, hash.hashing(pswd));
         } else {
            return 0; // Retorna 0 se a senha não for válida
         }
         // Regex para verificar o email
         String regexEmail = "^[a-z][^A-Z]@(gmail|germinare)\\.(com|org)(\\.br)?$";
         Pattern pattern2 = Pattern.compile(regexEmail);
         Matcher matcher2 = pattern2.matcher(email);
         if (matcher2.matches()) {
            pstmt.setString(3, email);
         } else {
            return 0; // Retorna 0 se o email não for válido
         }
         // Executa a inserção no banco de dados
         return pstmt.executeUpdate() > 0 ? 1 : 0; // Retorna 1 se a inserção for bem-sucedida
      } catch (SQLException sqe) {
         sqe.printStackTrace(); // Imprime o erro ao inserir
         return -1; // Retorna -1 em caso de erro
      } finally {
         disconnect(); // Garante a desconexão
      }
   }

   // Método para deletar um registro
   public int delete_Adm(String email,String senha) {
      try {
         connect(); // Conecta ao banco de dados

         // Verifica se o nome existe
         pstmt = conn.prepareStatement("SELECT COUNT(*) FROM ADM WHERE email = ? and PASSWORD=?");
         pstmt.setString(1, email);
         pstmt.setString(2,hash.hashing(senha));
         ResultSet rs = pstmt.executeQuery();
         rs.next();
         boolean nameExists = rs.getInt(1) > 0;

         if (!nameExists) {
            return 0; // Retorna 0 se o nome não existir
         }

         // Deleta o registro correspondente
         pstmt = conn.prepareStatement("DELETE FROM ADM WHERE email = ? and password=?");
         pstmt.setString(1, email);
         pstmt.setString(2,hash.hashing(senha));
         return pstmt.executeUpdate() > 0 ? 1 : 0; // Retorna 1 se a deleção for bem-sucedida
      } catch (SQLException sqe) {
         sqe.printStackTrace(); // Imprime o erro ao deletar
         return -1; // Retorna -1 em caso de erro
      } finally {
         disconnect(); // Garante a desconexão
      }
   }

   // Método para atualizar um registro
   public int update_Adm(String email, String newPassword,String senha,String nome) {
      try {
         connect(); // Conecta ao banco de dados

         // Verifica se o nome existe
         pstmt = conn.prepareStatement("SELECT COUNT(*) FROM ADM WHERE email = ? and password=?");
         pstmt.setString(1, email);
         pstmt.setString(2,hash.hashing(senha));
         ResultSet rs = pstmt.executeQuery();
         rs.next();
         boolean nameExists = rs.getInt(1) > 0;

         if (!nameExists) {
            return 0; // Retorna 0 se o nome não existir
         }

         // Verifica se a nova senha é válida
         String regexSenha = "^(?=.[A-Z])(?=.[0-9])(?=.*[!@#$&])[A-Z0-9a-z%.@#_-]{8,}$";
         Pattern pattern1 = Pattern.compile(regexSenha);
         Matcher matcher1 = pattern1.matcher(newPassword);
         if (!matcher1.matches()) {
            return 0; // Retorna 0 se a nova senha não for válida
         }

         // Atualiza a senha
         pstmt = conn.prepareStatement("UPDATE ADM SET PASSWORD = ?, EMAIL = ?, NAME = ? WHERE PASSWORD = ?");
         pstmt.setString(1, hash.hashing(newPassword));
         pstmt.setString(2, email);
         pstmt.setString(3,nome);
         pstmt.setString(4,hash.hashing(senha));

         return pstmt.executeUpdate() > 0 ? 1 : 0; // Retorna 1 se a atualização for bem-sucedida
      } catch (SQLException sqle) {
         sqle.printStackTrace(); // Imprime o erro ao atualizar
         return -1; // Retorna -1 em caso de erro
      } finally {
         disconnect(); // Garante a desconexão
      }
   }

   // Método para ler registros
   public ResultSet read_Adm() {
      try {
         connect(); // Conecta ao banco de dados
         pstmt = conn.prepareStatement("SELECT * FROM " +
                 "ADM ORDER BY NAME");
         ResultSet rset = pstmt.executeQuery(); // Executa a consulta
         return rset; // Retorna o ResultSet para uso posterior (cuidado com a desconexão!)
      } catch (SQLException sqe) {
         sqe.printStackTrace(); // Imprime o erro ao ler
      }
      return null; // Retorna null em caso de erro
   }

}