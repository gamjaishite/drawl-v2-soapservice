package org.soapService.Validations;

import org.soapService.Common.HTTPStatusCode;
import org.soapService.Exceptions.ValidationException;

public class AccountVerificationRequestValidation {
    public void validateCreateVerificationRequest(String userId) throws ValidationException {
        if (userId == null || userId.trim().equals("")) {
            throw new ValidationException(HTTPStatusCode.BAD_REQUEST.getCodeStr(), "User ID is required");
        }
    }
}
