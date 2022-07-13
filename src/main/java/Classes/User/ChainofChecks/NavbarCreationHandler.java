package Classes.User.ChainofChecks;

import Classes.Navbar.BuildNavbar;

import javax.servlet.http.HttpServletRequest;

/**
 * Crea la navbar.
 */

public class NavbarCreationHandler extends Handler{
    @Override
    public String handle(HttpServletRequest request) throws Exception {
        BuildNavbar.GetNavbar(request);
        return next.handle(request);
    }
}
