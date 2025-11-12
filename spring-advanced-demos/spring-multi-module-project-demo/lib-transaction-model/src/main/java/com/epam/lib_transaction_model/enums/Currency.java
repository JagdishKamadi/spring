package com.epam.lib_transaction_model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Currency {

    USD("USD"),
    EUR("EUR"),
    INR("INR"),
    GBP("GBP"),
    JPY("JPY"),
    AUD("AUD"),
    CAD("CAD"),
    @JsonEnumDefaultValue
    UNKNOWN("UNKNOWN");

    private final String code;

    Currency(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    @JsonCreator
    public static Currency fromValue(String value) {
        for (Currency c : values()) {
            if (c.code.equalsIgnoreCase(value)) {
                return c;
            }
        }
        return UNKNOWN;
    }
}