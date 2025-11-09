package com.epam.lib_transaction_adapter.delegate;

import com.epam.lib_transaction_adapter.converter.TransactionRequestTranslator;
import com.epam.lib_transaction_model.model.TransactionRequest;
import org.springframework.stereotype.Component;

/**
 * Default implementation of TransactionApiDelegate.
 * Handles preprocessing and transformation of incoming TransactionRequests
 * using the TransactionRequestTranslator.
 */
@Component
public class TransactionApiDelegateImpl implements TransactionApiDelegate {

    private final TransactionRequestTranslator transactionRequestTranslator;

    public TransactionApiDelegateImpl(TransactionRequestTranslator transactionRequestTranslator) {
        this.transactionRequestTranslator = transactionRequestTranslator;
    }

    @Override
    public TransactionRequest processTransactionRequest(TransactionRequest transactionRequest) {
        // Apply common transformation logic
        transactionRequest = transactionRequestTranslator.transformRequest(transactionRequest);
        return transactionRequest;
    }
}
