package Classes.Pattern;

import Classes.BuilderNavBar.AdminNavBuilder;
import Classes.BuilderNavBar.Director;
import Classes.BuilderNavBar.Navbar;
import Classes.Pair;

import javax.servlet.http.HttpServletRequest;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class BuildNavbar{

    public static void GetNavbar(HttpServletRequest request) throws Exception {
        Director director = new Director();
        Navbar myNavbar = new Navbar();
        AdminNavBuilder adminNavBar = new AdminNavBuilder();
        if(request.getSession().getAttribute("access_type") != null){
        int Access = (int) request.getSession().getAttribute("access_type");
        if(Access == 0) {
            director.constructUserNavBar(adminNavBar);
            myNavbar = adminNavBar.getProduct();
        }else{
            director.constructAdminNavBar(adminNavBar);
            myNavbar = adminNavBar.getProduct();
        }}
        request.getSession().setAttribute("myNavbar",myNavbar);
    }
}
