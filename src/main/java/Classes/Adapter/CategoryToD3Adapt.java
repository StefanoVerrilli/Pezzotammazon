package Classes.Adapter;

import Classes.Clustering.Record;
import Classes.Clustering.centroid;

import java.util.List;

public class CategoryToD3Adapt implements IAdaptClassData{

    IAdapter<centroid, List<Record>> toAdaptClass;
    TypeToken<> Mapping;

    public CategoryToD3Adapt(IAdapter<centroid, List<Record>> toAdaptClass) {
        this.toAdaptClass = toAdaptClass;
    }


    public void adaptClass() {

    }


    @Override
    public IAdaptClassData getAdaptedClass() {
        return this;
    }

    public TypeToken<> getAdapterMapping() {
        return Mapping;
    }
}
