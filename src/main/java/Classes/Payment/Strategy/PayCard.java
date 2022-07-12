package Classes.Payment.Strategy;

import javax.servlet.http.HttpServletRequest;

public class PayCard implements IPayMethod{
    @Override
    public Payment Create(HttpServletRequest request) {
            return new PaymentCard(request);
    }
}
