package org.example.servletsindespensa.servletsInDespensa.servletsProduct;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servletsindespensa.dao.ProductDAO;

import java.io.IOException;

//@WebServlet(name = "login", value = "/login")
public class ServletCadastrarPRODUCT extends HttpServlet {
    ProductDAO productDAO = new ProductDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("product_id");
        int intId = Integer.parseInt(id);

        String desc = request.getParameter("description");

        String barcode = request.getParameter("barcode");
        long longBarcode = Long.parseLong(barcode);

        String brand = request.getParameter("brand");

        String name = request.getParameter("name");

        String type = request.getParameter("type");

        String weight_volume = request.getParameter("weight_volume");
        double doubleWeight_volume = Double.parseDouble(weight_volume);

        int inserir = productDAO.insert(intId,desc,longBarcode,brand,name,type,doubleWeight_volume);
        if (inserir > 0) {
            request.getRequestDispatcher("sucessoCrud.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("erroCrud.jsp").forward(request, response);

        }

    }
}