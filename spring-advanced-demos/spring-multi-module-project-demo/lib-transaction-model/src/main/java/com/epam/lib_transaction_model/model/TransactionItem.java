package com.epam.lib_transaction_model.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Represents an individual item or component of a transaction.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionItem {

    private Long id;
    private String itemName;
    private Integer quantity;
    private BigDecimal price;
    private String category;
}