package kmprograms;

import kmprograms.service.customer.CustomerServiceImpl;
import kmprograms.service.invoice.InvoiceServiceImpl;
import kmprograms.service.order.OrderServiceImpl;

public class App {
    public static void main(String[] args) {
        try {
            var customerService = new CustomerServiceImpl();
            var orderService = new OrderServiceImpl();
            var invoiceService = new InvoiceServiceImpl(customerService, orderService);

            var invoice = invoiceService.create(11, 1, "java");
            System.out.println(invoice);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
