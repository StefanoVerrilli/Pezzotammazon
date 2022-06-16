package Classes;

public class Order {

    private int ID;
    private float SubTotal;
    private int quantity;
    private float Cost;
    private String Name;

    public Order(){
        this.quantity = 1;
        this.SubTotal = quantity*Cost;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public float getCost() {
        return Cost;
    }

    public void setCost(float cost) {
        Cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public float getSubTotal() {
        return SubTotal;
    }
    public void UpdateSubTotal(){
        this.SubTotal = this.Cost*this.quantity;
    }

}
