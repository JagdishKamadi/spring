package com.epam.acqHDFCService.validation.rule;

import com.epam.lib_transaction_adapter.exception.InvalidTransactionRequestException;
import com.epam.lib_transaction_adapter.validation.TransactionRequestValidator;
import com.epam.lib_transaction_model.enums.TransactionStatus;
import com.epam.lib_transaction_model.model.TransactionRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.EnumSet;
import java.util.Set;

/**
 * Validates that the {@link TransactionRequest} contains a supported transaction status.
 * <p>
 * Ensures the transaction status field is not null and matches one of the predefined supported
 * values defined in {@link TransactionStatus}.
 * </p>
 */
@Component
@Slf4j
public class TransactionStatusValidationRule implements TransactionRequestValidator {

    private static final Set<TransactionStatus> SUPPORTED_TRANSACTION_STATUS = EnumSet.allOf(TransactionStatus.class);

    @Override
    public void validateRequest(TransactionRequest transactionRequest) {
        if (transactionRequest == null) {
            log.error("Transaction request is null.");
            throw new InvalidTransactionRequestException("Transaction request cannot be null.");
        }

        TransactionStatus transactionStatus = transactionRequest.getStatus();
        log.debug("Validating transaction status for transaction: {}", transactionStatus);

        if (transactionStatus == null) {
            log.warn("Transaction status is null or missing in transaction request.");
            throw new InvalidTransactionRequestException("transactionRequest.transactionStatus", "Transaction status must not be null or empty.");
        }

        if (transactionStatus == TransactionStatus.UNKNOWN || !SUPPORTED_TRANSACTION_STATUS.contains(transactionStatus)) {
            SUPPORTED_TRANSACTION_STATUS.remove(TransactionStatus.UNKNOWN);
            log.warn("Unsupported transaction status: {}. Supported transaction statuses: {}", transactionStatus, SUPPORTED_TRANSACTION_STATUS);
            throw new InvalidTransactionRequestException("transactionRequest.transactionStatus", "Invalid transaction status. Supported transaction statuses are: " + SUPPORTED_TRANSACTION_STATUS);
        }

        log.info("Transaction status validation successful for: {}", transactionStatus);
    }
}