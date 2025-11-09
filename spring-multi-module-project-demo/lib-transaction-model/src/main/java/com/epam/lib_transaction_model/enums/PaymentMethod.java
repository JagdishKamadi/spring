package com.epam.lib_transaction_model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents the various payment methods supported by the system.
 * <p>
 * Each method corresponds to a distinct way of initiating or completing
 * a transaction, such as card payments, UPI, or cash.
 * </p>
 */
public enum PaymentMethod {

    /**
     * Unified Payments Interface-based transaction.
     */
    UPI("UPI"),

    /**
     * Payment made using a credit card.
     */
    CREDIT_CARD("CREDIT_CARD"),

    /**
     * Payment made using a debit card.
     */
    DEBIT_CARD("DEBIT_CARD"),

    /**
     * Online payment through net banking.
     */
    NET_BANKING("NET_BANKING"),

    /**
     * Payment through a digital wallet (e.g., Paytm, PhonePe).
     */
    WALLET("WALLET"),

    /**
     * Cash or cash-on-delivery payment.
     */
    CASH("CASH"),

    /**
     * Payment initiated by scanning a QR code.
     */
    QR_CODE("QR_CODE"),

    /**
     * Payment using EMI or Buy Now Pay Later option.
     */
    EMI("EMI"),

    @JsonEnumDefaultValue
    UNKNOWN("Unknown");

    private final String code;

    PaymentMethod(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return this.code;
    }

    @JsonCreator
    public static PaymentMethod fromValue(String code) {
        for (PaymentMethod pm : PaymentMethod.values()) {
            if (pm.code.equalsIgnoreCase(code)) {
                return pm;
            }
        }
        return UNKNOWN;
    }
}