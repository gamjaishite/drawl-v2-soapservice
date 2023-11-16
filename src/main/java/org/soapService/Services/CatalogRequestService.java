package org.soapService.Services;

import org.soapService.Common.ServiceResponse;
import org.soapService.Domain.CatalogRequest;
import org.soapService.Domain.GetAllResponse;

import javax.activation.DataHandler;
import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.soap.SOAPFaultException;

@WebService
@HandlerChain(file = "handler-chain.xml")
public interface CatalogRequestService {
        @WebMethod(operationName = "CatalogCreateRequest")
        @RequestWrapper(className = "CatalogRequestService.CatalogCreateRequest")
        public ServiceResponse<CatalogRequest> createCatalogRequest(@WebParam(name = "uuid") String uuid,
                        @WebParam(name = "title") String title,
                        @WebParam(name = "description") String description,
                        @WebParam(name = "poster") String poster,
                        @WebParam(name = "trailer") String trailer,
                        @WebParam(name = "category") String category) throws SOAPFaultException;

        @WebMethod(operationName = "GetRequests")
        @RequestWrapper(className = "CatalogRequestService.GetRequests")
        public ServiceResponse<GetAllResponse<CatalogRequest>> getCatalogRequests(@WebParam(name = "page") Integer page,
                        @WebParam(name = "pageSize") Integer pageSize) throws SOAPFaultException;

        @WebMethod(operationName = "GetRequest")
        @RequestWrapper(className = "CatalogRequestService.GetRequest")
        public ServiceResponse<CatalogRequest> getCatalogRequest(@WebParam(name = "requestId") int requestId)
                        throws SOAPFaultException;

        @WebMethod(operationName = "AcceptRequest")
        @RequestWrapper(className = "CatalogRequestService.AcceptRequest")
        public ServiceResponse<CatalogRequest> acceptCatalogRequest(@WebParam(name = "uuid") String uuid)
                        throws SOAPFaultException;

        @WebMethod(operationName = "RejectRequest")
        @RequestWrapper(className = "CatalogRequestService.RejectRequest")
        public ServiceResponse<CatalogRequest> rejectCatalogRequest(@WebParam(name = "uuid") String uuid)
                        throws SOAPFaultException;

        @WebMethod(operationName = "DeleteRequest")
        @RequestWrapper(className = "CatalogRequestService.DeleteRequest")
        public ServiceResponse<CatalogRequest> deleteCatalogRequest(@WebParam(name = "requestId") int requestId)
                        throws SOAPFaultException;

        @WebMethod(operationName = "TestUpload")
        @RequestWrapper(className = "CatalogRequestService.TestUpload")
        public ServiceResponse<CatalogRequest> testUpload(@WebParam(name = "data") DataHandler data)
                        throws SOAPFaultException;
}
