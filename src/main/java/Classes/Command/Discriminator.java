package Classes.Command;

import java.util.Optional;

public class Discriminator {

    private Integer id;

    public Discriminator(Integer id){
        this.id = id;
    }

    public String UserDiscriminator(){
        return (id != null) ? "ProductPage" : "UserProducts";
    }

    public String AdminDiscriminator(){
        return (id != null) ? "Edit" : "ProductsTable";
    }
}
