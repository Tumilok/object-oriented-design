package pl.edu.agh.internetshop;

public class PayersSurnameSearchStrategy implements SearchStrategy {
    private String payersSurname;

    public PayersSurnameSearchStrategy(String payersSurname) {
        this.payersSurname = payersSurname;
    }

    @Override
    public boolean filter(Order order) {
        return order.getOrdersPayerSurname().equals(this.payersSurname);
    }
}