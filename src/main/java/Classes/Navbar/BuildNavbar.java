package Classes.Navbar;

import Classes.User.AccessLevels;
import Classes.User.UserModel;

import javax.servlet.http.HttpServletRequest;

public class BuildNavbar{

    public static void GetNavbar(HttpServletRequest request) throws Exception {
        Director director = new Director();
        Navbar myNavbar = new Navbar();
        AdminNavBuilder adminNavBar = new AdminNavBuilder();
        UserModel user = (UserModel) request.getSession().getAttribute("user");
        if(user.getAccessType() != null){
        if(user.getAccessType() == AccessLevels.User) {
            director.constructUserNavBar(adminNavBar);
            myNavbar = adminNavBar.getProduct();
        }else{
            director.constructAdminNavBar(adminNavBar);
            myNavbar = adminNavBar.getProduct();
        }}
        request.getSession().setAttribute("myNavbar",myNavbar);
    }
}
