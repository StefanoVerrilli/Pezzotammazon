package Classes.Navbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NavBarBuilder implements BuilderInterface{
    private Navbar myNavbar = new Navbar();
    private List<Map.Entry<String, String>> elements = new ArrayList<>();
    @Override
    public void addLink(Map.Entry<String, String> pair) {
        this.elements.add(pair);
    }
    public Navbar getProduct(){
        myNavbar.setElements(elements);
        return myNavbar;
    }
}
