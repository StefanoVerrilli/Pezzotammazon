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
            throws ServletException, IOException, SQLException {
        List<Product> data = ProductOperations.GetProducts();
        ProductList mementoList;
        List<Memento> ArrayMemento = new ArrayList<>();
        if(request.getAttribute("mymemento") == null){
            mementoList = new ProductList();
            mementoList.setCurrentProductList(ProductOperations.GetProducts());
            ArrayMemento.add(mementoList.createMemento());
            request.getSession().setAttribute("mymemento",mementoList);
            request.getSession().setAttribute("concreteMemento",ArrayMemento);
        }else{
            mementoList = (ProductList) request.getSession().getAttribute("mymemento");
            ArrayMemento = (List<Memento>) request.getSession().getAttribute("concreteMemento");
        }
        request.getSession().setAttribute("data",mementoList.getCurrentProductList());
        ArrayMemento.get(ArrayMemento.size()-1).getLastmem();
        response.sendRedirect("ProductsTable.jsp");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request,response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request,response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
