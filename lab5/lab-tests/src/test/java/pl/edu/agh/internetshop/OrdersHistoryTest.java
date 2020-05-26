package pl.edu.agh.internetshop;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrdersHistoryTest {
    private static final BigDecimal DISCOUNT = BigDecimal.valueOf(0);

    @Test
    void getFilteredOrders() {
        //given
        Product p = new Product("Apple", BigDecimal.valueOf(250), DISCOUNT);
        Product p1 = new Product("Egg", BigDecimal.valueOf(200), DISCOUNT);
        Product p2 = new Product("Milk", BigDecimal.valueOf(350), DISCOUNT);
        Product p3 = new Product("Water", BigDecimal.valueOf(200), DISCOUNT);

        Order order = new Order(Arrays.asList(p, p1, p2, p3));
        order.setOrdersPayerSurname("Trishch");
        Order order1 = new Order(Arrays.asList(p, p2));
        order1.setOrdersPayerSurname("Tumilovich");
        Order order2 = new Order(Arrays.asList(p3, p1));
        order2.setOrdersPayerSurname("Trishch");

        SearchStrategy s = new ProductNameSearchStrategy("Apple");
        SearchStrategy s1 = new PayersSurnameSearchStrategy("Trishch");
        SearchStrategy s2 = new TotalPriceSearchStrategy(BigDecimal.valueOf(738));

        CompositeSearchStrategy cs = new CompositeSearchStrategy(Arrays.asList(s, s1));
        OrdersHistory oh = new OrdersHistory(Arrays.asList(order, order1, order2));

        //when
        assertEquals(oh.getFilteredOrders(cs).size(),1);
        assertEquals(oh.getFilteredOrders(s).size(),2);
        assertEquals(oh.getFilteredOrders(s2).size(),1);
    }
}
