package com.epam.lib_transaction_model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents the current lifecycle state of a transaction.
 * <p>
 * Each status reflects a specific stage in transaction processing,
 * from initiation to completion or reversal.
 * </p>
 */
public enum TransactionStatus {

    /**
     * Transaction has been created but not yet processed.
     */
    INITIATED("INITIATED"),

    /**
     * Transaction is currently being processed by the system or acquirer.
     */
    PROCESSING("PROCESSING"),

    /**
     * Transaction completed successfully.
     */
    SUCCESS("SUCCESS"),

    /**
     * Transaction failed due to an error or declined authorization.
     */
    FAILED("FAILED"),

    /**
     * Transaction was cancelled before completion.
     */
    CANCELLED("CANCELLED"),

    /**
     * Transaction amount was refunded after successful processing.
     */
    REFUNDED("REFUNDED"),

    @JsonEnumDefaultValue
    UNKNOWN("UNKNOWN");

    private final String code;

    TransactionStatus(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return this.code;
    }

    @JsonCreator
    public static TransactionStatus fromValue(String code) {
        for (TransactionStatus ts : TransactionStatus.values()) {
            if (ts.code.equalsIgnoreCase(code)) {
                return ts;
            }
        }
        return UNKNOWN;
    }
}