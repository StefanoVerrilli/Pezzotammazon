package Classes;

public class Product {

    private int ID = 0;
    private String name = "";
    private String desc = "";
    private int amount = 0;
    private float cost = 0;
    private String category = "";

    public Product(){
    }

    public Product(int _ID,String _name,String _desc,int _amount,float _cost,String _Category){
        this.ID = _ID;
        this.name = _name;
        this.desc = _desc;
        this.amount = _amount;
        this.cost = _cost;
        this.category = _Category;
    }
    public Product(String _ID,String _name,String _desc,String _amount,String _cost,String _Category){
        this.ID = Integer.parseInt(_ID);
        this.name = _name;
        this.desc = _desc;
        this.amount = Integer.parseInt(_amount);
        this.cost = Float.parseFloat(_cost);
        this.category = _Category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
}
