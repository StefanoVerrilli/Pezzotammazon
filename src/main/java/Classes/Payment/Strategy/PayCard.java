package Classes.Payment.Strategy;

import javax.servlet.http.HttpServletRequest;

public class PayCard implements IPayMethod{
    String key = "creditcard";
    public String getKey(){
        return key;
    }
    @Override
    public Payment Create(HttpServletRequest request) {
            return new PaymentCard(request);
    }
}
