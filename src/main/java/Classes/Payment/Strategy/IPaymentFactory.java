package Classes.Payment.Strategy;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public abstract class IPaymentFactory {

    public static HttpServletRequest request;

    public IPaymentFactory(HttpServletRequest request){
        this.request = request;
    }


    public abstract IPayMethod PaymentMethod();
}
