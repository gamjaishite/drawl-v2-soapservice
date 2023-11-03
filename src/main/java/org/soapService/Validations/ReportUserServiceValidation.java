package org.soapService.Validations;

import org.soapService.Common.HTTPStatusCode;
import org.soapService.Exceptions.ValidationException;

public class ReportUserServiceValidation {
    public void validateCreateReport(String content, String reportedId, String reporterId) throws ValidationException {
        if (content == null || content.trim().equals("")) {
            throw new ValidationException(HTTPStatusCode.BAD_REQUEST.getCodeStr(), "Content is required");
        }
        if (reportedId == null || reportedId.trim().equals("")) {
            throw new ValidationException(HTTPStatusCode.BAD_REQUEST.getCodeStr(), "Reported ID is required");
        }
        if (reporterId == null || reporterId.trim().equals("")) {
            throw new ValidationException(HTTPStatusCode.BAD_REQUEST.getCodeStr(), "Reporter ID is required");
        }
    }
}
