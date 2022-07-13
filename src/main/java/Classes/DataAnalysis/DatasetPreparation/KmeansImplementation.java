package Classes.DataAnalysis.DatasetPreparation;

import Classes.Adapter.DataToD3JSONAdapter;
import Classes.Adapter.IJSONAdapter;
import Classes.Cart.CartOperations;
import Classes.Clustering.EuclideanDistance;
import Classes.Clustering.KMeans;
import Classes.Clustering.Record;
import Classes.Clustering.centroid;
import Classes.OrderCollection.IOrderCollectionOperations;
import Classes.OrderCollection.OrderCollectionModel;
import Classes.Product.ProductCategory.IProductCategoryOperations;
import Classes.Product.ProductCategory.ProductCategoryModel;
import Classes.User.Hashing.ConcreteHashAlg;
import Classes.FrontController.Action;
import Classes.OrderCollection.OrderCollectionOperations;
import Classes.Product.ProductCategory.ProductCategoriesOperations;
import Classes.SuggestionSystemFacede.ClusteringFacade;
import Classes.User.IUserOperation;
import Classes.User.UserModel;
import Classes.User.UsersOperations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Classe che si occupa di gestire la richiesta per la generazione dei dati per la pagina di visualizzazione
 * dati dell'utente (clustering).
 * @see Action
 */

public class KmeansImplementation implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        IUserOperation<UserModel> usersOperations = new UsersOperations(new ConcreteHashAlg());
        IOrderCollectionOperations<OrderCollectionModel> orderCollectionOperations =
        new OrderCollectionOperations(new CartOperations());

        IProductCategoryOperations<ProductCategoryModel> productCategoriesOperations =
        new ProductCategoriesOperations();
        int NumClusters = productCategoriesOperations.getCount();

        ClusteringFacade clusteringFacade =
        new ClusteringFacade(new KMeans(new EuclideanDistance()));

        Map<centroid, List<Record>> result = clusteringFacade
        .ExecuteClustering(usersOperations,orderCollectionOperations,
        NumClusters,2000);

        IJSONAdapter<Map<centroid,List<Record>>> adapter = new DataToD3JSONAdapter();
        adapter.DataToJSON(result);

        request.getSession().setAttribute("analytics_data", ((DataToD3JSONAdapter) adapter).getResults());
        return "/AdminPages/UserStatistics";
    }
}
