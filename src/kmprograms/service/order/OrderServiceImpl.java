package kmprograms.service.order;


import kmprograms.model.Order;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OrderServiceImpl implements OrderService {
    private final Map<Integer, Order> orders = Map.of(
            1, new Order(1, "PROD A", 10, BigDecimal.valueOf(120)),
            2, new Order(2, "PROD B", 20, BigDecimal.valueOf(110)),
            3, new Order(3, "PROD C", 15, BigDecimal.valueOf(140))
    );

    @Override
    public Order getById(int id) {
        try {
            TimeUnit.SECONDS.sleep(1);
            if (!orders.containsKey(id)) {
                throw new IllegalArgumentException("Order not found");
            }
            return orders.get(id);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
