package Classes.BuilderNavBar;

import Classes.BuilderNavBar.AdminNavBuilder;
import Classes.Pair;

public class Director {


    private AdminNavBuilder Builder;

    public void constructAdminNavBar(BuilderInterface builder){
        builder.addLink(new Pair<String,String>("Inserisci prodotto","InsertProduct.jsp"));
        builder.addLink(new Pair<String,String>("Visualizza prodotti","ProductPageLogic"));
        builder.addLink(new Pair<String,String>("Logout","logout"));
        builder.addLink(new Pair<String,String>("Go Back","Homepage.jsp"));
    }

    public void constructUserNavBar(BuilderInterface builder){
        builder.addLink(new Pair<String,String>("Go Back","Homepage.jsp"));
        builder.addLink(new Pair<String,String>("Carrello","CartLogic"));
        builder.addLink(new Pair<String,String>("Visualizza prodotti","ShoppingProduct.jsp"));
        builder.addLink(new Pair<String,String>("Logout","logout"));
    }
}
