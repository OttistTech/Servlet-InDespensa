package org.example.servletsindespensa.util;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
   private java.sql.Connection conn; // Objeto de conexão para interagir com o banco de dados

   // Método para conectar ao banco de dados PostgreSQL
   public java.sql.Connection connect() {
      try {
         // Carregar o driver do PostgreSQL
         Class.forName("org.postgresql.Driver");
         // Estabelecer uma conexão usando variáveis de ambiente para host, usuário e senha
         conn = DriverManager.getConnection(
                 System.getenv("DB_HOST"), // Host do banco de dados (URL)
                 System.getenv("DB_USER"), // Usuário do banco de dados
                 System.getenv("DB_PASSWORD") // Senha do banco de dados
         );
      } catch (ClassNotFoundException | SQLException e) {
         e.printStackTrace(); // Imprimir qualquer erro que ocorre durante a tentativa de conexão
         return null;
      }
      return conn; // Retornar a conexão corretamente
   }

   // Método para desconectar do banco de dados PostgreSQL
   public void disconnect() {
      try {
         if (conn != null && !conn.isClosed()) {
            // Fechar a conexão se estiver aberta
            conn.close();
         }
      } catch (SQLException e) {
         e.printStackTrace(); // Imprimir qualquer erro que ocorre durante a desconexão
      }
   }
}
