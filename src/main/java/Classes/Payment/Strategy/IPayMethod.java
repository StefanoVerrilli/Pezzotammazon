package Classes.Payment.Strategy;

import javax.servlet.http.HttpServletRequest;

public interface IPayMethod {
    String getKey();
    Payment Create(HttpServletRequest request);
}
