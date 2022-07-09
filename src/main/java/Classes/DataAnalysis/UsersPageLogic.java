package Classes.DataAnalysis;

import Classes.Cart.CartOperation;
import Classes.ConcreteHashAlg;
import Classes.FrontController.Action;
import Classes.OrderCollection.OrderCollectionOperations;
import Classes.SuggestionSystemFacede.DataAnalysisFacade;
import Classes.User.UserModel;
import Classes.User.UsersOperations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Gestisce la logica della pagina contenente tutti gli utenti di cui è possibile visualizzare i dettagli sugli acquisti
 * @see Action
 */

public class UsersPageLogic implements Action {

    /**
     * Implementa l'esecuzione della richiesta
     * @param request Variabile di richiesta HTTP
     * @param response Variabile di risposta HTTP
     * @return Stringa della pagina a cui reindirizzare l'utente una volta eseguita la richiesta
     * @throws Exception Errore generico durante l'ottenimento dei dati
     * @see DataAnalysisFacade
     * @see Classes.ServletsRegulation.Context
     */

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DataAnalysisFacade dataAnalysisFacade = new DataAnalysisFacade();

        /**
         * Utilizza il Facade per ottenere la lista degli utenti di cui è possibile visualizzare i dati sugli acquisiti
         */
        List<UserModel> userModelList = dataAnalysisFacade.getSuggestibleUsers(
        new UsersOperations(new ConcreteHashAlg()),
        new OrderCollectionOperations(new CartOperation()));

        /**
         * Aggiunge un attributo di sessione contenente la lista degli utenti appena ottenuta
         */
        request.getSession().setAttribute("UsersList",userModelList);

        /**
         * Reindirizza l'amministratore alla pagina degli utenti appena creata
         */
        return "/AdminPages/UsersPage";
    }
}
