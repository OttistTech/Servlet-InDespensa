package org.example.servletsindespensa.servletsInDespensa.servletsProduct;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servletsindespensa.dao.ProductDAO;

import java.io.IOException;

@WebServlet(name = "cadastrarPRODUCT", urlPatterns = {"/cadastrarPRODUCT"})
public class ServletCadastrarPRODUCT extends HttpServlet {
    ProductDAO productDAO = new ProductDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String desc = request.getParameter("DESCRIPTION");

        String barcode = request.getParameter("BARCODE");
        long longBarcode = Long.parseLong(barcode);

        String brand = request.getParameter("BRAND");

        String name = request.getParameter("NAME");

        String type = request.getParameter("TYPE");

        String weight_volume = request.getParameter("WEIGHT_VOLUME");
        double doubleWeight_volume = Double.parseDouble(weight_volume);


        int inserir = productDAO.insertProduct(desc,longBarcode,brand,name,type,doubleWeight_volume);
        if (inserir > 0) {
            request.getRequestDispatcher("sucessoCrud.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("erroCrud.jsp").forward(request, response);
        }

    }
}