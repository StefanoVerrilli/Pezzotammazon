package Servlets;

import Classes.Order;
import Classes.ProductOperations;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CartLogic", value = "/CartLogic")
public class CartLogic extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = (String) request.getSession().getAttribute("email");
        try {
            List<Order> shoppingList = ProductOperations.getCart(email);
            request.getSession().setAttribute("shoppingList",shoppingList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("Cart.jsp");
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
