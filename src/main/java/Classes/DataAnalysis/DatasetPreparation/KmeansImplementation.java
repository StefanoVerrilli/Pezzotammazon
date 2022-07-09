package Classes.DataAnalysis.DatasetPreparation;

import Classes.Adapter.DataToD3JSONAdapter;
import Classes.Adapter.IJSONAdapter;
import Classes.Cart.CartOperation;
import Classes.Clustering.EuclideanDistance;
import Classes.Clustering.KMeans;
import Classes.Clustering.Record;
import Classes.Clustering.centroid;
import Classes.ConcreteHashAlg;
import Classes.FrontController.Action;
import Classes.OrderCollection.OrderCollectionOperations;
import Classes.Product.ProductCategory.ProductCategoriesOperations;
import Classes.SuggestionSystemFacede.ClusteringFacade;
import Classes.User.UsersOperations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class KmeansImplementation implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UsersOperations usersOperations = new UsersOperations(new ConcreteHashAlg());
        OrderCollectionOperations orderCollectionOperations =
        new OrderCollectionOperations(new CartOperation());
        ProductCategoriesOperations productCategoriesOperations =
        new ProductCategoriesOperations();
        int NumClusters = productCategoriesOperations.getCount();

        ClusteringFacade clusteringFacade =
        new ClusteringFacade(new KMeans(new EuclideanDistance()));

        Map<centroid, List<Record>> result = clusteringFacade
        .ExecuteClustering(usersOperations,orderCollectionOperations,
        NumClusters,10000);

        IJSONAdapter<Map<centroid,List<Record>>> adapter = new DataToD3JSONAdapter();
        adapter.DataToJSON(result);

        request.getSession().setAttribute("analytics_data", ((DataToD3JSONAdapter) adapter).getResults());
        return "/AdminPages/UserStatistics";
    }
}
