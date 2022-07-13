package Classes.FrontController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interfaccia per le classi che eseguono richieste.
 */

public interface Action {
    String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
