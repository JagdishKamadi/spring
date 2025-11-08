package com.epam.lib_transaction_model.model;

import java.util.List;

/**
 * Represents merchant (seller or payment acceptor) information
 * associated with one or more transaction requests.
 */
public class Merchant {

    private Long id;
    private String merchantName;
    private String merchantCode;
    private String email;
    private String contactNumber;
    private List<TransactionRequest> transactions;

    public Merchant() {
    }
}
