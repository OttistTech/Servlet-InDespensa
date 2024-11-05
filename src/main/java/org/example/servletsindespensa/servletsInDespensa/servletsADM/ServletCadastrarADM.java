
package org.example.servletsindespensa.servletsInDespensa.servletsADM;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servletsindespensa.dao.AddressDAO;
import org.example.servletsindespensa.dao.AdmDAO;
import org.example.servletsindespensa.dao.CepDAO;

import java.io.IOException;

@WebServlet(name = "cadastroADM", urlPatterns = {"/cadastroADM"})
public class ServletCadastrarADM extends HttpServlet {
    AdmDAO admDAO = new AdmDAO();
    CepDAO cepDAO = new CepDAO();
    AddressDAO addressDAO= new AddressDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String cep=request.getParameter("cep");
        int result = admDAO.insertAdm(name, password, email, cep);
        if (result<0) {  // Se a inserção de Adm falhar
            int retorno = cepDAO.insert(cep);  // Tenta inserir o CEP

            if (retorno==1 || retorno==0) {  // Se inserção de CEP for bem-sucedida ou o CEP já existir
                int retorno2 = addressDAO.inserir(cep);  // Tenta inserir o endereço

                if (retorno2>0) {  // Se inserção do endereço for bem-sucedida
                    int result1 = admDAO.insertAdm(name, password, email, cep);  // Tenta novamente inserir o Adm

                    if (result1 > 0) {  // Se a inserção de Adm for bem-sucedida
                        request.getRequestDispatcher("/ErroSucesso/sucessoCrud.jsp").forward(request, response);  // Se tudo deu certo, imprime "boa"
                    } else {
                        request.getRequestDispatcher("/ErroSucesso/erroCrud.jsp").forward(request, response);  // Se a inserção de Adm falhar, imprime "ruim"
                    }
                } else {
                    request.getRequestDispatcher("/ErroSucesso/erroCrud.jsp").forward(request, response);  // Se a inserção de endereço falhar, imprime "ruim"
                }
            } else {
                request.getRequestDispatcher("/ErroSucesso/erroCrud.jsp").forward(request, response);  // Se a inserção de CEP falhar, imprime "ruim"
            }
        } else {
            request.getRequestDispatcher("/ErroSucesso/sucessoCrud.jsp").forward(request, response);  // Se a inserção de Adm for bem-sucedida, imprime "boa"
        }



    }
}
