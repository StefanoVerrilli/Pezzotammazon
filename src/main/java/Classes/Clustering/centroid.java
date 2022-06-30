package Classes.Clustering;

import java.util.Map;

public class centroid {
    private final Map<String,Double> coords;

    public centroid(Map<String, Double> coords) {
        this.coords = coords;
    }

    public Map<String, Double> getCoords() {
        return coords;
    }
}
