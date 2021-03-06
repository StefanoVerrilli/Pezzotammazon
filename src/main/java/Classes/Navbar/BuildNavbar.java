package Classes.Navbar;

import Classes.User.AccessLevels;
import Classes.User.UserModel;

import javax.servlet.http.HttpServletRequest;

/**
 * Classe che si occupa della costruzione effettiva della navbar, differente a seconda dello stato di
 * accesso dell'utente, passandola alla view apposita.
 * @see AccessLevels
 * @see Navbar
 * @see NavBarBuilder
 */

public class BuildNavbar{

    public static void GetNavbar(HttpServletRequest request) {
        Navbar myNavbar = new Navbar();
        NavBarBuilder builder = new NavBarBuilder();
        Director director = new Director(builder);
        UserModel user = (UserModel) request.getSession().getAttribute("user");
        if(user.getAccessType() == AccessLevels.User) {
            director.constructUserNavBar();
            myNavbar = builder.getProduct();
        }else if(user.getAccessType() == AccessLevels.Admin){
            director.constructAdminNavBar();
            myNavbar = builder.getProduct();
        }
        request.getSession().setAttribute("myNavbar",myNavbar);
    }
}
