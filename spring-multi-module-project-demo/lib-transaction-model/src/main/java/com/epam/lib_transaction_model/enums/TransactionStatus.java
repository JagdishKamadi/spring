package com.epam.lib_transaction_model.enums;

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
    INITIATED,

    /**
     * Transaction is currently being processed by the system or acquirer.
     */
    PROCESSING,

    /**
     * Transaction completed successfully.
     */
    SUCCESS,

    /**
     * Transaction failed due to an error or declined authorization.
     */
    FAILED,

    /**
     * Transaction was cancelled before completion.
     */
    CANCELLED,

    /**
     * Transaction amount was refunded after successful processing.
     */
    REFUNDED
}
