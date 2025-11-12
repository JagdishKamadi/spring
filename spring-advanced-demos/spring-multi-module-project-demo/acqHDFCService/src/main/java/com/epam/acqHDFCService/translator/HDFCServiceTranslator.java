package com.epam.acqHDFCService.translator;

import com.epam.lib_transaction_adapter.translator.TransactionRequestTranslator;
import com.epam.lib_transaction_adapter.validation.TransactionRequestValidator;
import com.epam.lib_transaction_model.model.TransactionRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class HDFCServiceTranslator implements TransactionRequestTranslator {

    private final List<TransactionRequestValidator> transactionRequestValidatorList;

    public HDFCServiceTranslator(List<TransactionRequestValidator> transactionRequestValidatorList) {
        this.transactionRequestValidatorList = transactionRequestValidatorList;
    }

    @Override
    public TransactionRequest transformRequest(TransactionRequest transactionRequest) {
        log.info("\n\n ||| I am calling from HDFCServiceTranslator class |||\n\n");
        transactionRequestValidatorList.forEach(transactionRequestValidator -> transactionRequestValidator.validateRequest(transactionRequest));
        return transactionRequest;
    }
}
