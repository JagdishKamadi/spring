package com.epam.lib_transaction_model.model;

/**
 * Enum representing possible transaction states.
 */
public enum TransactionStatus {
    INITIATED,
    PROCESSING,
    SUCCESS,
    FAILED,
    CANCELLED,
    REFUNDED
}
