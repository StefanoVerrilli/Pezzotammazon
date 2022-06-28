package Classes.Pattern;

import Classes.BuilderNavBar.AdminNavBuilder;
import Classes.BuilderNavBar.Director;
import Classes.BuilderNavBar.Navbar;
import Classes.Models.User;

import javax.servlet.http.HttpServletRequest;

public class BuildNavbar{

    public static void GetNavbar(HttpServletRequest request) throws Exception {
        Director director = new Director();
        Navbar myNavbar = new Navbar();
        AdminNavBuilder adminNavBar = new AdminNavBuilder();
        User user = (User) request.getSession().getAttribute("user");
        if(user.getAccessType() != null){
        if(user.getAccessType() == 0) {
            director.constructUserNavBar(adminNavBar);
            myNavbar = adminNavBar.getProduct();
        }else{
            director.constructAdminNavBar(adminNavBar);
            myNavbar = adminNavBar.getProduct();
        }}
        request.getSession().setAttribute("myNavbar",myNavbar);
    }
}
