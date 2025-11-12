package com.epam.lib_transaction_adapter.translator;

import com.epam.lib_transaction_model.model.TransactionRequest;

/**
 * Defines contract for transforming or enriching a TransactionRequest.
 * Implementations can modify or map request data before further processing.
 */
public interface TransactionRequestTranslator {
    /**
     * Transforms the incoming TransactionRequest as per business or acquirer-specific needs.
     *
     * @param transactionRequest incoming transaction request
     * @return transformed or modified transaction request
     */
    TransactionRequest transformRequest(TransactionRequest transactionRequest);
}
