package Classes.Payment.Strategy;

import javax.servlet.http.HttpServletRequest;

public class PayCreditCardFactory extends IPaymentFactory {

    public PayCreditCardFactory(HttpServletRequest request) {
        super(request);
    }

    @Override
    public IPayMethod PaymentMethod() {
        return new PayCard();
    }
}
