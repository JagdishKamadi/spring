package com.epam.lib_transaction_adapter.validation.rule;

import com.epam.lib_transaction_adapter.exception.InvalidTransactionRequestException;
import com.epam.lib_transaction_adapter.validation.TransactionRequestValidator;
import com.epam.lib_transaction_model.model.TransactionRequest;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Validates that the {@link TransactionRequest} contains a supported currency.
 * <p>
 * Ensures the currency field is not null and matches one of the predefined supported
 * currency codes.
 * </p>
 */
@Component
public class CurrencyValidationRule implements TransactionRequestValidator {

    private static final List<String> SUPPORTED_CURRENCIES = List.of("INR", "USD", "EUR");

    @Override
    public void validateRequest(TransactionRequest transactionRequest) {
        if (transactionRequest == null) {
            throw new InvalidTransactionRequestException("Transaction request cannot be null.");
        }

        String currency = transactionRequest.getCurrency();

        if (currency == null || currency.isBlank()) {
            throw new InvalidTransactionRequestException("Currency must not be null or empty.");
        }

        if (!SUPPORTED_CURRENCIES.contains(currency)) {
            throw new InvalidTransactionRequestException(
                    "Invalid currency. Supported currencies are: " + SUPPORTED_CURRENCIES
            );
        }
    }
}