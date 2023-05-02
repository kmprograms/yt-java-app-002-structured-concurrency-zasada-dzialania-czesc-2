package kmprograms.service.customer;


import kmprograms.model.Customer;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CustomerServiceImpl implements CustomerService {
    private final Map<Integer, Customer> customers = Map.of(
            1, new Customer(1, "ADAM", "NOWAK"),
            2, new Customer(2, "ZOSIA", "KOWAL"),
            3, new Customer(3, "JAN", "KOWALSKI")
    );

    @Override
    public Customer getById(int id) {
        try {
            TimeUnit.SECONDS.sleep(1);
            if (!customers.containsKey(id)) {
                throw new IllegalArgumentException("Customer not found");
            }
            return customers.get(id);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
