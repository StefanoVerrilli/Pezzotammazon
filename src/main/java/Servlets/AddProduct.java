package Servlets;

import Classes.Product;
import Classes.ProductOperations;
import org.apache.commons.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;

import javax.servlet.http.Part;

@WebServlet(name = "AddProduct", value = "/AddProduct")
@MultipartConfig(maxFileSize = 16177215)
public class AddProduct extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Product productToAdd = new Product();
        productToAdd.setName(request.getParameter("productName"));
        productToAdd.setDesc(request.getParameter("productDesc"));
        productToAdd.setCost(Float.parseFloat(request.getParameter("productCost")));
        productToAdd.setAmount(Integer.parseInt(request.getParameter("productAmount")));
        productToAdd.setCategory(request.getParameter("productCategory"));
       Part filePart = request.getPart("productImage");
       InputStream is = filePart.getInputStream();
       byte[] bytesArray = IOUtils.toByteArray(is);
        productToAdd.setImage(Base64.getEncoder().encodeToString(bytesArray));
        try {
            ProductOperations.AddProduct(productToAdd);

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
