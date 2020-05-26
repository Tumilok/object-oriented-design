package pl.edu.agh.internetshop;

import java.util.ArrayList;
import java.util.List;

public class OrdersHistory {
    private List<Order> pastOrders;

    public OrdersHistory(List<Order> pastOrders) {
        this.pastOrders = pastOrders;
    }

    public void addOrder(Order order){
        this.pastOrders.add(order);
    }

    public void setPastOrders(List<Order> pastOrders) {
        this.pastOrders = pastOrders;
    }

    public List<Order> getPastOrders() {
        return pastOrders;
    }

    public List<Order> getFilteredOrders(SearchStrategy searchStrategy) {
        List<Order> resultList = new ArrayList<>();
        for (Order order: pastOrders) {
            if (searchStrategy.filter(order)) {
                resultList.add(order);
            }
        }
        return resultList;
    }
}
