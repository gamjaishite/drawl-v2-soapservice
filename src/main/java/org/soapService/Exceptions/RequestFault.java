package org.soapService.Exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestFault {
    private String faultCode;
    private String faultString;
}
