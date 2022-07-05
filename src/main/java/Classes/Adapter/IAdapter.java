package Classes.Adapter;

import java.util.Map;

public interface IAdapter<T,G> {
    public void DataToJSON(Map<T,G> datas);
}
