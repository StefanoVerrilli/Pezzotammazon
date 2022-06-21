package Classes.Strategy;

public class PayCard implements IPayMethod{
    String key = "CreditCard";
    public String getKey(){
        return key;
    }
    @Override
    public Payment Create() {
        return new PaymentCard();
    }
}
