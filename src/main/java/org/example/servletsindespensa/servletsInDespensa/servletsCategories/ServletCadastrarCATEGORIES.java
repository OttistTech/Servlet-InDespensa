package org.example.servletsindespensa.servletsInDespensa.servletsCategories;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servletsindespensa.dao.CategoriesDAO;

import java.io.IOException;

//@WebServlet(name = "login", value = "/login")
public class ServletCadastrarCATEGORIES extends HttpServlet {
    CategoriesDAO categoriesDAO = new CategoriesDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("category_name");

        int inserir = categoriesDAO.insertCategories(name);
        if (inserir > 0) {
            request.getRequestDispatcher("sucessoCrud.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("erroCrud.jsp").forward(request, response);

        }

    }
}
