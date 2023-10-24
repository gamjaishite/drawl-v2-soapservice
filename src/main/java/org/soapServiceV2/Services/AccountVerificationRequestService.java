package org.soapServiceV2.Services;

import org.soapServiceV2.Domain.AccountVerificationRequest;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface AccountVerificationRequestService {
    @WebMethod
    public List<AccountVerificationRequest> getRequests();

    @WebMethod
    public boolean createRequest();

    @WebMethod
    public boolean acceptRequest(int userId);

    @WebMethod
    public boolean rejectRequest(int userId);

    @WebMethod
    public boolean deleteRequest(int requestId);
}
