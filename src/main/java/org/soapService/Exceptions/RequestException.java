package org.soapService.Exceptions;

import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.ws.soap.SOAPFaultException;

public class RequestException {
    public RequestException(String faultCode, String faultString) throws SOAPFaultException {
        try {
            SOAPFactory soapFactory = SOAPFactory.newInstance();
            SOAPFault soapFault = soapFactory.createFault();
            soapFault.setFaultCode(faultCode);
            soapFault.setFaultString(faultString);

            throw new SOAPFaultException(soapFault);
        } catch (SOAPFaultException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

