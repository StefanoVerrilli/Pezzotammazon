package Classes.Pattern;

import Classes.Product;
import Classes.ProductOperations;
import org.apache.commons.io.IOUtils;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.InputStream;
import java.util.Base64;
import java.util.Optional;

@MultipartConfig(maxFileSize = 16177215)
public class EditAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProductOperations productOperations = new ProductOperations();
        Optional<Product> productToAdd = productOperations.get(Integer.parseInt(request.getParameter("productID")));
        productToAdd.get().setName(request.getParameter("productName"));
        productToAdd.get().setDesc(request.getParameter("productDesc"));
        productToAdd.get().setCost(Float.parseFloat(request.getParameter("productCost")));
        productToAdd.get().setAmount(Integer.parseInt(request.getParameter("productAmount")));
        productToAdd.get().setCategory(request.getParameter("productCategory"));
        productToAdd.get().setID(Integer.parseInt(request.getParameter("productID")));
        if (request.getPart("productImage") != null) {
            Part filePart = request.getPart("productImage");
            InputStream is = filePart.getInputStream();
            byte[] bytesArray = IOUtils.toByteArray(is);
            productToAdd.get().setImage(Base64.getEncoder().encodeToString(bytesArray));
        }
        productOperations.update(productToAdd.get());
        return "ProductsTable";
}}
