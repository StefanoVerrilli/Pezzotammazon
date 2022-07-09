package Classes.Navbar;

import java.util.AbstractMap;

public class Director {


    private final BuilderInterface Builder;

    public Director(BuilderInterface builder) {
        this.Builder = builder;
    }
    public void constructAdminNavBar(){
        Builder.addLink(new AbstractMap.SimpleEntry<>("Insert a new product", "InsertLogic.do"));
        Builder.addLink(new AbstractMap.SimpleEntry<>("View products table","ProductsTable.do"));
        Builder.addLink(new AbstractMap.SimpleEntry<>("User purchases analysis","UsersPageLogic.do"));
        Builder.addLink(new AbstractMap.SimpleEntry<>("User macro-groups","Kmeans.do"));
    }

    public void constructUserNavBar(){
        Builder.addLink(new AbstractMap.SimpleEntry<>("Shopping cart","CartLogic.do"));
        Builder.addLink(new AbstractMap.SimpleEntry<>("Explore all products","ProductsTable.do"));
    }

}
