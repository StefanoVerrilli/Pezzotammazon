package Classes.Strategy;

public class PaymentCard implements Payment{
    @Override
    public void Pay() {
        System.out.println("Pay with card");
    }
}
