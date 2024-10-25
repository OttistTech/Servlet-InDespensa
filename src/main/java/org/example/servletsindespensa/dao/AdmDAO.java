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
    // Atributos para manipulação de conexão com o banco de dados e consultas preparadas
    private final DbConnection connection = new DbConnection();
    private PreparedStatement pstmt;
    private final Hash hash = new Hash();
    private final Random rd = new Random();

    // Método para inserir um novo registro
    public int insertAdm(String name, String pswd, String email) {
        try {
            Connection conn = connection.connect(); // Conecta ao banco de dados

            int id = rd.nextInt(10000) + 1; // Gera um ID aleatório entre 1 e 10000

            // Verifica se o ID já existe
            pstmt = conn.prepareStatement("SELECT COUNT(*) FROM ADM WHERE ADM_ID = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            if (rs.getInt(1) > 0) {
                return 0; // ID já existe
            }

            // Verifica se o email já existe
            pstmt = conn.prepareStatement("SELECT COUNT(*) FROM customer WHERE email_login = ?");
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            rs.next();
            if (rs.getInt(1) > 0) {
                return 0; // Email já existe
            }

            // Regex para verificar o nome
            String regexNome = "^[A-Z][a-z]* [A-Z][a-z]*$";
            Pattern pattern = Pattern.compile(regexNome);
            Matcher matcher = pattern.matcher(name);
            if (!matcher.matches()) {
                return 0; // Nome não é válido
            }

            // Regex para verificar a senha
            String regexSenha = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$&])[A-Z0-9a-z%.@#_-]{8,}$";
            pattern = Pattern.compile(regexSenha);
            matcher = pattern.matcher(pswd);
            if (!matcher.matches()) {
                return 0; // Senha não é válida
            }

            // Regex para verificar o email
            String regexEmail = "^[a-z][^A-Z]@(gmail|germinare)\\.(com|org)(\\.br)?$";
            pattern = Pattern.compile(regexEmail);
            matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                return 0; // Email não é válido
            }

            // Prepara a inserção no banco de dados
            pstmt = conn.prepareStatement("INSERT INTO ADM (name, pswd, email) VALUES (?, ?, ?)");
            pstmt.setString(1, name);
            pstmt.setString(2, hash.hashing(pswd));
            pstmt.setString(3, email);

            return pstmt.executeUpdate() > 0 ? 1 : 0; // Retorna 1 se a inserção for bem-sucedida
        } catch (SQLException sqe) {
            sqe.printStackTrace();
            return -1; // Retorna -1 em caso de erro
        } finally {
            connection.disconnect(); // Garante a desconexão
        }
    }

    // Método para deletar um registro
    public int deleteAdm(String email, String senha) {
        try {
            Connection conn = connection.connect(); // Conecta ao banco de dados

            // Verifica se o registro existe
            pstmt = conn.prepareStatement("SELECT COUNT(*) FROM ADM WHERE email = ? and password = ?");
            pstmt.setString(1, email);
            pstmt.setString(2, hash.hashing(senha));
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            if (rs.getInt(1) == 0) {
                return 0; // Registro não existe
            }

            // Prepara a deleção
            pstmt = conn.prepareStatement("DELETE FROM ADM WHERE email = ? and password = ?");
            pstmt.setString(1, email);
            pstmt.setString(2, hash.hashing(senha));

            return pstmt.executeUpdate() > 0 ? 1 : 0; // Retorna 1 se a deleção for bem-sucedida
        } catch (SQLException sqe) {
            sqe.printStackTrace();
            return -1; // Retorna -1 em caso de erro
        } finally {
            connection.disconnect(); // Garante a desconexão
        }
    }

    // Método para atualizar um registro
    public int updateAdm(String email, String newPassword, String senha, String nome) {
        try {
            Connection conn = connection.connect(); // Conecta ao banco de dados

            // Verifica se o registro existe
            pstmt = conn.prepareStatement("SELECT COUNT(*) FROM ADM WHERE email = ? and password = ?");
            pstmt.setString(1, email);
            pstmt.setString(2, hash.hashing(senha));
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            if (rs.getInt(1) == 0) {
                return 0; // Registro não existe
            }

            // Verifica se a nova senha é válida
            String regexSenha = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$&])[A-Z0-9a-z%.@#_-]{8,}$";
            Pattern pattern = Pattern.compile(regexSenha);
            Matcher matcher = pattern.matcher(newPassword);
            if (!matcher.matches()) {
                return 0; // Nova senha não é válida
            }

            // Prepara a atualização
            pstmt = conn.prepareStatement("UPDATE ADM SET password = ?, name = ? WHERE email = ? and password = ?");
            pstmt.setString(1, hash.hashing(newPassword));
            pstmt.setString(2, nome);
            pstmt.setString(3, email);
            pstmt.setString(4, hash.hashing(senha));

            return pstmt.executeUpdate() > 0 ? 1 : 0; // Retorna 1 se a atualização for bem-sucedida
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return -1; // Retorna -1 em caso de erro
        } finally {
            connection.disconnect(); // Garante a desconexão
        }
    }

    // Método para ler registros
    public ResultSet readAdm() {
        try {
            Connection conn = connection.connect(); // Conecta ao banco de dados

            pstmt = conn.prepareStatement("SELECT * FROM ADM ORDER BY NAME");
            return pstmt.executeQuery(); // Retorna o ResultSet para uso posterior
        } catch (SQLException sqe) {
            sqe.printStackTrace();
            return null; // Retorna null em caso de erro
        } finally {
            connection.disconnect(); // Garante a desconexão
        }
    }
    private DbConnection dbConnection = new DbConnection();

    public int autenticarAdm(String email, String password) {
        // Hash a senha usando a classe Hash
        Hash hashUtil = new Hash();
        String hashedPassword = hashUtil.hashing(password);
        Connection conn = dbConnection.connect();

        String sql = "SELECT COUNT(*) FROM adm WHERE email = ? AND senha = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // Definir os parâmetros da consulta
            pstmt.setString(1, email);
            pstmt.setString(2, hashedPassword);

            // Executar a consulta
            ResultSet rs = pstmt.executeQuery();

            // Verificar se o resultado da consulta é maior que 0
            if (rs.next()) {
                return rs.getInt(1) > 0 ? 1 : 0; // Retorna 1 se o usuário existir, 0 caso contrário
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime qualquer erro que ocorra
            return -1; // Retorna -1 se ocorrer um erro na conexão ou consulta
        } finally {
            dbConnection.disconnect(); // Assegura que a conexão seja fechada
        }
        return 0; // Retorna 0 se o usuário não existir
    }
}