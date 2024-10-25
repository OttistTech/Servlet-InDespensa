package org.example.servletsindespensa.servletsInDespensa.servletsCEP;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servletsindespensa.dao.CepDAO;

import java.io.IOException;

@WebServlet(name = "cadastrarCEP", value = "/cadastrarCEP")
public class ServletCadastrarCEP extends HttpServlet {
    CepDAO cepDAO = new CepDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String cepId = request.getParameter("cep_id");
        int atualizar = cepDAO.insert(cepId);
        if (atualizar > 0) {
            request.getRequestDispatcher("sucessoCrud.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("erroCrud.jsp").forward(request, response);

        }

    }
}