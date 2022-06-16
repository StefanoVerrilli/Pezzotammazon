package Classes.Pattern;

import Classes.Pair;

import java.util.HashMap;
import java.util.Map;

public class Actions {
    private static Map<String, Pair<Action,Integer>>ActionsAccess = new HashMap<>();

    public static void putAction(String method,Pair<Action,Integer> Action){
        ActionsAccess.put(method,Action);
    }
    public static Action getAction(String method){
        return ActionsAccess.get(method).getKey();
    }
}
