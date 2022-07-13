package Classes.FrontController;

import java.util.HashMap;
import java.util.Map;

/**
 * Tiene traccia delle {@link Action}, utilizzata per {@link ActionFactory} per creare oggetti Action senza esporre la logica di istanziazione al client.
 */

public class Actions {

    /**
     * Tiene traccia delle {@link Action}, permettendo di accedervi grazie un identificativo alfanumerico.
     */
    private static Map<String, Action>ActionsAccess = new HashMap<>();

    /**
     * Permette di aggiungere una {@link Action} a {@link Actions}.
     * @param method Nome semplice con cui accedere all'oggetto {@link Action}.
     * @param Action {@link Action} da associare al nome.
     */
    public static void putAction(String method,Action Action){
        ActionsAccess.put(method,Action);
    }

    /**
     * Permette di ottenere l'oggetto {@link Action} corrispondente alla stringa data in input.
     * @param method Stringa da utilizzare per la ricerca dell'oggetto {@link Action} corrispondente.
     * @return
     */
    public static Action getAction(String method){
        return ActionsAccess.get(method);
    }

    /**
     * Elimina la lista degli action.
     */
    public static void delete(){ActionsAccess.clear();}
}
