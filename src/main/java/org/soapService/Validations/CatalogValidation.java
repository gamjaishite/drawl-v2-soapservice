package org.soapService.Validations;

import org.soapService.Common.HTTPStatusCode;
import org.soapService.Exceptions.ValidationException;

public class CatalogValidation {
    public void validateCreateCatalogRequest(String uuid, String title, String description, String poster,
            String trailer, String category) throws ValidationException {
        if (uuid == null || uuid.trim().isEmpty()) {
            throw new ValidationException(HTTPStatusCode.BAD_REQUEST.getCodeStr(), "Title is required");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new ValidationException(HTTPStatusCode.BAD_REQUEST.getCodeStr(), "Title is required");
        }
        if (poster == null || poster.trim().isEmpty()) {
            throw new ValidationException(HTTPStatusCode.BAD_REQUEST.getCodeStr(), "Poster is required");
        }
        if (category == null || category.trim().isEmpty()) {
            throw new ValidationException(HTTPStatusCode.BAD_REQUEST.getCodeStr(), "Category is required");
        }
    }

    public void validateAcceptCatalogRequest(String uuid) throws ValidationException {
        System.out.println(uuid);
        System.out.println(uuid.trim());
        if (uuid == null || uuid.trim().equals("")) {
            throw new ValidationException(HTTPStatusCode.BAD_REQUEST.getCodeStr(), "User ID is required");
        }
    }

    public void validateRejectCatalogRequest(String uuid) throws ValidationException {
        if (uuid == null || uuid.trim().equals("")) {
            throw new ValidationException(HTTPStatusCode.BAD_REQUEST.getCodeStr(), "User ID is required");
        }
    }

    public void validateDeleteCatalogRequest(int requestId) throws ValidationException {
        System.out.print(requestId);
        if (requestId <= 0) {
            throw new ValidationException(HTTPStatusCode.BAD_REQUEST.getCodeStr(), "Request ID is required");
        }
    }

}
