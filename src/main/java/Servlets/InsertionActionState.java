package Servlets;

import Classes.State.State;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "InsertionActionState", value = "/InsertionActionState")
public class InsertionActionState extends HttpServlet{



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doAction(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doAction(request,response);
    }

    public void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.sendRedirect("InsertProduct.jsp");
    }
}
