package Classes.ServletsRegulation;

import Classes.DataAnalysis.DatasetPreparation.KmeansImplementation;
import Classes.DataAnalysis.OrderElaboration;
import Classes.DataAnalysis.UsersPageLogic;
import Classes.FrontController.Actions;
import Classes.FrontController.InsertLogic;
import Classes.Product.ProductDelete;
import Classes.Product.ProductEdit;
import Classes.Product.ProductInsert;
import Classes.Suggestion.AddSuggestion;

public class AdminState implements State {


    @Override
    public void LoadLink() {
        Actions.putAction("GET/delete.do", new ProductDelete());
        Actions.putAction("POST/EditAction.do", new ProductEdit());
        Actions.putAction("POST/Insert.do", new ProductInsert());
        Actions.putAction("GET/InsertLogic.do", new InsertLogic());
        Actions.putAction("GET/UsersPageLogic.do", new UsersPageLogic());
        Actions.putAction("GET/orderElaboration.do", new OrderElaboration());
        Actions.putAction("GET/Kmeans.do", new KmeansImplementation());
        Actions.putAction("POST/AddSuggestion.do", new AddSuggestion());
    }
}
