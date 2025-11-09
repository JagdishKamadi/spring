package com.epam.acqHDFCService.translator;

import com.epam.lib_transaction_adapter.translator.TransactionRequestTranslator;
import com.epam.lib_transaction_model.model.TransactionRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HDFCServiceTranslator implements TransactionRequestTranslator {
    @Override
    public TransactionRequest transformRequest(TransactionRequest transactionRequest) {
        log.info("\n\n ||| I am calling from HDFCServiceTranslator class |||\n\n");
        return transactionRequest;
    }
}
