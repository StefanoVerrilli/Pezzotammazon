package Classes.Payment;

public interface IPayMethod {
    String key = "";
    public String getKey();
    public Payment Create();
}
