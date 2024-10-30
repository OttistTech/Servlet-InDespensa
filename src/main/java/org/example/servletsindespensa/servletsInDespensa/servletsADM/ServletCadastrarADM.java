
package org.example.servletsindespensa.servletsInDespensa.servletsADM;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servletsindespensa.dao.AdmDAO;

import java.io.IOException;

@WebServlet(name = "cadastroADM", urlPatterns = {"/cadastroADM"})
public class ServletCadastrarADM extends HttpServlet {
    AdmDAO admDAO = new AdmDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            int inserir = admDAO.insertAdm(name,password,email);
            if (inserir > 0) {
                request.getRequestDispatcher("SucessoCRUD.html").forward(request, response);
            } else {
                request.getRequestDispatcher("ErroCRUD.html").forward(request, response);

            }



    }
}
