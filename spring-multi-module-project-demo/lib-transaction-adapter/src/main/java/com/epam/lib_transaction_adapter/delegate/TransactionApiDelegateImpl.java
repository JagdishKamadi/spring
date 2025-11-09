package com.epam.lib_transaction_adapter.delegate;

import com.epam.lib_transaction_adapter.translator.TransactionRequestTranslator;
import com.epam.lib_transaction_adapter.validation.TransactionRequestValidator;
import com.epam.lib_transaction_model.model.TransactionRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Default delegate implementation that validates and transforms
 * incoming {@link TransactionRequest} objects before further processing.
 */
@Slf4j
@Component
public class TransactionApiDelegateImpl implements TransactionApiDelegate {

    private final TransactionRequestTranslator transactionRequestTranslator;
    private final List<TransactionRequestValidator> transactionRequestValidators;

    public TransactionApiDelegateImpl(TransactionRequestTranslator transactionRequestTranslator,
                                      List<TransactionRequestValidator> transactionRequestValidators) {
        this.transactionRequestTranslator = transactionRequestTranslator;
        this.transactionRequestValidators = transactionRequestValidators;
    }

    @Override
    public TransactionRequest processTransactionRequest(TransactionRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Transaction request cannot be null.");
        }

        transactionRequestValidators.forEach(validator -> validator.validateRequest(request));

        TransactionRequest transformedRequest = transactionRequestTranslator.transformRequest(request);

        log.debug("Processed transaction request: {}", transformedRequest);
        return transformedRequest;
    }
}