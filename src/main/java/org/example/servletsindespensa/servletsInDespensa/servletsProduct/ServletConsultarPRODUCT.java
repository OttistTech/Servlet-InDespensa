package org.example.servletsindespensa.servletsInDespensa.servletsProduct;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servletsindespensa.dao.ProductDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "consultarPRODUCT", value = "/consultarPRODUCT")
public class ServletConsultarPRODUCT extends HttpServlet {
    ProductDAO productDAO = new ProductDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ResultSet rs = productDAO.read();

        PrintWriter out = response.getWriter();
        try {
            // Itera sobre todos os resultados do ResultSet
            while (rs.next()) {
                String lista = (
                        "ID: " + rs.getString("product_id") +
                        "Descrição: " + rs.getString("description") +
                        "Codigo de Barras: " + rs.getString("barcode") +
                                "Marca: " + rs.getString("brand") +
                        "Nome: " + rs.getString("name") +
                        "Tipo: " + rs.getString("type") +
                        "Volume: " + rs.getString("weight_volume"));
                out.println(lista);
            }
        } catch (SQLException sqe) {
            sqe.printStackTrace();
        } finally {
            // Fecha o ResultSet se não for mais necessário
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
