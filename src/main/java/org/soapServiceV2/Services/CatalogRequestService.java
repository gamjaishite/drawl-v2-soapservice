package org.soapServiceV2.Services;

import org.soapServiceV2.Domain.CatalogRequest;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface CatalogRequestService {

    @WebMethod
    public boolean createRequest(String title, String description, String poster, String trailer, String category);

    @WebMethod
    public List<CatalogRequest> getRequests();

    @WebMethod
    public CatalogRequest getRequest(int requestId);

    @WebMethod
    public boolean acceptRequest(int requestId);

    @WebMethod
    public boolean rejectRequest(int requestId);

    @WebMethod
    public boolean deleteRequest(int requestId);
}
