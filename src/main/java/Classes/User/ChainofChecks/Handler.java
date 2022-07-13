package Classes.User.ChainofChecks;

import javax.servlet.http.HttpServletRequest;

/**
 * Classe astratta per la gestione degli Handler della Chain of Responsibility.
 */

public abstract class Handler {
    protected Handler next;
    public void SetNext(Handler h){
        this.next = h;
    }

    public abstract String handle(HttpServletRequest request) throws Exception;
}
