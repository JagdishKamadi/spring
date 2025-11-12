package com.epam.lib_transaction_model.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents merchant (seller or payment acceptor) information
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Merchant {

    private Long id;
    private String merchantName;
    private String merchantCode;
    private String email;
    private String contactNumber;
}
