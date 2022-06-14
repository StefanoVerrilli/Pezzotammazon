package Servlets;

import Classes.Memento.Memento;
import Classes.Memento.ProductList;
import Classes.Product;
import Classes.ProductOperations;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductPageLogic", value = "/ProductPageLogic")
public class ProductPageLogic extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Product> data = null;
        try {
            data = ProductOperations.GetProducts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.getSession().setAttribute("data",data);
        response.sendRedirect("ProductsTable.jsp");
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
