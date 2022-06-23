package Classes.Pattern;

import Classes.Models.Product;
import Classes.DAO.ProductOperations;
import org.apache.commons.io.IOUtils;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Base64;
@MultipartConfig(maxFileSize = 16177215)
public class Insert implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
            ProductOperations productOperations = new ProductOperations();
            productOperations.add(productToAdd);
            return "";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
