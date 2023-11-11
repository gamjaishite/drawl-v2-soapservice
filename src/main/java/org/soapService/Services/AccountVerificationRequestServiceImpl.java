package org.soapService.Services;

import org.soapService.Common.HTTPStatusCode;
import org.soapService.Common.ServiceResponse;
import org.soapService.Domain.AccountVerificationRequest;
import org.soapService.Exceptions.RequestException;
import org.soapService.Exceptions.ValidationException;
import org.soapService.Repository.AccountVerificationRequestRepository;
import org.soapService.Validations.AccountVerificationRequestValidation;

import javax.jws.WebService;
import javax.xml.ws.soap.SOAPFaultException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface = "org.soapService.Services.AccountVerificationRequestService")
public class AccountVerificationRequestServiceImpl extends BaseService implements AccountVerificationRequestService {
    private static AccountVerificationRequestRepository accountVerificationRepository = new AccountVerificationRequestRepository();
    private static AccountVerificationRequestValidation accountVerificationServiceValidation = new AccountVerificationRequestValidation();

    public List<AccountVerificationRequest> getRequests() throws SOAPFaultException {
        return null;
    }

    public ServiceResponse<AccountVerificationRequest> createRequest(String userId) throws SOAPFaultException {
        List<AccountVerificationRequest> lru = new ArrayList<>();
        try {
            accountVerificationServiceValidation.validateCreateVerificationRequest(userId);

            AccountVerificationRequest accountVerificationRequest = new AccountVerificationRequest();
            accountVerificationRequest.setUserId(userId);

            lru.add(accountVerificationRequest);
            accountVerificationRepository.add(accountVerificationRequest);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            new RequestException(HTTPStatusCode.BAD_REQUEST.getCodeStr(), e.getMessage());
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            new RequestException(HTTPStatusCode.INTERNAL_SERVER_ERROR.getCodeStr(),
                    "Something went wrong, please try again later");
        }

        ServiceResponse<AccountVerificationRequest> response = new ServiceResponse<>();
        response.setStatus(HTTPStatusCode.OK.getCodeStr());
        response.setMessage("Report successfully created");
        response.setData(lru);

        System.out.println(response.toString());
        return response;
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
