package Classes.Payment.Strategy;

import javax.servlet.http.HttpServletRequest;

public class PaymentBancomat implements Payment{
    public PaymentBancomat(HttpServletRequest request) {

    }

    @Override
    public boolean Pay() {
        System.out.println("Pay with bancomat");return true;
    }
}
