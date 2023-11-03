package org.soapService.Exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ValidationException extends Exception {
    private String status;
    private String message;

    public ValidationException(String status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }
}
