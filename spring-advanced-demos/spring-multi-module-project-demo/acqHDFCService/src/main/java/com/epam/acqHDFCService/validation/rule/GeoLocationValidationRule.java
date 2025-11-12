package com.epam.acqHDFCService.validation.rule;

import com.epam.lib_transaction_adapter.exception.InvalidTransactionRequestException;
import com.epam.lib_transaction_adapter.validation.TransactionRequestValidator;
import com.epam.lib_transaction_model.model.TransactionRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Validates that the {@link TransactionRequest} contains a valid geo-location.
 * <p>
 * Ensures the geo-location field is not null or empty and that it represents a
 * location within India ("IN"). This validation helps confirm that transactions
 * originate from or are intended for supported geographic regions.
 * </p>
 */
@Component
@Slf4j
public class GeoLocationValidationRule implements TransactionRequestValidator {

    private static final String SUPPORTED_COUNTRY_CODE = "IN";

    @Override
    public void validateRequest(TransactionRequest transactionRequest) {
        if (transactionRequest == null) {
            log.error("Transaction request is null.");
            throw new InvalidTransactionRequestException("Transaction request cannot be null.");
        }

        String geoLocation = Optional.ofNullable(transactionRequest.getGeoLocation())
                .orElseThrow(() -> {
                    log.warn("Geo-location is missing in transaction request.");
                    return new InvalidTransactionRequestException(
                            "transactionRequest.geoLocation",
                            "Geo-location must not be null or empty."
                    );
                });

        log.debug("Validating geo-location for transaction: {}", geoLocation);

        if (!geoLocation.contains(SUPPORTED_COUNTRY_CODE)) {
            log.warn("Unsupported geo-location detected: {}. Supported country code: {}", geoLocation, SUPPORTED_COUNTRY_CODE);
            throw new InvalidTransactionRequestException(
                    "transactionRequest.geoLocation",
                    String.format("Invalid geo-location. Supported country code: %s", SUPPORTED_COUNTRY_CODE)
            );
        }

        log.info("Geo-location validation successful for: {}", geoLocation);
    }
}