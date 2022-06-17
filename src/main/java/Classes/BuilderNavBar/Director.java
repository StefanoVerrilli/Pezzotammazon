package Classes.BuilderNavBar;

import Classes.BuilderNavBar.AdminNavBuilder;
import Classes.Pair;

public class Director {


    private BuilderInterface Builder;

    public void constructAdminNavBar(BuilderInterface builder){
        builder.addLink(new Pair<String,String>("Inserisci prodotto","InsertProduct.jsp"));
        builder.addLink(new Pair<String,String>("Visualizza prodotti","ProductsTable.do"));
        builder.addLink(new Pair<String,String>("Logout","LogOut.do"));
        builder.addLink(new Pair<String,String>("Go Back","Homepage.jsp"));
    }

    public void constructUserNavBar(BuilderInterface builder){
        builder.addLink(new Pair<String,String>("Go Back","Homepage.jsp"));
        builder.addLink(new Pair<String,String>("Carrello","CartLogic.do"));
        builder.addLink(new Pair<String,String>("Test Insert","TestInsertOrder.do"));
        builder.addLink(new Pair<String,String>("Visualizza prodotti","ProductsTable.do"));
        builder.addLink(new Pair<String,String>("Logout","LogOut.do"));
    }

}
