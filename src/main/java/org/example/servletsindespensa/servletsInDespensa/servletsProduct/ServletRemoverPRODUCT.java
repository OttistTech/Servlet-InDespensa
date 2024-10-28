package org.example.servletsindespensa.servletsInDespensa.servletsProduct;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servletsindespensa.dao.ProductDAO;

import java.io.IOException;

//@WebServlet(name = "login", value = "/login")
public class ServletRemoverPRODUCT extends HttpServlet {
    ProductDAO productDAO = new ProductDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("product_id");
        int intId = Integer.parseInt(id);

        int remover = productDAO.deleteProduct(intId);
        if (remover > 0) {
            request.getRequestDispatcher("sucessoCrud.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("erroCrud.jsp").forward(request, response);

        }

    }
}