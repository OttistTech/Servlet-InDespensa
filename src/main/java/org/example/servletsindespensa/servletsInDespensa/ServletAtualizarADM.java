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
public class ServletAtualizarADM extends HttpServlet {
    AdmDAO admDAO = new AdmDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String newPassword = request.getParameter("newPassword");
        String password = request.getParameter("password");
        String name = request.getParameter("name");



        int atualizar = admDAO.update_Adm(email,newPassword,password,name);
        if (atualizar > 0) {
            request.getRequestDispatcher("sucesso.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("erro.jsp").forward(request, response);

        }



    }
}
