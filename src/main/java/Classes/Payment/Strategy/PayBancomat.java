package Classes.Payment.Strategy;

import javax.servlet.http.HttpServletRequest;

public class PayBancomat implements IPayMethod{
    String key = "bancomat";

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public Payment Create(HttpServletRequest request) {
        return new PaymentBancomat(request);
    }
}
