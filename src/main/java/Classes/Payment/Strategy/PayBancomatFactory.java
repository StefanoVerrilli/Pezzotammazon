package Classes.Payment.Strategy;

import javax.servlet.http.HttpServletRequest;

public class PayBancomatFactory extends IPaymentFactory {

    public PayBancomatFactory(HttpServletRequest request){
        super(request);

    }

    public IPayMethod PaymentMethod(){
        return new PayBancomat();
    }
}
