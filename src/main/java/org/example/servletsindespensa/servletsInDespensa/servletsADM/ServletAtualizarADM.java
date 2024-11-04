
package org.example.servletsindespensa.servletsInDespensa.servletsADM;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servletsindespensa.dao.AdmDAO;

import java.io.IOException;
@WebServlet(name = "atualizarADM", urlPatterns = {"/atualizarADM"})
public class ServletAtualizarADM extends HttpServlet {
    AdmDAO admDAO = new AdmDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String newPassword = request.getParameter("newPassword");
        String password = request.getParameter("password");
        int update = admDAO.updateAdm(email,newPassword,password);
        if (update > 0) {
            request.getRequestDispatcher("/ErroSucesso/sucessoCrud.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/ErroSucesso/erroCrud.jsp").forward(request, response);

        }



    }
}
