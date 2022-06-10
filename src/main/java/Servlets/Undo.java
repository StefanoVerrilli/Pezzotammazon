package Servlets;

import Classes.Memento.Memento;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Undo", value = "/Undo")
public class Undo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Memento myMemento = (Memento) request.getSession().getAttribute("concreteMemento");
        System.out.println("memento" + myMemento);
        if(myMemento != null){
            myMemento.RestoreProducts();
            myMemento.getLastmem();
        }
        response.sendRedirect("ProductPageLogic");
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
