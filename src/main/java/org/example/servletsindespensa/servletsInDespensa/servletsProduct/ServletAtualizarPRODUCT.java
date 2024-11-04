package org.example.servletsindespensa.servletsInDespensa.servletsProduct;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servletsindespensa.dao.ProductDAO;

import java.io.IOException;

@WebServlet(name = "atualizarPRODUCT", urlPatterns = {"/atualizarPRODUCT"})
public class ServletAtualizarPRODUCT extends HttpServlet {
    ProductDAO productDAO = new ProductDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String weight_volume = request.getParameter("weight");
        double doubleWeight_volume = Double.parseDouble(weight_volume);

        String id = request.getParameter("id");
        int intId = Integer.parseInt(id);

        int atualizar = productDAO.updateProduct(doubleWeight_volume,intId);
        if (atualizar > 0) {
            request.getRequestDispatcher("/ErroSucesso/sucessoCrud.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/ErroSucesso/erroCrud.jsp").forward(request, response);}

    }
}