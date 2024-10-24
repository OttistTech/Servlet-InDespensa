package org.example.servletsindespensa.servletsInDespensa;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servletsindespensa.dao.AdmDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "login", value = "/login")
public class ServletConsultarADM extends HttpServlet {
    AdmDAO admDAO = new AdmDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("username");
        String password = request.getParameter("password");
        ResultSet rs = admDAO.readAdm();

        PrintWriter out = response.getWriter();
        try {
            // Itera sobre todos os resultados do ResultSet
            while (rs.next()) {
                String lista = ("Password: " + rs.getInt("password") + " - " +
                        "Name: " + rs.getString("email") + " - " +
                        "Plan: " + rs.getString("nome"));
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
