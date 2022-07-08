package Classes.Payment;
import Classes.Cart.CartOperation;
import Classes.FrontController.Action;
import Classes.OrderCollection.OrderCollectionOperations;
import Classes.Cart.CartModel;
import Classes.OrderCollection.OrderCollection;
import Classes.Payment.Strategy.IPayMethod;
import Classes.Payment.Strategy.IPaymentFactory;
import Classes.Payment.Strategy.Payment;
import Classes.Payment.Strategy.PaymentFactory;
import Classes.Product.ProductCategory.ProductCategoriesOperations;
import Classes.Product.ProductModel;
import Classes.Product.ProductOperations;
import Classes.ShoppingItem.ShoppingItemModel;
import Classes.User.UserModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PaymentLogic implements Action {

    /**
     * Esegue le azioni necessarie a eseguire il pagamento e modifica lo stato dell'ordine e degli articoli acquistati
     * @param request Variabile di richiesta HTTP
     * @param response Variabile di risposta HTTP
     * @return Stringa che rappresenta la pagina sulla quale redirigere l'utente al termine dell'operazione
     * @throws Exception Eccezione generale che identifica un errore durante l'esecuzione
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        UserModel user = (UserModel) request.getSession().getAttribute("user");

        try {
            payOrderBySessionInformation(request);
        } catch (Exception exception) {
            request.getSession().setAttribute("error", exception.toString());
            return "/UserPages/Pay";
        }

        CartOperation cartOperation = new CartOperation();


        decrementProductsAmount(cartOperation, user);

        OrderCollectionOperations orderCollectionOperations =
        new OrderCollectionOperations(cartOperation);
        OrderCollection orderCollection = new OrderCollection();
        orderCollection.setUser_ID(user.getId());
        orderCollection.setTimestamp(Date.valueOf(java.time.LocalDate.now()));
        orderCollectionOperations.add(orderCollection);
        orderCollectionOperations.AddSingleOrders(user.getId());


        EmptyCartWrapper(user.getId());
        return "/Homepage";
    }


    /**
     * Ripulisce il carrello dell'utente
     * @param User_id Identificativo dell'utente
     * @throws SQLException Errore durante l'esecuzione di una query SQL
     * @see CartOperation
     */
    private void EmptyCartWrapper(int User_id) throws SQLException{
        CartOperation cartOperation = new CartOperation();
        Optional<CartModel> cart = cartOperation.get(User_id);
        if(cart.isPresent())
            cartOperation.EmptyCart(cart.get());
    }

    /**
     * Esegue il pagamento
     * @param request Variabile di richiesta HTTP, contenente i dati necessari per il pagamento
     * @throws Exception Errore durante il pagamento
     */
    private void payOrderBySessionInformation(HttpServletRequest request) throws Exception {
        String method = request.getParameter("payment_type");
        PaymentFactory factory = new PaymentFactory(request);
        Payment Payment;
        if((Payment = factory.PaymentMethod(method)) == null) {
            throw new Exception("Transaction failed");
        }
        if(!Payment.Pay()) {
            request.getSession().setAttribute("error", "Transaction failed");
            throw new Exception("Transaction failed");
        }
    }

    /**
     * Decrementa la quantità in stock nel magazzino dei prodotti acquistati dall'utente
     * @param operation Variabile per accedere alle operazioni sul carrello dell'utente
     * @param user Utente che ha acquistato
     * @throws Exception Errore causato dall'acquisto di una quantità di prodotto superiore a quella in magazzino
     * @see CartOperation
     * @see UserModel
     */

    private void decrementProductsAmount(CartOperation operation, UserModel user) throws Exception {
        List<ShoppingItemModel> orderedItems = operation.getAll(user.getId());
        ProductOperations productOperations = new ProductOperations(new ProductCategoriesOperations());
        for(ShoppingItemModel item : orderedItems) {
            ProductModel product = productOperations.get(item.getProduct().getID()).get();
            Integer amountOrdered = item.getQuantity();

            try {
                decrementProductIstanceAmount(product, productOperations, amountOrdered);
            } catch (Exception e) {
                operation.delete(item.getCartID(), item.getProduct().getID());
            }
        }
    }

    /**
     * Decrementa il valore di quantità in stock di un singolo prodotto
     * @param product Prodotto di cui decrementare la quantità
     * @param operations Variabile per le operazioni sui prodotti
     * @param amountOrdered Quantità di prodotto ordinato, da sottrarre a quella attualmente presente in stock
     * @throws Exception Errore causato dall'acquisto di una quantità di prodotto superiore a quella in magazzino
     * @see ProductOperations
     * @see ProductModel
     */
    private void decrementProductIstanceAmount(ProductModel product, ProductOperations operations, Integer amountOrdered) throws Exception {
        if((product.getAmount() - amountOrdered)  >= 0) {
            product.setAmount(product.getAmount() - amountOrdered);
            operations.update(product);
        } else {
            throw new Exception("Product can't be purchased");
        }
    }
}
