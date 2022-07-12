package Classes.Payment;

import Classes.Cart.CartModel;
import Classes.Cart.CartOperations;
import Classes.Cart.ICartOperations;
import Classes.Exceptions.LogicException;
import Classes.FrontController.Action;
import Classes.OrderCollection.IOrderCollectionOperations;
import Classes.OrderCollection.OrderCollectionModel;
import Classes.OrderCollection.OrderCollectionOperations;
import Classes.Payment.Strategy.*;
import Classes.Product.IProductOperations;
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

    HttpServletRequest request;

    /**
     * Esegue le azioni necessarie a eseguire il pagamento e modifica lo stato dell'ordine e degli articoli acquistati
     * @param request Variabile di richiesta HTTP
     * @param response Variabile di risposta HTTP
     * @return Stringa che rappresenta la pagina sulla quale redirigere l'utente al termine dell'operazione
     * @throws Exception Eccezione generale che identifica un errore durante l'esecuzione
     * @see Classes.ServletsRegulation.Context
     * @see Action
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.request = request;
        UserModel user = (UserModel) request.getSession().getAttribute("user");

        try {
            payOrderBySessionInformation();
        } catch (Exception exception) {
            request.getSession().setAttribute("error", exception.toString());
            return "/UserPages/Pay";
        }

        ICartOperations<CartModel, ShoppingItemModel> cartOperation = new CartOperations();

        decrementProductsAmount(cartOperation, user);

        IOrderCollectionOperations<OrderCollectionModel> orderCollectionOperations =
        new OrderCollectionOperations(cartOperation);

        OrderCollectionModel orderCollection = new OrderCollectionModel();
        orderCollection.setUser_ID(user.getId());
        orderCollection.setTimestamp(Date.valueOf(java.time.LocalDate.now()));
        orderCollectionOperations.add(orderCollection);
        if(!orderCollectionOperations.AddSingleOrders(user.getId()))
            throw new LogicException(request,"error","error during payment");
        EmptyCartWrapper(user.getId());
        return "/UserPages/ProductPage";
    }


    /**
     * Ripulisce il carrello dell'utente
     * @param User_id Identificativo dell'utente
     * @throws SQLException Errore durante l'esecuzione di una query SQL
     * @see CartOperations
     */
    private void EmptyCartWrapper(int User_id) throws LogicException,SQLException{
        ICartOperations<CartModel,ShoppingItemModel> cartOperation = new CartOperations();
        Optional<CartModel> cart = cartOperation.get(User_id);
        if(cart.isEmpty())
            throw new LogicException(request,"error","NoCartFound");
        cartOperation.EmptyCart(cart.get());
    }

    /**
     * Esegue il pagamento
     * @throws Exception Errore durante il pagamento
     */
    private void payOrderBySessionInformation() {
        String method = request.getParameter("payment_type");
        IPaymentFactory factory = switch (method) {
            case "creditcard" -> new PayCreditCardFactory(request);
            case "bancomat" -> new PayBancomatFactory(request);
            default -> throw new LogicException(request, "invalid_payment", "Payment Method not found");
        };
        IPayMethod PaymentMethod = factory.PaymentMethod();
        Payment Payment = PaymentMethod.Create(request);
        if(!Payment.Pay()) {
            throw new LogicException(request,"invalid_payment","error during payment");
        }
    }

    /**
     * Decrementa la quantità in stock nel magazzino dei prodotti acquistati dall'utente
     * @param operation Variabile per accedere alle operazioni sul carrello dell'utente
     * @param user Utente che ha acquistato
     * @throws Exception Errore causato dall'acquisto di una quantità di prodotto superiore a quella in magazzino
     * @see CartOperations
     * @see UserModel
     */

    private void decrementProductsAmount(ICartOperations operation, UserModel user) throws SQLException,LogicException {
        List<ShoppingItemModel> orderedItems = operation.getAll(user.getId());
        IProductOperations<ProductModel> productOperations =
        new ProductOperations(new ProductCategoriesOperations());
        for(ShoppingItemModel item : orderedItems) {
            Optional<ProductModel> product = productOperations.get(item.getProduct().getID());
            if(product.isEmpty())
                throw new LogicException(request,"error","NoProductFound");
            Integer amountOrdered = item.getQuantity();
            try {
                decrementProductIstanceAmount(product.get(), productOperations, amountOrdered);
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
    private void decrementProductIstanceAmount(ProductModel product,
    IProductOperations operations, Integer amountOrdered) throws Exception {
        if((product.getAmount() - amountOrdered)  >= 0) {
            product.setAmount(product.getAmount() - amountOrdered);
            operations.update(product);
        } else {
            throw new LogicException(request,"error","Product can't be purchased");
        }
    }
}
