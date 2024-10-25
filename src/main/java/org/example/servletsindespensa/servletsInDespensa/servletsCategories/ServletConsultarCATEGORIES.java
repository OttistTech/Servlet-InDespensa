package org.example.servletsindespensa.servletsInDespensa.servletsCategories;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servletsindespensa.dao.CategoriesDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "consultarCATEGORIES", value = "/consultarCATEGORIES")
public class ServletConsultarCATEGORIES extends HttpServlet {
    CategoriesDAO categoriesDAO = new CategoriesDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ResultSet rs = categoriesDAO.read();

        PrintWriter out = response.getWriter();
        try {
            // Itera sobre todos os resultados do ResultSet
            while (rs.next()) {
                String lista = ( "Name: " + rs.getString("category_name") + " - " +
                        "ID: " + rs.getString("category_id"));
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
