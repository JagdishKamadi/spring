package com.epam.lib_transaction_adapter.validation.rule;

import com.epam.lib_transaction_adapter.exception.InvalidTransactionRequestException;
import com.epam.lib_transaction_adapter.validation.TransactionRequestValidator;
import com.epam.lib_transaction_model.enums.PaymentMethod;
import com.epam.lib_transaction_model.model.TransactionRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.EnumSet;
import java.util.Set;

/**
 * Validates that the {@link TransactionRequest} contains a supported payment method.
 * <p>
 * Ensures the payment method field is not null and matches one of the predefined supported
 * values defined in {@link PaymentMethod}.
 * </p>
 */
@Component
@Slf4j
public class PaymentMethodValidationRule implements TransactionRequestValidator {

    private static final Set<PaymentMethod> SUPPORTED_PAYMENT_METHODS = EnumSet.allOf(PaymentMethod.class);

    @Override
    public void validateRequest(TransactionRequest transactionRequest) {
        if (transactionRequest == null) {
            log.error("Transaction request is null.");
            throw new InvalidTransactionRequestException("Transaction request cannot be null.");
        }

        PaymentMethod paymentMethod = transactionRequest.getPaymentMethod();
        log.debug("Validating payment method for transaction: {}", paymentMethod);

        if (paymentMethod == null) {
            log.warn("Payment method is null or missing in transaction request.");
            throw new InvalidTransactionRequestException("transactionRequest.paymentMethod", "Payment method must not be null or empty.");
        }

        if (paymentMethod == PaymentMethod.UNKNOWN || !SUPPORTED_PAYMENT_METHODS.contains(paymentMethod)) {
            SUPPORTED_PAYMENT_METHODS.remove(PaymentMethod.UNKNOWN);
            log.warn("Unsupported payment method: {}. Supported payment methods: {}", paymentMethod, SUPPORTED_PAYMENT_METHODS);
            throw new InvalidTransactionRequestException("transactionRequest.paymentMethod", "Invalid payment method. Supported payment methods are: " + SUPPORTED_PAYMENT_METHODS);
        }
        log.info("Payment method validation successful for: {}", paymentMethod);
    }
}