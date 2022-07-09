package Classes.Adapter;

import Classes.Clustering.Record;
import Classes.Clustering.centroid;
import Classes.User.UserModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;

/**
 * Classe adapter che adatta i dati calcolati tramite clustering ai dati necessari alla corretta visualizzazione
 * di questi tramite la libreria Javascript D3.js
 */

public class DataToD3JSONAdapter implements IJSONAdapter<Map<centroid, List<Record>>> {

    private Map<String,List<UserModel>> preparedData = new HashMap<>();
    String results;

    private void prepareData(Map<centroid, List<Record>> data) {
        String maxCoordsKey;
        for (Map.Entry<centroid, List<Record>> element : data.entrySet()) {
             maxCoordsKey = element.getKey().getCoords().entrySet()
                                    .stream()
                                    .max((Map.Entry<String, Double> cord1, Map.Entry<String, Double> cord2) -> cord1.getValue()
                                    .compareTo(cord2.getValue())
                                    )
                                    .get().getKey();
             List<UserModel> list = preparedData.get(maxCoordsKey) != null ? preparedData.get(maxCoordsKey) : new ArrayList<>();
             element.getValue().stream().forEach(e -> list.add(e.getTarget()));
             preparedData.put(maxCoordsKey, list);
        }
    }

    public String getResults() {return  results;}
    @Override
    public void DataToJSON(Map<centroid, List<Record>> data) {
        prepareData(data);

        JSONObject jsonObj = new JSONObject();

        jsonObj.put("name", "Categories");

        List<JSONObject> categoryList = new ArrayList<>();

        for (Map.Entry<String, List<UserModel>> element : preparedData.entrySet()) {
            JSONObject categoryJson = new JSONObject();

            categoryJson.put("name", element.getKey());
            categoryList.add(categoryJson);

            List<JSONObject> usersList = new ArrayList<>();
            JSONObject obj;
            for(UserModel user : element.getValue()) {
                obj = new JSONObject();
                obj.put("name", user.getUsername());
                usersList.add(obj);
            }

            categoryJson.put("children", usersList);
        }

        jsonObj.put("children", categoryList);
        results = jsonObj.toJSONString();

    }
}
