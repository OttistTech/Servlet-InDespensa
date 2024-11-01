package org.example.servletsindespensa.dao;

import org.example.servletsindespensa.util.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class ProductDAO {
    Random rd = new Random();
    // Atributos da classe: PreparedStatement e a classe de conexão personalizada
    private DbConnection connection = new DbConnection();
    private PreparedStatement pstmt;

    // Método para inserir um novo produto no banco de dados
    public int insertProduct(String desc, long barcode, String brand, String name, String type, double weight_volume) {
        try {
            int id = rd.nextInt(10000) + 1;
            Boolean idExists;
            java.sql.Connection conn = connection.connect(); // Abre a conexão com o banco de dados
            // Verifica se o product_id já existe no banco de dados
            do {
                pstmt = conn.prepareStatement("SELECT COUNT(*) FROM PRODUCT WHERE PRODUCT_ID = ?");
                pstmt.setInt(1, id); // Define o parâmetro do ID do produto
                ResultSet rs = pstmt.executeQuery(); // Executa a consulta
                rs.next();
                idExists = rs.getInt(1) > 0;
            } while (idExists);
            // Se o ID do produto existir, retorna 0 para indicar falha
            // Fecha o ResultSet se o ID não existir

            // Prepara a declaração SQL para inserir o novo produto
            pstmt = conn.prepareStatement("INSERT INTO PRODUCT (PRODUCT_ID, DESCRIPTION, BARCODE, BRAND, NAME, TYPE, WEIGHT_VOLUME) VALUES (?, ?, ?, ?, ?, ?, ?)");
            pstmt.setInt(1, id);
            pstmt.setString(2, desc);
            pstmt.setLong(3, barcode);
            pstmt.setString(4, brand);
            pstmt.setString(5, name);
            pstmt.setString(6, type);
            pstmt.setDouble(7, weight_volume);

            // Executa a inserção e retorna sucesso (1) ou falha (0)
            if (pstmt.executeUpdate() > 0) {
                return 1;  // Inserção foi bem-sucedida
            } else {
                return 0;  // Inserção falhou
            }
        } catch (SQLException sqe) {
            sqe.printStackTrace(); // Imprime erros SQL
            return -1;  // Retorna -1 para indicar um erro no banco de dados
        } finally {
            connection.disconnect(); // Desconecta do banco de dados
        }
    }

    // Método para deletar um produto do banco de dados pelo seu ID
    public int deleteProduct(int id) {
        try {
            java.sql.Connection conn = connection.connect(); // Abre a conexão com o banco de dados

            // Verifica se o product_id existe
            pstmt = conn.prepareStatement("SELECT COUNT(*) FROM PRODUCT WHERE PRODUCT_ID = ?");
            pstmt.setInt(1, id); // Define o parâmetro do ID do produto
            ResultSet rs = pstmt.executeQuery(); // Executa a consulta
            rs.next();

            // Se o ID do produto não existir, retorna 0 para indicar falha
            if (rs.getInt(1) == 0) {
                rs.close(); // Fecha o ResultSet
                return 0;
            }
            rs.close(); // Fecha o ResultSet se o ID existir

            // Prepara a declaração SQL para deletar o produto
            String remover = "DELETE FROM PRODUCT WHERE PRODUCT_ID = ?";
            pstmt = conn.prepareStatement(remover);
            pstmt.setInt(1, id);

            // Executa a deleção e retorna sucesso (1) ou falha (0)
            if (pstmt.executeUpdate() > 0) {
                return 1;  // Deleção foi bem-sucedida
            } else {
                return 0;  // Nenhuma linha foi deletada
            }
        } catch (SQLException sqe) {
            sqe.printStackTrace(); // Imprime erros SQL
            return -1;  // Retorna -1 para indicar um erro no banco de dados
        } finally {
            connection.disconnect(); // Desconecta do banco de dados
        }
    }

    // Método para atualizar o weight_volume de um produto no banco de dados pelo seu ID
    public int updateProduct(double weight_volume, int id) {
        try {
            java.sql.Connection conn = connection.connect(); // Abre a conexão com o banco de dados

            // Verifica se o product_id existe no banco de dados
            pstmt = conn.prepareStatement("SELECT COUNT(*) FROM PRODUCT WHERE PRODUCT_ID = ?");
            pstmt.setInt(1, id); // Define o parâmetro do ID do produto
            ResultSet rs = pstmt.executeQuery(); // Executa a consulta
            rs.next();

            // Se o ID do produto não existir, retorna 0 para indicar falha
            if (rs.getInt(1) == 0) {
                rs.close(); // Fecha o ResultSet
                return 0;
            }
            rs.close(); // Fecha o ResultSet se o ID existir

            // Prepara a declaração SQL para atualizar o weight_volume do produto
            pstmt = conn.prepareStatement("UPDATE PRODUCT SET WEIGHT_VOLUME = ? WHERE PRODUCT_ID = ?");
            pstmt.setDouble(1, weight_volume); // Define o novo weight_volume
            pstmt.setInt(2, id); // Define o ID do produto

            // Executa a atualização e retorna sucesso (1) ou falha (0)
            if (pstmt.executeUpdate() > 0) {
                return 1;  // Atualização foi bem-sucedida
            } else {
                return 0;  // Nenhuma linha foi atualizada
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace(); // Imprime erros SQL
            return -1;  // Retorna -1 para indicar um erro no banco de dados
        } finally {
            connection.disconnect(); // Desconecta do banco de dados
        }
    }

    // Método para ler todos os produtos do banco de dados
    public ResultSet readProduct() {
        ResultSet rset = null;
        try {
            java.sql.Connection conn = connection.connect(); // Abre a conexão com o banco de dados

            // Prepara a consulta SQL para selecionar todos os produtos ordenados pelo seu ID
            pstmt = conn.prepareStatement("SELECT * FROM PRODUCT ORDER BY PRODUCT_ID");
            rset = pstmt.executeQuery(); // Executa a consulta e retorna o conjunto de resultados
        } catch (SQLException sqe) {
            sqe.printStackTrace(); // Imprime erros SQL
        } finally {
            connection.disconnect(); // Desconecta do banco de dados
        }
        return rset;  // Retorna o conjunto de resultados contendo os produtos
    }
}
