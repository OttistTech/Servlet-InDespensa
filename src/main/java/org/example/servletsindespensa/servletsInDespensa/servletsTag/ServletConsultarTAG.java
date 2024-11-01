package org.example.servletsindespensa.servletsInDespensa.servletsTag;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servletsindespensa.dao.TagDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

//@WebServlet(name = "login", value = "/login")
public class ServletConsultarTAG extends HttpServlet {
    TagDAO tagDAO = new TagDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ResultSet rs = tagDAO.readTag();

        PrintWriter out = response.getWriter();
        try {
            // Itera sobre todos os resultados do ResultSet
            while (rs.next()) {
                String lista = (
                        "ID: " + rs.getString("tag_id") +
                        "Descrição: " + rs.getString("description"));
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
