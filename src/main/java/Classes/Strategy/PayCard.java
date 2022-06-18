package Classes.Strategy;

public class PayCard implements IPay{
    @Override
    public void Pay() {
        System.out.println("U paid with Card");
    }
}
