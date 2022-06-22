package Classes;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<ShoppingItem> Items = new ArrayList<>();
    private int Cart_id;
    private int User_id;

    public List<ShoppingItem> getItems() {
        return Items;
    }

    public void setItems(List<ShoppingItem> items) {
        Items = items;
    }

    public void addItem(ShoppingItem item){
        this.Items.add(item);
    }

    public int getCart_id() {
        return this.Cart_id;
    }

    public void setCart_id(int CartId){
        this.Cart_id = CartId;
    }

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int user_id) {
        User_id = user_id;
    }
}
