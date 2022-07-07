package Classes.Payment.Strategy;

import javax.servlet.http.HttpServletRequest;

public interface IPayMethod {
    public String getKey();
    public Payment Create(HttpServletRequest request);
}
