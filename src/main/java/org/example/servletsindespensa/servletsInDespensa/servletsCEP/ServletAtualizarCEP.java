package org.example.servletsindespensa.servletsInDespensa.servletsCEP;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servletsindespensa.dao.CepDAO;

import java.io.IOException;

@WebServlet(name = "atualizarCEP", urlPatterns = {"/atualizarCEP"})
public class ServletAtualizarCEP extends HttpServlet {
    CepDAO cepDAO = new CepDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String oldCep = request.getParameter("oldCep");
        String newCep = request.getParameter("newCep");
        int atualizar = cepDAO.updateCep(oldCep,newCep);
        if (atualizar > 0) {
            request.getRequestDispatcher("/ErroSucesso/sucessoCrud.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/ErroSucesso/erroCrud.jsp").forward(request, response);}

    }
}