package org.soapService.Validations;

import org.soapService.Common.HTTPStatusCode;
import org.soapService.Exceptions.ValidationException;

public class ReportUserServiceValidation {
    public void validateCreateReport(String content, String reportedId, String reporterId) throws ValidationException {
        if (content == null || content.trim().isEmpty()) {
            throw new ValidationException(HTTPStatusCode.BAD_REQUEST.getCodeStr(), "Content is required");
        }
        if (reportedId == null || reportedId.trim().isEmpty()) {
            throw new ValidationException(HTTPStatusCode.BAD_REQUEST.getCodeStr(), "Reported ID is required");
        }
        if (reporterId == null || reporterId.trim().isEmpty()) {
            throw new ValidationException(HTTPStatusCode.BAD_REQUEST.getCodeStr(), "Reporter ID is required");
        }
    }

    public void validateBlockUser(String reportedId) throws ValidationException {
        if (reportedId == null || reportedId.trim().isEmpty()) {
            throw new ValidationException(HTTPStatusCode.BAD_REQUEST.getCodeStr(), "Reported ID is required");
        }
    }

    public void validateDeleteReportUser(String reportId) throws ValidationException {
        if (reportId == null || reportId.trim().isEmpty()) {
            throw new ValidationException(HTTPStatusCode.BAD_REQUEST.getCodeStr(), "Report ID is required");
        }
    }
}
