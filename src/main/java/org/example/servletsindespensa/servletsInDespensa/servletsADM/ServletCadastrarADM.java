
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
            String cep=request.getParameter("cep");
            int inserir = admDAO.insertAdm(name,password,email,cep);
            if (inserir > 0) {
                request.getRequestDispatcher("/ErroSucesso/sucessoCrud.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/ErroSucesso/erroCrud.jsp").forward(request, response);}



    }
}
