package com.epam.lib_transaction_adapter.controller;

import com.epam.lib_transaction_adapter.delegate.TransactionApiDelegate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Base REST controller for transaction operations.
 * Implements the TransactionApi interface and delegates requests
 * to the configured TransactionApiDelegate.
 */
@Controller
@RequestMapping(value = "/api/v1/transactions")
public class TransactionApiController implements TransactionApi {

    private final TransactionApiDelegate transactionApiDelegate;

    public TransactionApiController(TransactionApiDelegate transactionApiDelegate) {
        this.transactionApiDelegate = transactionApiDelegate;
    }

    @Override
    public TransactionApiDelegate getDelegate() {
        return this.transactionApiDelegate;
    }
}
