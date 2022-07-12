package Classes.Payment.Strategy;

import javax.servlet.http.HttpServletRequest;

public class PayBancomat implements IPayMethod{

    @Override
    public Payment Create(HttpServletRequest request) {
        return new PaymentBancomat(request);
    }
}
