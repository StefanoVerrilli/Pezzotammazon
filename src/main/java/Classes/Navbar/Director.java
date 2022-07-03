package Classes.Navbar;

import Classes.Pair;

public class Director {


    private BuilderInterface Builder;

    public Director(BuilderInterface builder) {
        this.Builder = builder;
    }
    public void constructAdminNavBar(){
        Builder.addLink(new Pair<String,String>("Inserisci prodotto","InsertLogic.do"));
        Builder.addLink(new Pair<String,String>("Visualizza prodotti","ProductsTable.do"));
        Builder.addLink(new Pair<String,String>("Analysis","UsersPageLogic.do"));
    }

    public void constructUserNavBar(){
        Builder.addLink(new Pair<String,String>("Carrello","CartLogic.do"));
        Builder.addLink(new Pair<String,String>("Visualizza prodotti","ProductsTable.do"));
    }

}
