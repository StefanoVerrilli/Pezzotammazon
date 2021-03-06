package Classes.User.Access;

import Classes.FrontController.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Esegue il logout dell'utente.
 */
public class LogOut implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        if(session != null){
            session.invalidate();
        }
        return "/LogIn";
    }
}
