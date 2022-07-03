package Classes.User.ChainofChecks;

import javax.servlet.http.HttpServletRequest;

public abstract class Handler {
    protected Handler next;
    public void SetNext(Handler h){
        this.next = h;
    };
    public abstract String handle(HttpServletRequest request) throws Exception;
}
