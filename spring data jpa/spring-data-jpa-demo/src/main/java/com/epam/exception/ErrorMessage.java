package com.epam.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorMessage {
    private String timestamp;
    private String status;
    private String error;
    private String path;
}
