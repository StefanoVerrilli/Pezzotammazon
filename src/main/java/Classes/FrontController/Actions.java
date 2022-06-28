package Classes.FrontController;

import java.util.HashMap;
import java.util.Map;

public class Actions {
    private static Map<String, Action>ActionsAccess = new HashMap<>();

    public static void putAction(String method,Action Action){
        ActionsAccess.put(method,Action);
    }
    public static Action getAction(String method){
        return ActionsAccess.get(method);
    }

    public static void delete(){ActionsAccess.clear();}
}
