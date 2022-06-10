package Servlets;

import Classes.Product;
import Classes.ProductOperations;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "EditProductAction", value = "/EditProductAction")
public class EditProductAction extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Product productToAdd = new Product();
        productToAdd.setName(request.getParameter("productName"));
        productToAdd.setDesc(request.getParameter("productDesc"));
        Float Cost = Float.parseFloat(request.getParameter("productCost"));
        int Amount = Integer.parseInt(request.getParameter("productAmount"));
        productToAdd.setCost(Cost);
        productToAdd.setAmount(Amount);
        productToAdd.setCategory(request.getParameter("productCategory"));
        productToAdd.setID(Integer.parseInt(request.getParameter("productID")));
        try {
            ProductOperations.ModifyProduct(productToAdd);
            response.sendRedirect("ProductPageLogic");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
