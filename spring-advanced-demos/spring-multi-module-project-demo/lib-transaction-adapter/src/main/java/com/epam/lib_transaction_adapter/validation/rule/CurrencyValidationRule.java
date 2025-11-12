package com.epam.lib_transaction_adapter.validation.rule;

import com.epam.lib_transaction_adapter.exception.InvalidTransactionRequestException;
import com.epam.lib_transaction_adapter.validation.TransactionRequestValidator;
import com.epam.lib_transaction_model.enums.Currency;
import com.epam.lib_transaction_model.model.TransactionRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.EnumSet;
import java.util.Set;

/**
 * Validates that the {@link TransactionRequest} contains a supported currency.
 * <p>
 * Ensures the currency field is not null and matches one of the predefined supported
 * currency codes defined in {@link Currency}.
 * </p>
 */
@Component
@Slf4j
public class CurrencyValidationRule implements TransactionRequestValidator {

    private static final Set<Currency> SUPPORTED_CURRENCIES = EnumSet.allOf(Currency.class);

    @Override
    public void validateRequest(TransactionRequest transactionRequest) {
        log.debug("Starting currency validation for transaction request: {}", transactionRequest);

        if (transactionRequest == null) {
            log.error("Transaction request is null.");
            throw new InvalidTransactionRequestException("Transaction request cannot be null.");
        }

        Currency currency = transactionRequest.getCurrency();

        if (currency == null) {
            log.warn("Currency field is null or empty in the transaction request.");
            throw new InvalidTransactionRequestException("transactionRequest.currency", "Currency must not be null or empty.");
        }

        if (currency == Currency.UNKNOWN || !SUPPORTED_CURRENCIES.contains(currency)) {
            SUPPORTED_CURRENCIES.remove(Currency.UNKNOWN);
            log.warn("Unsupported currency detected: {}. Supported currencies are: {}", currency, SUPPORTED_CURRENCIES);
            throw new InvalidTransactionRequestException("transactionRequest.currency",
                    "Invalid currency. Supported currencies are: " + SUPPORTED_CURRENCIES
            );
        }
        log.info("Currency validation successful for: {}", currency);
    }
}