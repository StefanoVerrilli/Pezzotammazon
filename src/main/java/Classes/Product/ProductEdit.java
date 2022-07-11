package Classes.Product;

import Classes.Exceptions.LogicException;
import Classes.FrontController.Action;
import Classes.Product.ProductCategory.IProductCategoryOperations;
import Classes.Product.ProductCategory.ProductCategoriesOperations;
import Classes.Product.ProductCategory.ProductCategoryModel;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Optional;

@MultipartConfig(maxFileSize = 16177215)
public class ProductEdit implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException{
        IProductOperations<ProductModel> productOperations =
        new ProductOperations(new ProductCategoriesOperations());

        IProductCategoryOperations<ProductCategoryModel> categoryOperations =
        new ProductCategoriesOperations();

        Optional<ProductCategoryModel> category = categoryOperations.get(
        Integer.parseInt(request.getParameter("productCategory")));

        if (category.isEmpty()) {
            throw new LogicException(request,"error","CategoryNotFound");
        }

        Optional<ProductModel> productToAdd = productOperations.
        get(Integer.parseInt(request.getParameter("productID")));

        if(productToAdd.isEmpty()){
            throw new LogicException(request,"error","ProductNotFound");}

        try {
            productToAdd.get()
                    .setName(request.getParameter("productName"));
            productToAdd.get()
                    .setDesc(request.getParameter("productDesc"));
            productToAdd.get()
                    .setCost(Float.parseFloat(request.getParameter("productCost")));
            productToAdd.get()
                    .setAmount(Integer.parseInt(request.getParameter("productAmount")));
            productToAdd.get()
                    .setCategory(category.get());
            productToAdd.get()
                    .setID(Integer.parseInt(request.getParameter("productID")));
        }catch (NumberFormatException e){
            request.getSession().setAttribute("error","illegal arguments for product edit");
            return "/AdminPages/Edit";
        }
        try {
            if (request.getPart("productImage") != null) {
                Part filePart = request.getPart("productImage");
                InputStream is = filePart.getInputStream();
                byte[] bytesArray = IOUtils.toByteArray(is);
                productToAdd.get().setImage(Base64.getEncoder().encodeToString(bytesArray));
            }
        } catch (IOException | ServletException e) {
            throw new LogicException(request,"error","UnableToLoadImage");
        }

        productOperations.update(productToAdd.get());
        return "/AdminPages/ProductsTable";
}}
