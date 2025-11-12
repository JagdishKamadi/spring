package com.epam.lib_transaction_adapter.validation;

import com.epam.lib_transaction_adapter.exception.InvalidTransactionRequestException;
import com.epam.lib_transaction_model.model.TransactionRequest;

/**
 * Defines contract for validating incoming TransactionRequest objects.
 * Implementations should ensure that all required fields and business rules are satisfied.
 */
public interface TransactionRequestValidator {
    /**
     * Validates the given transaction request.
     *
     * @param transactionRequest the request to validate
     * @throws InvalidTransactionRequestException if validation fails
     */
    void validateRequest(TransactionRequest transactionRequest) throws InvalidTransactionRequestException;
}
