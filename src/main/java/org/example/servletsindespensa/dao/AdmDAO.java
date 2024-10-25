package org.example.servletsindespensa.dao;

import org.example.servletsindespensa.util.DbConnection;
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
    Hash hash = new Hash();
    private Connection conn;
    private DbConnection connection = new DbConnection();
    private PreparedStatement pstmt;
    Random rd = new Random();

    // Método para inserir um novo registro
    public int insertAdm(String name, String pswd, String email) {
        try {
            connection.connect(); // Conecta ao banco de dados
            boolean emailExists;
            boolean idExists;
            int id = rd.nextInt(10000-1)+1;
            // Gera um ID aleatório
            do {
                PreparedStatement pstmID = conn.prepareStatement("SELECT COUNT(*) FROM adm WHERE adm_id = ?");
                pstmID.setInt(1, id);
                ResultSet rs = pstmID.executeQuery();
                rs.next();
                idExists = rs.getInt(1) > 0;
            } while (idExists);
            PreparedStatement pstmEmail = conn.prepareStatement("SELECT COUNT(*) FROM adm WHERE email = ?");
            pstmEmail.setString(1, email);
            ResultSet rs1 = pstmEmail.executeQuery();
            emailExists = false;
            if (rs1.next()) {
                emailExists = rs1.getInt(1) > 0;
            }
            pstmEmail.close();
            rs1.close();
            if (emailExists){
                return 0;
            }
            pstmt = conn.prepareStatement("INSERT INTO ADM (name, password, email,adm_id) VALUES (?, ?, ?,?)");
            // Regex para verificar o nome
            String regexNome = "^[A-Z][a-z]* [A-Z][a-z]*$";
            Pattern pattern = Pattern.compile(regexNome);
            Matcher matcher = pattern.matcher(name);
            if (matcher.matches()) {// Prepara a inserção
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
            pstmt.setInt(4,id);
            // Executa a inserção no banco de dados
            return pstmt.executeUpdate() > 0 ? 1 : 0; // Retorna 1 se a inserção for bem-sucedida
        } catch (SQLException sqe) {
            sqe.printStackTrace(); // Imprime o erro ao inserir
            return -1; // Retorna -1 em caso de erro
        } finally {
            connection.disconnect(); // Garante a desconexão
        }
    }

    // Método para deletar um registro
    public int deleteAdm(String email, String senha) {
        try {
            connection.connect(); // Conecta ao banco de dados

            // Verifica se o nome existe
            pstmt = conn.prepareStatement("SELECT COUNT(*) FROM ADM WHERE email = ? and PASSWORD=?");
            pstmt.setString(1, email);
            pstmt.setString(2, hash.hashing(senha));
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            boolean nameExists = rs.getInt(1) > 0;

            if (!nameExists) {
                return 0; // Retorna 0 se o nome não existir
            }

            // Deleta o registro correspondente
            pstmt = conn.prepareStatement("DELETE FROM ADM WHERE email = ? and password=?");
            pstmt.setString(1, email);
            pstmt.setString(2, hash.hashing(senha));
            return pstmt.executeUpdate() > 0 ? 1 : 0; // Retorna 1 se a deleção for bem-sucedida
        } catch (SQLException sqe) {
            sqe.printStackTrace(); // Imprime o erro ao deletar
            return -1; // Retorna -1 em caso de erro
        } finally {
            connection.disconnect(); // Garante a desconexão
        }
    }

    // Método para atualizar um registro
    public int updateAdm(String email, String newPassword, String senha) {
        try {
            connection.connect(); // Conecta ao banco de dados

            // Verifica se o nome existe
            pstmt = conn.prepareStatement("SELECT COUNT(*) FROM ADM WHERE email = ? and password=?");
            pstmt.setString(1, email);
            pstmt.setString(2, hash.hashing(senha));
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
            pstmt = conn.prepareStatement("UPDATE ADM SET PASSWORD = ? WHERE NAME = ?");
            pstmt.setString(1, hash.hashing(newPassword));
            pstmt.setString(2, email);

            return pstmt.executeUpdate() > 0 ? 1 : 0; // Retorna 1 se a atualização for bem-sucedida
        } catch (SQLException sqle) {
            sqle.printStackTrace(); // Imprime o erro ao atualizar
            return -1; // Retorna -1 em caso de erro
        } finally {
            connection.disconnect(); // Garante a desconexão
        }
    }

    // Método para ler registros
    public ResultSet readAdm() {
        try {
            connection.connect(); // Conecta ao banco de dados
            pstmt = conn.prepareStatement("SELECT * FROM ADM ORDER BY NAME");
            ResultSet rset = pstmt.executeQuery(); // Executa a consulta
            return rset; // Retorna o ResultSet para uso posterior (cuidado com a desconexão!)
        } catch (SQLException sqe) {
            sqe.printStackTrace(); // Imprime o erro ao ler
        }
        return null; // Retorna null em caso de erro
    }

    public int autenticarAdm(String pswd, String email) {
        try {
            connection.connect(); // Conecta ao banco de dados

            // Verifica se a senha já existe
            pstmt = conn.prepareStatement("SELECT COUNT(*) FROM ADM WHERE PASSWORD = ? AND EMAIL = ?");
            pstmt.setString(1, hash.hashing(pswd));
            pstmt.setString(2, email);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            boolean password_email_Exists = rs.getInt(1) > 0; // Verifica se a senha já está registrada

            if (password_email_Exists) {
                return 1; // Retorna 1 se a senha e o email já existir
            } else {
                return 0; // Retorna 0 se a senha e o email não existirem
            }

        } catch (SQLException sqe) {
            sqe.printStackTrace();
            return -1;
        } finally {
            connection.disconnect();
        }

    }

}