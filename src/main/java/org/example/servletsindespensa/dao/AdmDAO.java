package org.example.servletsindespensa.dao;

import org.example.servletsindespensa.util.DbConnection;
import org.example.servletsindespensa.util.Hash;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdmDAO {
    // Atributos para a conexão com o banco de dados e manipulação de dados
    private final Hash hash = new Hash();
    private Connection conn;
    private final DbConnection dbConnection = new DbConnection(); // Usando dbConnection para gerenciar a conexão
    private PreparedStatement pstmt;
    private final Random rd = new Random();

    // Método para inserir um novo registro
    public int insertAdm(String name, String pswd, String email) {
        try {
            conn = dbConnection.connect(); // Conecta ao banco de dados
            if (conn == null) {
                System.out.println("Falha ao estabelecer a conexão com o banco de dados.");
                return -1; // Retorne erro se não conseguiu conectar
            }

            boolean idExists;
            boolean emailExists;
            int id;
            id = rd.nextInt(10000) + 1;

            do {
                // Gera um ID aleatório
                // Verifica se o ID já existe
                pstmt = conn.prepareStatement("SELECT COUNT(*) FROM ADM WHERE ADM_ID = ?");
                pstmt.setInt(1, id);
                ResultSet rs = pstmt.executeQuery();
                idExists = rs.next() && rs.getInt(1) > 0;
                rs.close();

                // Verifica se o email já existe
                pstmt = conn.prepareStatement("SELECT COUNT(*) FROM ADM WHERE email = ?");
                pstmt.setString(1, email);
                ResultSet rsEmail = pstmt.executeQuery();
                emailExists = rsEmail.next() && rsEmail.getInt(1) > 0;
                rsEmail.close();
            } while (emailExists || idExists);

            // Valida o nome
            String regexNome = "^[A-Z][a-z]* [A-Z][a-z]*$";
            if (!Pattern.matches(regexNome, name)) {
                return 0; // Retorna 0 se o nome não for válido
            }
            // Valida a senha
            String regexSenha = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$&])[A-Z0-9a-z%.@#_-]{8,}$";
            if (!Pattern.matches(regexSenha, pswd)) {
                return 0; // Retorna 0 se a senha não for válida
            }
            // Valida o email
            String regexEmail = "^[a-z][^A-Z]*@(gmail|germinare)\\.(com|org)(\\.br)?$";
            if (!Pattern.matches(regexEmail, email)) {
                return 0; // Retorna 0 se o email não for válido
            }
            // Insere o novo administrador
            pstmt = conn.prepareStatement("INSERT INTO ADM (adm_id,name,email, password) VALUES (?, ?, ?, ?)");
                        pstmt.setInt(1, id);
                        pstmt.setString(2, name);
                        pstmt.setString(3, email);
                        pstmt.setString(4, hash.hashing(pswd));
            return pstmt.executeUpdate(); // Retorna 1 se a inserção for bem-sucedida
        } catch (SQLException sqe) {
            sqe.printStackTrace(); // Imprime o erro ao inserir
            return -1; // Retorna -1 em caso de erro
        } finally {
            dbConnection.disconnect(); // Garante a desconexão
        }
    }

    // Método para deletar um registro
    public int deleteAdm(String email, String senha) {
        try {
            conn = dbConnection.connect(); // Conecta ao banco de dados
            if (conn == null) {
                System.err.println("Falha ao estabelecer a conexão com o banco de dados.");
                return -1; // Retorne erro se não conseguiu conectar
            }

            // Verifica se o nome existe
            pstmt = conn.prepareStatement("SELECT COUNT(*) FROM ADM WHERE pswd = ?");
            pstmt.setString(1, hash.hashing(senha));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                // Deleta o registro correspondente
                pstmt = conn.prepareStatement("DELETE FROM ADM WHERE email = ? AND pswd = ?");
                pstmt.setString(1, email);
                pstmt.setString(2, hash.hashing(senha));
                return pstmt.executeUpdate() > 0 ? 1 : 0; // Retorna 1 se a deleção for bem-sucedida
            }
            return 0; // Retorna 0 se o nome não existir
        } catch (SQLException sqe) {
            sqe.printStackTrace(); // Imprime o erro ao deletar
            return -1; // Retorna -1 em caso de erro
        } finally {
            dbConnection.disconnect(); // Garante a desconexão
        }
    }

    // Método para atualizar um registro
    public int updateAdm(String email, String newPassword, String senha, String nome) {
        try {
            conn = dbConnection.connect(); // Conecta ao banco de dados
            if (conn == null) {
                System.out.println("Falha ao estabelecer a conexão com o banco de dados.");
                return -1; // Retorne erro se não conseguiu conectar
            }

            // Verifica se o nome existe
            pstmt = conn.prepareStatement("SELECT COUNT(*) FROM ADM WHERE email = ? AND pswd = ?");
            pstmt.setString(1, email);
            pstmt.setString(2, hash.hashing(senha));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                // Verifica se a nova senha é válida
                String regexSenha = "^(?=.[A-Z])(?=.[0-9])(?=.*[!@#$&])[A-Z0-9a-z%.@#_-]{8,}$";
                if (!Pattern.matches(regexSenha, newPassword)) {
                    return 0; // Retorna 0 se a nova senha não for válida
                }

                // Atualiza a senha
                pstmt = conn.prepareStatement("UPDATE ADM SET pswd = ?, email = ?, name = ? WHERE email = ? AND pswd = ?");
                pstmt.setString(1, hash.hashing(newPassword));
                pstmt.setString(2, email);
                pstmt.setString(3, nome);
                pstmt.setString(4, email);
                pstmt.setString(5, hash.hashing(senha));
                return pstmt.executeUpdate() > 0 ? 1 : 0; // Retorna 1 se a atualização for bem-sucedida
            }
            return 0; // Retorna 0 se o nome não existir
        } catch (SQLException sqle) {
            sqle.printStackTrace(); // Imprime o erro ao atualizar
            return -1; // Retorna -1 em caso de erro
        } finally {
            dbConnection.disconnect(); // Garante a desconexão
        }
    }

    // Método para ler registros
    public ResultSet readAdm() {
        try {
            conn = dbConnection.connect(); // Conecta ao banco de dados
            if (conn == null) {
                System.out.println("Falha ao estabelecer a conexão com o banco de dados.");
                return null; // Retorne null se não conseguiu conectar
            }
            pstmt = conn.prepareStatement("SELECT * FROM ADM ORDER BY name");
            return pstmt.executeQuery(); // Retorna o ResultSet para uso posterior
        } catch (SQLException sqe) {
            sqe.printStackTrace(); // Imprime o erro ao ler
            return null; // Retorna null em caso de erro
        } finally {
            dbConnection.disconnect(); // Garante a desconexão
        }
    }

    public int autenticarAdm(String email, String password) {
        ResultSet rs = null;
        try {
            conn = dbConnection.connect(); // Conecta ao banco de dados
            if (conn == null) {
                System.out.println("Falha ao estabelecer a conexão com o banco de dados.");
                return -1; // Retorne erro se não conseguiu conectar
            }
            String hashedPassword = hash.hashing(password); // Usa o hash correto
            String sql = "SELECT COUNT(*) FROM ADM WHERE email = ? AND password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, hashedPassword);

            rs = pstmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0 ? 1 : 0; // Retorna 1 se o usuário existir, 0 caso contrário
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Retorna -1 se ocorrer um erro
        } finally {
            // Fechar ResultSet e PreparedStatement
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                dbConnection.disconnect(); // Fecha a conexão aqui
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}