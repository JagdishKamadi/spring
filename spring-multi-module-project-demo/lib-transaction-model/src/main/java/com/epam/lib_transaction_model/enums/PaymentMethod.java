package com.epam.lib_transaction_model.enums;

/**
 * Enum representing different payment methods supported by the system.
 * Each method includes a short description for reference.
 */
public enum PaymentMethod {

    UPI("Unified Payments Interface-based transaction"),
    CREDIT_CARD("Payment made using a credit card"),
    DEBIT_CARD("Payment made using a debit card"),
    NET_BANKING("Online payment through net banking"),
    WALLET("Payment through a digital wallet (e.g., Paytm, PhonePe)"),
    CASH("Cash or cash-on-delivery payment"),
    QR_CODE("Payment initiated by scanning a QR code"),
    EMI("Payment using EMI or Buy Now Pay Later option");

    private final String description;

    PaymentMethod(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
