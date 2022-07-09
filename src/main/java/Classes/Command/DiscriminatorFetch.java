package Classes.Command;

public class DiscriminatorFetch implements DiscriminatorInterface{

    private final Integer id;

    public DiscriminatorFetch(Integer id){
        this.id = id;
    }

    @Override
    public String UserPages(){return (id != null) ? "/UserPages/ProductPage" : "/Error/404";}
    @Override
    public String AdminPages(){
        return (id != null) ? "/AdminPages/Edit" : "/Error/404";
    }
}
