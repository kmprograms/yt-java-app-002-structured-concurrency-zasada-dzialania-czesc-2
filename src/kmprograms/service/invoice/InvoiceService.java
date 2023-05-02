package kmprograms.service.invoice;

import kmprograms.model.Invoice;

public interface InvoiceService {
    Invoice create(int customerId, int orderId, String keyword);
}
