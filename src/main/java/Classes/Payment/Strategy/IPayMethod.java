package Classes.Payment.Strategy;

import javax.servlet.http.HttpServletRequest;

public interface IPayMethod {
    String key = "";
    public String getKey();
    public Payment Create(HttpServletRequest request);
}
