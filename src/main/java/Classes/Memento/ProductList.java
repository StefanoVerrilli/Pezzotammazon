package Classes.Memento;

import Classes.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductList {
    private List<Product> currentProductList;
    private ProductList productList;

    public ProductList(){
        productList = this;
    }

    public List<Product> getCurrentProductList(){
        return currentProductList;
    }
    public void setCurrentProductList(List<Product> outerProducts){
        currentProductList = outerProducts;
    }

    public Memento createMemento(){
        return new ProductsMemento();
    }

    class ProductsMemento implements Memento{
    private List<Product> mem_currentProductList = new ArrayList<>();

    public void getLastmem(){
        for(int i=0;i<mem_currentProductList.size();i++){
        }
    }

        public ProductsMemento(){
            mem_currentProductList = currentProductList;
        }
        @Override
        public void RestoreProducts() {
            currentProductList = mem_currentProductList;
        }
    }

}
