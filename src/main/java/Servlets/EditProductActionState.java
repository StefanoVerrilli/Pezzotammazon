package Servlets;

import Classes.Product;
import Classes.ProductOperations;
import Classes.State.State;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "EditProductActionState", value = "/EditProductActionState")
public class EditProductActionState extends HttpServlet implements State {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doAction(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doAction(request,response);
    }

    @Override
    public void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect = "ProductPageLogic";
        int ID = Integer.parseInt(request.getParameter("productID"));
        System.out.println("hi");
        try {
            Product lastLoadedProduct = ProductOperations.GetSpecificProduct(ID);
            request.getSession().setAttribute("redirect",redirect);
            request.getSession().setAttribute("oldProduct",lastLoadedProduct);
            System.out.println("hi pt.2");
            response.sendRedirect("EditProduct.jsp");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
