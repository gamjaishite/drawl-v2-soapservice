package org.soapService.Services;

import org.soapService.Domain.CatalogRequest;

import javax.activation.DataHandler;
import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import java.util.List;

@WebService
@HandlerChain(file = "handler-chain.xml")
public interface CatalogRequestService {
    @WebMethod(operationName = "CreateRequest")
    @RequestWrapper(className = "CatalogRequestService.CreateRequest")
    public boolean createRequest(@WebParam(name = "title") String title,
                                 @WebParam(name = "description") String description,
                                 @WebParam(name = "poster") String poster,
                                 @WebParam(name = "trailer") String trailer,
                                 @WebParam(name = "category") String category);

    @WebMethod(operationName = "GetRequests")
    @RequestWrapper(className = "CatalogRequestService.GetRequests")
    public List<CatalogRequest> getRequests();

    @WebMethod(operationName = "GetRequest")
    @RequestWrapper(className = "CatalogRequestService.GetRequest")
    public CatalogRequest getRequest(@WebParam(name = "requestId") int requestId);

    @WebMethod(operationName = "AcceptRequest")
    @RequestWrapper(className = "CatalogRequestService.AcceptRequest")
    public boolean acceptRequest(@WebParam(name = "requestId") int requestId);

    @WebMethod(operationName = "RejectRequest")
    @RequestWrapper(className = "CatalogRequestService.RejectRequest")
    public boolean rejectRequest(@WebParam(name = "requestId") int requestId);

    @WebMethod(operationName = "DeleteRequest")
    @RequestWrapper(className = "CatalogRequestService.DeleteRequest")
    public boolean deleteRequest(@WebParam(name = "requestId") int requestId);

    @WebMethod(operationName = "TestUpload")
    @RequestWrapper(className = "CatalogRequestService.TestUpload")
    public String testUpload(@WebParam(name = "data") DataHandler data);
}
