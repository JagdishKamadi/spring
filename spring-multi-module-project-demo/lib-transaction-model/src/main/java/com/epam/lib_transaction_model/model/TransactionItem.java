package com.epam.lib_transaction_model.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Represents an individual item or component of a transaction.
 */
public class TransactionItem {

    private Long id;
    private String itemName;
    private Integer quantity;
    private BigDecimal price;
    private String category;
    private TransactionRequest parentTransaction;

    public TransactionItem() {
    }
}