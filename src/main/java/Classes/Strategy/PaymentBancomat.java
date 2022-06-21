package Classes.Strategy;

public class PaymentBancomat implements Payment{
    @Override
    public void Pay() {
        System.out.println("Pay with bancomat");
    }
}
