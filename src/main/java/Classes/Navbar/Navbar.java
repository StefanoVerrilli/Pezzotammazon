package Classes.Navbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Navbar {
    private List<Map.Entry<String,String>> elements = new ArrayList<>();
    public List<Map.Entry<String,String>> getElements(){
        return this.elements;
    }
    public void setElements(List<Map.Entry<String, String>> _elements){
        this.elements = _elements;
    }
}
