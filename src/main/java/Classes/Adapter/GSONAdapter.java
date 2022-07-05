package Classes.Adapter;

import Classes.Clustering.Record;
import Classes.Clustering.centroid;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

public class GSONAdapter implements IAdapter<centroid, List<Record>>{
    private static final Gson gson = new GsonBuilder()
                                    .enableComplexMapKeySerialization()
                                    .create();
    private static final TypeToken<Map<centroid,List<Record>>> Mapping =
        new TypeToken<Map<centroid,List<Record>>>(){};

    private final JsonElaboration Adapted = new JsonElaboration();
    @Override
    public void DataToJSON(Map<centroid, List<Record>> data) {
        final String json = gson.toJson(data,Mapping.getType());
        System.out.println(json);
    }
}
