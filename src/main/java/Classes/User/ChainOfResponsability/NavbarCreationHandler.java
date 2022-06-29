package Classes.User.ChainOfResponsability;

import Classes.Navbar.BuildNavbar;

import javax.servlet.http.HttpServletRequest;

public class NavbarCreationHandler extends Handler{
    @Override
    public String handle(HttpServletRequest request) throws Exception {
        BuildNavbar.GetNavbar(request);
        return next.handle(request);
    }
}
