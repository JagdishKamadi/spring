package com.epam.lib_transaction_adapter.controller;

import com.epam.lib_transaction_adapter.delegate.TransactionApiDelegate;
import com.epam.lib_transaction_model.model.TransactionRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

public interface TransactionApi {
    TransactionApiDelegate getDelegate();

    @PostMapping(value = "/process")
    @ResponseBody
    default TransactionRequest processTransactionRequest(@RequestBody TransactionRequest transactionRequest) {
        return this.getDelegate().processTransactionRequest(transactionRequest);
    }
}
