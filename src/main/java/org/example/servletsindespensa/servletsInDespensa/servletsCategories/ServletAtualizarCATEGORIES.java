package org.example.servletsindespensa.servletsInDespensa.servletsCategories;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servletsindespensa.dao.CategoriesDAO;

import java.io.IOException;

@WebServlet(name = "atualizarCATEGORIES", urlPatterns = {"/atualizarCATEGORIES"})
public class ServletAtualizarCATEGORIES extends HttpServlet {
    CategoriesDAO categoriesDAO = new CategoriesDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("categoryId");
        int intId = Integer.parseInt(id);
        String name = request.getParameter("name");
        int atualizar = categoriesDAO.updateCategories(name, intId);
        if (atualizar > 0) {
            request.getRequestDispatcher("/ErroSucesso/sucessoCrud.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/ErroSucesso/erroCrud.jsp").forward(request, response);}


    }
}
