package org.soapService.Services;

import org.soapService.Domain.AccountVerificationRequest;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "org.soapService.Services.AccountVerificationRequestService")
public class AccountVerificationRequestServiceImpl extends BaseService implements AccountVerificationRequestService {
    public List<AccountVerificationRequest> getRequests() {
        return null;
    }

    public boolean createRequest() {
        return false;
    }

    public boolean acceptRequest(int userId) {
        return false;
    }

    public boolean rejectRequest(int userId) {
        return false;
    }

    public boolean deleteRequest(int requestId) {
        return false;
    }
}
