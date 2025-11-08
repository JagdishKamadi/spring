package com.epam.lib_transaction_model.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Base class representing a complete transaction request.
 * Holds all core transaction details and linked child objects
 * such as Merchant info and list of TransactionItems.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionRequest {

    private Long id;
    private String transactionId;
    private String referenceNumber;
    private BigDecimal amount;
    private String currency;
    private String paymentMethod;
    private TransactionStatus status;
    private LocalDateTime initiatedTime;
    private LocalDateTime completedTime;
    private String ipAddress;
    private String deviceInfo;
    private String geoLocation;
    private String remarks;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Child objects
     **/
    private Merchant merchant;
    private List<TransactionItem> items;
}
