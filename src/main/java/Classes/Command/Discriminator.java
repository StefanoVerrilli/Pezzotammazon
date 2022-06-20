package Classes.Command;

public class Discriminator implements DiscriminatorInterface{

    private Integer id;

    public Discriminator(Integer id){
        this.id = id;
    }

    @Override
    public String UserPages(){return (id != null) ? "ProductPage" : "UserProducts";}
    @Override
    public String AdminPages(){
        return (id != null) ? "Edit" : "ProductsTable";
    }
}
