package kmprograms.service.invoice;

import jdk.incubator.concurrent.StructuredTaskScope;
import kmprograms.model.Invoice;
import kmprograms.service.customer.CustomerService;
import kmprograms.service.order.OrderService;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class InvoiceServiceImpl implements InvoiceService {
    private final CustomerService customerService;
    private final OrderService orderService;

    public InvoiceServiceImpl(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    public String createDescription(String keyword) {
        try {
            if (keyword == null || keyword.isEmpty()) {
                throw new IllegalArgumentException("Keyword is null or empty");
            }
            TimeUnit.SECONDS.sleep(5);
            return "Invoice description: %s".formatted(keyword);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /*@Override
    public Invoice create(int customerId, int orderId, String keyword) {
        try (var es = Executors.newVirtualThreadPerTaskExecutor()) {
            var foundCustomer = es.submit(() -> customerService.getById(customerId));
            var foundOrder = es.submit(() -> orderService.getById(orderId));
            var invoiceDescription = es.submit(() -> createDescription(keyword));

            return new Invoice(
                    foundOrder.get(),
                    foundCustomer.get(),
                    invoiceDescription.get()
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }*/

    @Override
    public Invoice create(int customerId, int orderId, String keyword) {
        // try (var scope = new StructuredTaskScope.ShutdownOnSuccess<>()
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            var foundCustomer = scope.fork(() -> customerService.getById(customerId));
            var foundOrder = scope.fork(() -> orderService.getById(orderId));
            var invoiceDescription = scope.fork(() -> createDescription(keyword));

            scope.join();
            scope.throwIfFailed();

            return new Invoice(
                    foundOrder.resultNow(),
                    foundCustomer.resultNow(),
                    invoiceDescription.resultNow()
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

}
