package org.soapService.Services;

import org.soapService.Domain.AccountVerificationRequest;
import org.soapService.Exceptions.RequestException;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "org.soapService.Services.AccountVerificationRequestService")
public class AccountVerificationRequestServiceImpl extends BaseService implements AccountVerificationRequestService {
    public List<AccountVerificationRequest> getRequests() throws RequestException {
        return null;
    }

    public boolean createRequest() throws RequestException {
        return false;
    }

    public boolean acceptRequest(int userId) throws RequestException {
        return false;
    }

    public boolean rejectRequest(int userId) throws RequestException {
        return false;
    }

    public boolean deleteRequest(int requestId) throws RequestException {
        return false;
    }
}
