package Classes.Navbar;

import Classes.Pair;

public class Director {


    private BuilderInterface Builder;

    public Director(BuilderInterface builder) {
        this.Builder = builder;
    }
    public void constructAdminNavBar(){
        Builder.addLink(new Pair<String,String>("Insert a new product","InsertLogic.do"));
        Builder.addLink(new Pair<String,String>("View products table","ProductsTable.do"));
        Builder.addLink(new Pair<String,String>("User purchases analysis","UsersPageLogic.do"));
        Builder.addLink(new Pair<String,String>("User macro-groups","Kmeans.do"));
    }

    public void constructUserNavBar(){
        Builder.addLink(new Pair<String,String>("Shopping cart","CartLogic.do"));
        Builder.addLink(new Pair<String,String>("Explore all products","ProductsTable.do"));
    }

}
