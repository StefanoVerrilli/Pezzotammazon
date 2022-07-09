package Classes.Payment.Strategy;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentFactory {
    HttpServletRequest request;

    public static final List<IPayMethod> Payment = new ArrayList<>();
    public PaymentFactory(HttpServletRequest request){
        this.request = request;
        Payment.add(new PayBancomat());
        Payment.add(new PayCard());
    }

    public Payment PaymentMethod(String key){
        Optional<IPayMethod> paymentMethod = Payment.stream().filter(e -> e.getKey().equals(key)).findFirst();
        if(paymentMethod.isEmpty()){
            throw new IllegalArgumentException();
        }
        return paymentMethod.get().Create(request);
    }
}
