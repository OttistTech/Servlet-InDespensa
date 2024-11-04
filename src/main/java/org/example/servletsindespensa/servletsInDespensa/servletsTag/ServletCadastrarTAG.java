package org.example.servletsindespensa.servletsInDespensa.servletsTag;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servletsindespensa.dao.TagDAO;

import java.io.IOException;

@WebServlet(name = "cadastroTAG", urlPatterns = {"/cadastroTAG"})
public class ServletCadastrarTAG extends HttpServlet {
    TagDAO tagDAO = new TagDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String desc = request.getParameter("description");
        int inserir = tagDAO.insertTag(desc);
        if (inserir > 0) {
            request.getRequestDispatcher("/ErroSucesso/sucessoCrud.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/ErroSucesso/erroCrud.jsp").forward(request, response);}
    }
}