package Servlets;

import Classes.Product;
import Classes.ProductOperations;
import Classes.State.State;
import org.apache.commons.io.IOUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Base64;

@WebServlet(name = "EditProductAction", value = "/EditProductAction")
@MultipartConfig(maxFileSize = 16177215)
public class EditProductAction extends HttpServlet implements State {
    @Override
    public void doAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Product productToAdd = null;
        try {
            productToAdd = ProductOperations.GetSpecificProduct(Integer.parseInt(request.getParameter("productID")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        productToAdd.setName(request.getParameter("productName"));
        productToAdd.setDesc(request.getParameter("productDesc"));
        productToAdd.setCost(Float.parseFloat(request.getParameter("productCost")));
        productToAdd.setAmount(Integer.parseInt(request.getParameter("productAmount")));
        productToAdd.setCategory(request.getParameter("productCategory"));
        productToAdd.setID(Integer.parseInt(request.getParameter("productID")));
        if(request.getPart("productImage") != null){
        Part filePart = request.getPart("productImage");
        InputStream is = filePart.getInputStream();
        byte[] bytesArray = IOUtils.toByteArray(is);
        productToAdd.setImage(Base64.getEncoder().encodeToString(bytesArray));}
        try {
            ProductOperations.ModifyProduct(productToAdd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doAction(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doAction(request,response);
    }
}
