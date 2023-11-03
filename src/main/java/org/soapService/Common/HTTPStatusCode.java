package org.soapService.Common;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HTTPStatusCode {
    OK(200, "200"),
    BAD_REQUEST(400, "400"),
    UNAUTHORIZED(401, "401"),
    FORBIDDEN(403, "403"),
    NOT_FOUND(404, "404"),
    INTERNAL_SERVER_ERROR(500, "500");

    private final int codeInt;
    private final String codeStr;
}
