package org.example.servletsindespensa.servletsInDespensa.servletsTag;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servletsindespensa.dao.TagDAO;

import java.io.IOException;

//@WebServlet(name = "login", value = "/login")
public class ServletCadastrarTAG extends HttpServlet {
    TagDAO tagDAO = new TagDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("tag_id");
        int intId = Integer.parseInt(id);

        String desc = request.getParameter("description");


        int inserir = tagDAO.insert(intId,desc);
        if (inserir > 0) {
            request.getRequestDispatcher("sucessoCrud.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("erroCrud.jsp").forward(request, response);

        }

    }
}