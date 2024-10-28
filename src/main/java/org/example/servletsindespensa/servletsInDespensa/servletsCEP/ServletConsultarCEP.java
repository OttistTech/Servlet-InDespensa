package org.example.servletsindespensa.servletsInDespensa.servletsCEP;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servletsindespensa.dao.CepDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

//@WebServlet(name = "login", value = "/login")
public class ServletConsultarCEP extends HttpServlet {
   CepDAO cepDAO = new CepDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ResultSet rs = cepDAO.readCep();

        PrintWriter out = response.getWriter();
        try {
            // Itera sobre todos os resultados do ResultSet
            while (rs.next()) {
                String lista = (
                        "CEP: " + rs.getString("cep_id"));
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
