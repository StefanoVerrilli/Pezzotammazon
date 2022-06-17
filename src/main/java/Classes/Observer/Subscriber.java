package Classes.Observer;

import Classes.Product;

public interface Subscriber {
    public void update(Product product);
}
