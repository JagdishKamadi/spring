package com.epam.lib_transaction_adapter.delegate;

import com.epam.lib_transaction_model.model.TransactionRequest;

/**
 * Handles processing of {@link TransactionRequest} objects.
 * <p>
 * Implementations typically perform validation, transformation,
 * and other pre-processing before returning the final request.
 * </p>
 */
public interface TransactionApiDelegate {

    /**
     * Processes the given transaction request.
     *
     * @param transactionRequest the incoming transaction data
     * @return the processed transaction request
     */
    TransactionRequest processTransactionRequest(TransactionRequest transactionRequest);
}
