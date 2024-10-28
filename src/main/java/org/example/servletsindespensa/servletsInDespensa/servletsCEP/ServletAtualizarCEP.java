package org.example.servletsindespensa.servletsInDespensa.servletsCEP;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servletsindespensa.dao.CepDAO;

import java.io.IOException;

//@WebServlet(name = "login", value = "/login")
public class ServletAtualizarCEP extends HttpServlet {
    CepDAO cepDAO = new CepDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String oldCep = request.getParameter("old_cep");
        String newCep = request.getParameter("new_cep");
        int atualizar = cepDAO.updateCep(oldCep,newCep);
        if (atualizar > 0) {
            request.getRequestDispatcher("sucessoCrud.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("erroCrud.jsp").forward(request, response);

        }

    }
}