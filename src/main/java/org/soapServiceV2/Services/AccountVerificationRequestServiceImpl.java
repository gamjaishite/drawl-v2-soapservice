package org.soapServiceV2.Services;

import org.soapServiceV2.Domain.AccountVerificationRequest;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "org.soapServiceV2.Services.AccountVerificationRequestService")
public class AccountVerificationRequestServiceImpl extends BaseService implements AccountVerificationRequestService {
    @WebMethod
    public List<AccountVerificationRequest> getRequests() {
        return null;
    }

    @WebMethod
    public boolean createRequest() {
        return false;
    }

    @WebMethod
    public boolean acceptRequest(int userId) {
        return false;
    }

    @WebMethod
    public boolean rejectRequest(int userId) {
        return false;
    }

    @WebMethod
    public boolean deleteRequest(int requestId) {
        return false;
    }
}
