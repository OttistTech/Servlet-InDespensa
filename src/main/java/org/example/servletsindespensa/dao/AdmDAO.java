package org.example.servletsindespensa.dao;
import java.time.LocalDateTime;
import org.example.servletsindespensa.util.DbConnection;
import org.example.servletsindespensa.util.GetAddressId;
import org.example.servletsindespensa.util.Hash;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdmDAO {
    GetAddressId getAdrId=new GetAddressId();

    // Atributos para a conexão com o banco de dados e manipulação de dados
    private final Hash hash = new Hash();
    private Connection conn;
    private final DbConnection dbConnection = new DbConnection(); // Usando dbConnection para gerenciar a conexão
    private PreparedStatement pstmt;
    private final Random rd = new Random();

    // Método para inserir um novo registro
    public int insertAdm(String name, String pswd, String email, String cep) {
        PreparedStatement pstmID = null;
        PreparedStatement pstmEmail = null;
        ResultSet rs = null;
        try {
            conn = dbConnection.connect();
            if (conn == null) {
                System.out.println("Falha ao estabelecer a conexão com o banco de dados.");
                return -1;
            }

            boolean idExists;
            boolean emailExists;
            int id;

            do {
                id = rd.nextInt(10000) + 1;

                // Verifica se o ID já existe
                pstmID = conn.prepareStatement("SELECT COUNT(*) FROM customer WHERE customer_id = ?");
                pstmID.setInt(1, id);
                rs = pstmID.executeQuery();
                idExists = rs.next() && rs.getInt(1) > 0;
                pstmID.close();

                // Verifica se o email já existe
                pstmEmail = conn.prepareStatement("SELECT COUNT(*) FROM customer WHERE email = ?");
                pstmEmail.setString(1, email);
                ResultSet rs1 = pstmEmail.executeQuery();
                emailExists = rs1.next() && rs1.getInt(1) > 0;
                if (emailExists){
                    return 0;
                }
                pstmEmail.close();
                rs1.close();
            } while (idExists || emailExists);

            // Prepara a inserção na tabela customer
            pstmt = conn.prepareStatement("INSERT INTO customer (customer_id, name, plan, type, password, email, register_date, address_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            // Validações (mantenha como está)
            // ...
            String regexNome = "^[A-Z][a-z]* [A-Z][a-z]*$";
            if (!Pattern.matches(regexNome,name)) {
                return 0;
            }
            String regexSenha = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$&])[A-Z0-9a-z%.@#-]{8,}$";
            if (!Pattern.matches(regexSenha,pswd)) {
                return 0;
            }
            String regexEmail = "^[a-z][^A-Z]*@(gmail|germinare)\\.(com|org)(\\.br)?$";
            if (!Pattern.matches(regexEmail,email)) {
                return 0;
            }
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, "ADM");
            pstmt.setString(4, "ADM");
            pstmt.setString(5, hash.hashing(pswd));
            pstmt.setString(6, email);
            pstmt.setDate(7, java.sql.Date.valueOf(LocalDateTime.now().toLocalDate()));
            pstmt.setInt(8, getAdrId.getAddressId(cep));


            // Executa a inserção e verifica se foi bem-sucedida
            return pstmt.executeUpdate();
        } catch (SQLException sqe) {
            sqe.printStackTrace();
            return -1;
        } finally {
            try {
                if (rs != null) rs.close(); // Fecha o ResultSet
                if (pstmt != null) pstmt.close(); // Fecha o PreparedStatement
                if (conn != null) dbConnection.disconnect(); // Fecha a conexão
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
            pstmt = conn.prepareStatement("SELECT COUNT(*) FROM CUSTOMER WHERE password = ?");
            pstmt.setString(1, hash.hashing(senha));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                // Deleta o registro correspondente
                pstmt = conn.prepareStatement("DELETE FROM CUSTOMER WHERE email = ? AND password = ? and type=?");
                pstmt.setString(1, email);
                pstmt.setString(2, hash.hashing(senha));
                pstmt.setString(3, "ADM");
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
    public int updateAdm(String email, String newPassword, String senha) {
        try {
            conn = dbConnection.connect(); // Conecta ao banco de dados
            if (conn == null) {
                System.out.println("Falha ao estabelecer a conexão com o banco de dados.");
                return -1; // Retorne erro se não conseguiu conectar
            }

            // Verifica se o nome existe
            pstmt = conn.prepareStatement("SELECT COUNT(*) FROM CUSTOMER WHERE email = ? AND password = ?");
            pstmt.setString(1, email);
            pstmt.setString(2, hash.hashing(senha));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                // Verifica se a nova senha é válida
                String regexSenha = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$&])[A-Z0-9a-z%.@#_-]{8,}$";
                if (!Pattern.matches(regexSenha, newPassword)) {
                    return 0; // Retorna 0 se a nova senha não for válida
                }

                // Atualiza a senha
                pstmt = conn.prepareStatement("UPDATE customer SET password = ? WHERE email = ? AND password = ?");
                pstmt.setString(1, hash.hashing(newPassword));
                pstmt.setString(2, email);
                pstmt.setString(3, hash.hashing(senha));
                return pstmt.executeUpdate(); // Retorna 1 se a atualização for bem-sucedida
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
            pstmt = conn.prepareStatement("SELECT * FROM CUSTOMER ORDER BY name");
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
            String sql = "SELECT COUNT(*) FROM CUSTOMER WHERE email = ? AND password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, hashedPassword);

            rs = pstmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0 ? 1 : 0; // Retorna 1 se o usuário existir, 0 caso contrário
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Retorna -1 se ocorrer um erro
        } finally {
            // Fechar ResultSet e PreparedStatement]
            dbConnection.disconnect();
        }
    }

}