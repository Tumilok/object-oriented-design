package pl.edu.agh.internetshop;

import java.util.List;

public class ProductNameSearchStrategy implements SearchStrategy {
    private String name;

    public ProductNameSearchStrategy(String name) {
        this.name = name;
    }

    @Override
    public boolean filter(Order order) {
        List<Product> products = order.getProducts();
        for (Product p : products) {
            if (p.getName().equals(this.name)) {
                return true;
            }
        }
        return false;
    }
}