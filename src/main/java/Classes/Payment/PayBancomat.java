package Classes.Payment;

public class PayBancomat implements IPayMethod{
    String key = "bancomat";

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public Payment Create() {
        return new PaymentBancomat();
    }
}
