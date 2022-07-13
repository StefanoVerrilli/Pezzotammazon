package Classes.FrontController;

import javax.servlet.http.HttpServletRequest;

/**
 * Gestisce le richieste.
 */

public class ActionFactory {

    /**
     * Permette di generare una richiesta {@link Action} in modo semplice.
     * @param request Richiesta HTTP a servlet.
     * @return Ritorna un oggetto di tipo {@link Action}.
     */
    public static Action getAction(HttpServletRequest request){
        String[] subPath = request.getRequestURI().split("/");
        return Actions.getAction(request.getMethod() + "/"+ subPath[subPath.length - 1 ]);
    }
}
