package Classes.Strategy;

public class PayBancomat implements IPayMethod{
    String key = "Bancomat";

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public Payment Create() {
        return new PaymentBancomat();
    }
}
