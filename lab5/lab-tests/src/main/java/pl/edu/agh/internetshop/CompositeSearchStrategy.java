package pl.edu.agh.internetshop;

import java.util.List;

public class CompositeSearchStrategy implements SearchStrategy {
    private final List<SearchStrategy> filters;

    public CompositeSearchStrategy(List<SearchStrategy> filters) {
        this.filters = filters;
    }

    @Override
    public boolean filter(Order order) {
        return filters.stream().allMatch(f -> f.filter(order));
    }
}
