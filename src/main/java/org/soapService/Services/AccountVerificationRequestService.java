package org.soapService.Services;

import org.soapService.Common.ServiceResponse;
import org.soapService.Domain.AccountVerificationRequest;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.soap.SOAPFaultException;

@WebService
@XmlSeeAlso({ServiceResponse.class})
@HandlerChain(file = "handler-chain.xml")
public interface AccountVerificationRequestService {
    @WebMethod(operationName = "GetRequests")
    @RequestWrapper(className = "AccountVerificationRequestService.GetRequests")
    public ServiceResponse<AccountVerificationRequest> getAccountVerificationRequests() throws SOAPFaultException;

    @WebMethod(operationName = "CreateRequest")
    @RequestWrapper(className = "AccountVerificationRequestService.AccountVerificationCreateRequest")
    public ServiceResponse<AccountVerificationRequest> createAccountVerificationRequest(@WebParam(name = "userId") String userId)
            throws SOAPFaultException;

    @WebMethod(operationName = "AcceptRequest")
    @RequestWrapper(className = "AccountVerificationRequestService.AcceptRequest")
    public ServiceResponse acceptAccountVerificationRequest(@WebParam(name = "userId") int userId) throws SOAPFaultException;

    @WebMethod(operationName = "RejectRequest")
    @RequestWrapper(className = "AccountVerificationRequestService.RejectRequest")
    public ServiceResponse rejectAccountVerificationRequest(@WebParam(name = "userId") int userId) throws SOAPFaultException;

    @WebMethod(operationName = "DeleteRequest")
    @RequestWrapper(className = "AccountVerificationRequestService.DeleteRequest")
    public ServiceResponse deleteAccountVerificationRequest(@WebParam(name = "requestId") int requestId) throws SOAPFaultException;
}
