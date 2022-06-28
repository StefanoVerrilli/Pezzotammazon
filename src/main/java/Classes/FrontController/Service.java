package Classes.FrontController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


/**
 * Fornisce un interfaccia di accesso da parte di tutte le richieste effettuate da parte
 * dei file .jsp per i servizi dei servlet.
 * Per osservare il meccanismo tramite cui la classe effettua lo smistamento delle richieste,
 * tramite il pattern Factory, visualizzare i seguenti file:
 * @see ActionFactory
 * @see Action
 * @see Actions
 */
@WebServlet(name = "Service", value = "*.do")
@MultipartConfig(maxFileSize = 16177215)
public class Service extends HttpServlet {
    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void Service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException{
        try {
            Action action = ActionFactory.getAction(request);
            String View = action.execute(request,response);
            //request.getRequestDispatcher(View + ".jsp").forward(request,response);
            response.sendRedirect(View + ".jsp");
        } catch (Exception e) {
            throw new ServletException("Executing action failed",e);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Service(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Service(request,response);
    }
}
