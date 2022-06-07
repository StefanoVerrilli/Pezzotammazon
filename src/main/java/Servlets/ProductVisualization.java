package Servlets;

import Classes.Product;
import Classes.ProductOperations;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductVisualization", value = "/ProductVisualization")
public class ProductVisualization extends HttpServlet {

    private HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Product> listProduct;
        try {
            listProduct = ProductOperations.GetProducts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        session = request.getSession();
        session.setAttribute("products",listProduct);
        getServletContext().getRequestDispatcher("/ProductsTable.jsp").forward(request,response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
}
