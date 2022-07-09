package Classes.Adapter;

/**
 * Interfaccia generale contenente i metodi che gli adattatori di dati a JSON devono implementare.
 * @param <T> Tipo di dati che trasforma.
 */

public interface IJSONAdapter<T> {
    public void DataToJSON(T data);
}
