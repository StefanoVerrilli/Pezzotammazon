package Classes.Payment.Strategy;

import javax.servlet.http.HttpServletRequest;

public class PaymentBancomat implements Payment{
    private final HttpServletRequest request;
    public PaymentBancomat(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public boolean Pay() {
        return true;
    }
}
