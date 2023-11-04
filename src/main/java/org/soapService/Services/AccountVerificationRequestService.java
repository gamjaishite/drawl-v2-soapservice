package org.soapService.Services;

import org.soapService.Domain.AccountVerificationRequest;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.soap.SOAPFaultException;
import java.util.List;

@WebService
@HandlerChain(file = "handler-chain.xml")
public interface AccountVerificationRequestService {
    @WebMethod(operationName = "GetRequests")
    @RequestWrapper(className = "AccountVerificationRequestService.GetRequests")
    public List<AccountVerificationRequest> getRequests() throws SOAPFaultException;

    @WebMethod(operationName = "CreateRequest")
    @RequestWrapper(className = "AccountVerificationRequestService.CreateRequest")
    public boolean createRequest() throws SOAPFaultException;

    @WebMethod(operationName = "AcceptRequest")
    @RequestWrapper(className = "AccountVerificationRequestService.AcceptRequest")
    public boolean acceptRequest(@WebParam(name = "userId") int userId) throws SOAPFaultException;

    @WebMethod(operationName = "RejectRequest")
    @RequestWrapper(className = "AccountVerificationRequestService.RejectRequest")
    public boolean rejectRequest(@WebParam(name = "userId") int userId) throws SOAPFaultException;

    @WebMethod(operationName = "DeleteRequest")
    @RequestWrapper(className = "AccountVerificationRequestService.DeleteRequest")
    public boolean deleteRequest(@WebParam(name = "requestId") int requestId) throws SOAPFaultException;
}
