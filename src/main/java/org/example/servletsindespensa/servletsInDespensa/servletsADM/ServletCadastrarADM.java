
package org.example.servletsindespensa.servletsInDespensa.servletsADM;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servletsindespensa.dao.AdmDAO;

import java.io.IOException;

@WebServlet(name = "cadastrarADM", value = "/cadastrarADM")
public class ServletCadastrarADM extends HttpServlet {
    AdmDAO admDAO = new AdmDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String email = request.getParameter("username");
            String newPassword = request.getParameter("newPassword");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            int atualizar = admDAO.updateAdm(email,newPassword,password,name);
            if (atualizar > 0) {
                request.getRequestDispatcher("sucessoCrud.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("erroCrud.jsp").forward(request, response);

            }



    }
}
