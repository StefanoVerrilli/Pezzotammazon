package Servlets;

import Classes.Product;
import Classes.ProductOperations;
import com.apple.eawt.AppEvent;
import org.omg.CORBA.CODESET_INCOMPATIBLE;
import sun.lwawt.macosx.CSystemTray;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddProduct", value = "/AddProduct")
public class AddProduct extends HttpServlet {

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
        try {
            ProductOperations.AddProduct(productToAdd);
            response.sendRedirect("InsertProduct.jsp");

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
