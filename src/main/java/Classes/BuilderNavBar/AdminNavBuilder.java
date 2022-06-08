package Classes.BuilderNavBar;

import Classes.Pair;

import java.util.ArrayList;
import java.util.List;

public class AdminNavBuilder implements BuilderInterface{
    private Navbar myNavbar = new Navbar();
    private List<Pair<String,String>> elements = new ArrayList<>();
    @Override
    public void addLink(Pair<String,String> pair) {
        this.elements.add(pair);
    }
    public Navbar getProduct(){
        myNavbar.setElements(elements);
        return myNavbar;
    }
}
