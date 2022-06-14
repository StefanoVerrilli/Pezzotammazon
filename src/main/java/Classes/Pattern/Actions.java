package Classes.Pattern;

import java.util.HashMap;
import java.util.Map;

public class Actions {
    private static Map<String,Action> ActionsAccess = new HashMap<>();

    public static void putAction(String method,Action action){
        ActionsAccess.put(method,action);
    }
    public static Action getAction(String method){
        return ActionsAccess.get(method);
    }
}
