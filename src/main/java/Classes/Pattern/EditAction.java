package Classes.Pattern;

import Classes.Product;
import Classes.ProductOperations;
import org.apache.commons.io.IOUtils;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Base64;

@MultipartConfig(maxFileSize = 16177215)
public class EditAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Product productToAdd = ProductOperations.GetSpecificProduct(Integer.parseInt(request.getParameter("productID")));
        productToAdd.setName(request.getParameter("productName"));
        productToAdd.setDesc(request.getParameter("productDesc"));
        productToAdd.setCost(Float.parseFloat(request.getParameter("productCost")));
        productToAdd.setAmount(Integer.parseInt(request.getParameter("productAmount")));
        productToAdd.setCategory(request.getParameter("productCategory"));
        productToAdd.setID(Integer.parseInt(request.getParameter("productID")));
        if (request.getPart("productImage") != null) {
            Part filePart = request.getPart("productImage");
            InputStream is = filePart.getInputStream();
            byte[] bytesArray = IOUtils.toByteArray(is);
            productToAdd.setImage(Base64.getEncoder().encodeToString(bytesArray));
        }
        ProductOperations.ModifyProduct(productToAdd);
        return "ProductsTable";
}}
