package Classes.Strategy;

public interface IPayMethod {
    String key = "";
    public String getKey();
    public Payment Create();
}
