package Classes.Payment.Strategy;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentFactory {
    private List<IPayMethod> Payment = new ArrayList<>();
    private HttpServletRequest request;


    public PaymentFactory(HttpServletRequest request){
        this.request = request;
        Payment.add(new PayBancomat());
        Payment.add(new PayCard());
    }

    public Payment PaymentMethod(String key){
        Optional<IPayMethod> payment = Payment.stream().filter(e -> e.getKey().equals(key)).findFirst();
        if(!payment.isPresent()){
            throw new IllegalArgumentException();
        }
        return payment.get().Create(request);
    }
}
