package com.epam.lib_transaction_adapter.delegate;

import com.epam.lib_transaction_model.model.TransactionRequest;

public interface TransactionApiDelegate {
    TransactionRequest processTransactionRequest(TransactionRequest transactionRequest);
}
