package Classes.FrontController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 * Fornisce un'interfaccia di accesso alle richieste eseguite nei file
 * .jsp, utilizzando il pattern Factory ({@link ActionFactory}, {@link Action}, {@link Actions}) per lo smistamento delle richieste
 * @see ActionFactory
 * @see Action
 * @see Actions
 * @see Classes.ServletsRegulation.Context
 */
@WebServlet(name = "Service", value = "*.do")
@MultipartConfig(maxFileSize = 16177215)
public class    Service extends HttpServlet {
    /**
     * Gestisce le richieste dei Servlet.
     * @param request Richiesta HTTP.
     * @param response Risposta HTTP.
     * @throws ServletException Eccezione servlet.
     */
    protected void Service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException{
        try {
            Action action = ActionFactory.getAction(request);
            String View = action.execute(request,response);
            /**
             * Esegue il redirect della pagina ottenuta dalla execute e aggiunge l'esensione .jsp.
             */
            response.sendRedirect(View + ".jsp");
        } catch (Exception e) {
            throw new ServletException("Executing action failed",e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException{
            Service(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException{
        Service(request,response);
    }
}
