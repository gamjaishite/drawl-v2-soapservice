package org.soapServiceV2.Services;

import org.soapServiceV2.Domain.CatalogRequest;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "org.soapServiceV2.Services.CatalogRequestService")
public class CatalogRequestServiceImpl extends BaseService implements CatalogRequestService {

    @WebMethod
    public boolean createRequest(String title, String description, String poster, String trailer, String category) {
        return false;
    }

    @WebMethod
    public List<CatalogRequest> getRequests() {
        return null;
    }

    @WebMethod
    public CatalogRequest getRequest(int requestId) {
        // For testing purpose
        CatalogRequest cr = new CatalogRequest();
        cr.setId(requestId);
        return cr;
    }

    @WebMethod
    public boolean acceptRequest(int requestId) {
        return false;
    }

    @WebMethod
    public boolean rejectRequest(int requestId) {
        return false;
    }

    @WebMethod
    public boolean deleteRequest(int requestId) {
        return false;
    }
}
