package Classes.Command;

public class DiscriminatorFetch implements DiscriminatorInterface{

    private Integer id;

    public DiscriminatorFetch(Integer id){
        this.id = id;
    }

    @Override
    public String UserPages(){return (id != null) ? "/UserPages/ProductPage" : "/UserPages/UserProducts";}
    @Override
    public String AdminPages(){
        return (id != null) ? "/AdminPages/Edit" : "/AdminPages/ProductsTable";
    }
}
