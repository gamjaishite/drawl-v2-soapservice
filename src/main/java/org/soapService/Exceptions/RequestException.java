package org.soapService.Exceptions;

import lombok.NoArgsConstructor;

import javax.xml.ws.WebFault;

@NoArgsConstructor
@WebFault(name = "RequestFault", faultBean = "org.soapService.Exceptions.RequestFault")
public class RequestException extends Exception {
    private RequestFault fault;

    protected RequestException(RequestFault fault) {
        super(fault.getFaultString());
        this.fault = fault;
    }

    public RequestException(String code, String message) {
        super(message);
        this.fault = new RequestFault();
        this.fault.setFaultString(message);
        this.fault.setFaultCode(code);
    }

    public RequestFault getFaultInfo() {
        return fault;
    }
}

