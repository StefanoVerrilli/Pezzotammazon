package Classes.Payment.Strategy;

import Classes.Payment.CreditCardChecker;

import javax.servlet.http.HttpServletRequest;

public class PaymentCard implements Payment{

    private final HttpServletRequest request;

    public PaymentCard(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public boolean Pay() {
        String cardNumber = request.getParameter("card_number");
        Integer cardExpMonth = Integer.valueOf(request.getParameter("card_month"));
        Integer cardExpYear = Integer.valueOf(request.getParameter("card_year"));
        CreditCardChecker checker = new CreditCardChecker(cardNumber, cardExpMonth, cardExpYear);
        return(checker.check());
    }
}
