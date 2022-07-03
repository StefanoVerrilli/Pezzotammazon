package Classes.Navbar;

import Classes.Pair;

public class Director {


    private BuilderInterface Builder;

    public void constructAdminNavBar(BuilderInterface builder){
        builder.addLink(new Pair<String,String>("Inserisci prodotto","InsertLogic.do"));
        builder.addLink(new Pair<String,String>("Visualizza prodotti","ProductsTable.do"));
        builder.addLink(new Pair<String,String>("Analysis","UsersPageLogic.do"));
    }

    public void constructUserNavBar(BuilderInterface builder){
        builder.addLink(new Pair<String,String>("Carrello","CartLogic.do"));
        builder.addLink(new Pair<String,String>("Visualizza prodotti","ProductsTable.do"));
    }

}
