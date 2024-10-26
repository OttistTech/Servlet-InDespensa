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
    Hash hash= new Hash();
    private Connection conn;
    private DbConnection connection;
    private PreparedStatement pstmt;
    Random rd = new Random();


    // Método para inserir um novo registro
    public int insertAdm(String name, String pswd, String email) {
        try {
            conn = connection.connect(); // Conecta ao banco de dados

            boolean idExists = false;
            boolean emailExists = false;
            int id = rd.nextInt(10000) + 1; // Gera um ID aleatório

            do {
                PreparedStatement pstmID = conn.prepareStatement("SELECT COUNT(*) FROM ADM WHERE ADM_ID = ?");
                pstmID.setInt(1, id);
                ResultSet rs = pstmID.executeQuery();
                if (rs.next()) {
                    idExists = rs.getInt(1) > 0;
                }
                pstmID.close();
                rs.close();

                PreparedStatement pstmEmail = conn.prepareStatement("SELECT COUNT(*) FROM customer WHERE email_login = ?");
                pstmEmail.setString(1, email);
                ResultSet rs1 = pstmEmail.executeQuery();
                if (rs1.next()) {
                    emailExists = rs1.getInt(1) > 0;
                }
                pstmEmail.close();
                rs1.close();
            } while (emailExists || idExists);

            // Regex para verificar o nome
            String regexNome = "^[A-Z][a-z]* [A-Z][a-z]*$";
            Pattern pattern = Pattern.compile(regexNome);
            Matcher matcher = pattern.matcher(name);
            if (matcher.matches()) {
                pstmt = conn.prepareStatement("INSERT INTO ADM (name, pswd, email, adm_id) VALUES (?, ?, ?, ?)"); // Prepara a inserção
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

            pstmt.setInt(4, id);
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
    public int deleteAdm(String email,String senha) {
        try {
            conn = connection.connect(); // Conecta ao banco de dados

            // Verifica se o nome existe
            pstmt = conn.prepareStatement("SELECT COUNT(*) FROM ADM WHERE PASSWORD=?");
            pstmt.setString(1,hash.hashing(senha));
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
            connection.disconnect(); // Garante a desconexão
        }
    }

    // Método para atualizar um registro
    public int updateAdm(String email, String newPassword,String senha,String nome) {
        try {
            conn = connection.connect(); // Conecta ao banco de dados

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
            connection.disconnect(); // Garante a desconexão
        }
    }

    // Método para ler registros
    public ResultSet readAdm() {
        try {
            conn = connection.connect(); // Conecta ao banco de dados
            pstmt = conn.prepareStatement("SELECT * FROM " +
                    "ADM ORDER BY NAME");
            ResultSet rset = pstmt.executeQuery(); // Executa a consulta
            return rset; // Retorna o ResultSet para uso posterior (cuidado com a desconexão!)
        } catch (SQLException sqe) {
            sqe.printStackTrace(); // Imprime o erro ao ler
        }
        return null; // Retorna null em caso de erro
    }
    public int autenticarAdm(String email, String password) {
        // Hash a senha usando a classe Hash



        try {
            Hash hashUtil = new Hash();
            String hashedPassword = hashUtil.hashing(password);
            Connection conn = connection.connect();
            String sql = "SELECT COUNT(*) FROM adm WHERE email = ? AND senha = ?";
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
            connection.disconnect(); // Assegura que a conexão seja fechada
        }
        return 0; // Retorna 0 se o usuário não existir
    }

}
