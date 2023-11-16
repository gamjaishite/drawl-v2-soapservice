package org.soapService.Validations;

import org.soapService.Common.HTTPStatusCode;
import org.soapService.Exceptions.ValidationException;

public class AccountVerificationRequestValidation {
    public void validateCreateVerificationRequest(String userId) throws ValidationException {
        if (userId == null || userId.trim().equals("")) {
            throw new ValidationException(HTTPStatusCode.BAD_REQUEST.getCodeStr(), "User ID is required");
        }
    }

    public void validateDeleteVerificationRequest(int requestId) throws ValidationException {
        System.out.print(requestId);
        if (requestId <= 0) {
            throw new ValidationException(HTTPStatusCode.BAD_REQUEST.getCodeStr(), "Request ID is required");
        }
    }

    public void validateAcceptVerificationRequest(String userId, String email) throws ValidationException {
        System.out.println(userId);
        System.out.println(userId.trim());
        if (userId == null || userId.trim().equals("")) {
            throw new ValidationException(HTTPStatusCode.BAD_REQUEST.getCodeStr(), "User ID is required");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new ValidationException(HTTPStatusCode.BAD_REQUEST.getCodeStr(), "Email is required");
        }
    }

    public void validateRejectVerificationRequest(String userId) throws ValidationException {
        if (userId == null || userId.trim().equals("")) {
            throw new ValidationException(HTTPStatusCode.BAD_REQUEST.getCodeStr(), "User ID is required");
        }
    }
}
