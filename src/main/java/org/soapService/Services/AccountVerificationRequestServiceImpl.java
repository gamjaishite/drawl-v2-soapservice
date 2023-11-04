package org.soapService.Services;

import org.soapService.Domain.AccountVerificationRequest;

import javax.jws.WebService;
import javax.xml.ws.soap.SOAPFaultException;
import java.util.List;

@WebService(endpointInterface = "org.soapService.Services.AccountVerificationRequestService")
public class AccountVerificationRequestServiceImpl extends BaseService implements AccountVerificationRequestService {
    public List<AccountVerificationRequest> getRequests() throws SOAPFaultException {
        return null;
    }

    public boolean createRequest() throws SOAPFaultException {
        return false;
    }

    public boolean acceptRequest(int userId) throws SOAPFaultException {
        return false;
    }

    public boolean rejectRequest(int userId) throws SOAPFaultException {
        return false;
    }

    public boolean deleteRequest(int requestId) throws SOAPFaultException {
        return false;
    }
}
