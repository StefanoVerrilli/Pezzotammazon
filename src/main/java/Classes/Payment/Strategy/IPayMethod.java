package Classes.Payment.Strategy;

import javax.servlet.http.HttpServletRequest;

public interface IPayMethod {
    Payment Create(HttpServletRequest request);
}
