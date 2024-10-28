package org.example.servletsindespensa.servletsInDespensa.servletsADM;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servletsindespensa.dao.AdmDAO;
import java.io.IOException;

@WebServlet(name = "login", urlPatterns = {"/login"})
public class ServletLoginADM extends HttpServlet {

    // Instância do DAO para interação com o banco de dados
    private final AdmDAO admDAO = new AdmDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Obtendo parâmetros de email e senha da requisição
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        // Verificando credenciais no banco de dados
        int resultado = admDAO.autenticarAdm(email, password);
        if (resultado > 0) {
            //response.sendRedirect(request.getContextPath() + "/PaginasCRUD/index.jsp");
            request.getRequestDispatcher("/PaginasCRUD/MENU/index.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Email ou senha inválidos!");
            request.getRequestDispatcher("/CadastroADM/index.jsp").forward(request, response);
        }
    }
}