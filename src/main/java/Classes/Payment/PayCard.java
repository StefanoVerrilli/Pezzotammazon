package Classes.Payment;

public class PayCard implements IPayMethod{
    String key = "creditcard";
    public String getKey(){
        return key;
    }
    @Override
    public Payment Create() {
        return new PaymentCard();
    }
}
