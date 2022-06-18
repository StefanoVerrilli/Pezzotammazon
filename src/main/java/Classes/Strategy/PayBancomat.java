package Classes.Strategy;

public class PayBancomat implements IPay{
    @Override
    public void Pay() {
        System.out.println("U paid with Bancomat");
    }
}
