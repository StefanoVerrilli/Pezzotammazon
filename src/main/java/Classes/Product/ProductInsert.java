package Classes.Product;

import Classes.FrontController.Action;
import Classes.Product.ProductCategory.ProductCategoriesOperations;
import Classes.Product.ProductCategory.ProductCategoryModel;
import org.apache.commons.io.IOUtils;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Optional;

@MultipartConfig(maxFileSize = 16177215)
public class ProductInsert implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Part filePart = request.getPart("productImage");
        InputStream is = filePart.getInputStream();
        byte[] bytesArray = IOUtils.toByteArray(is);

        Optional<ProductCategoryModel> category = new ProductCategoriesOperations()
                                        .get(Integer.parseInt(request.getParameter("productCategory")));
        if(category.isEmpty())
            throw new IllegalArgumentException("product category not found");

        ProductModel productToAdd  = new ProductModel.Builder(request.getParameter("productName"))
                                    .setCost(Float.parseFloat(request.getParameter("productCost")))
                                    .setAmount(Integer.valueOf(request.getParameter("productAmount")))
                                    .setDesc(request.getParameter("productDesc"))
                                    .setCategory(category.get())
                                    .setImage(Base64.getEncoder().encodeToString(bytesArray))
                                    .build();
        try {
            ProductOperations productOperations = new ProductOperations(new ProductCategoriesOperations());
            productOperations.add(productToAdd);
            return "";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
