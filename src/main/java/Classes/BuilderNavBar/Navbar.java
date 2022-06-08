package Classes.BuilderNavBar;

import Classes.Pair;

import java.util.ArrayList;
import java.util.List;

public class Navbar {
    private List<Pair<String,String>> elements = new ArrayList<>();

    public List<Pair<String,String>> getElements(){
        return this.elements;
    }
    public void setElements(List<Pair<String,String>> _elements){
        this.elements = _elements;
    }
}
