package org.soapService.Services;

import org.soapService.Common.HTTPStatusCode;
import org.soapService.Common.ServiceResponse;
import org.soapService.Domain.CatalogRequest;
import org.soapService.Exceptions.RequestException;
import org.soapService.Utils.FileType;
import org.soapService.Utils.FileUploader;

import javax.activation.DataHandler;
import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "org.soapService.Services.CatalogRequestService")
public class CatalogRequestServiceImpl extends BaseService implements CatalogRequestService {
    public boolean createRequest(String title, String description, String poster, String trailer, String category) throws RequestException {
        return false;
    }

    public List<CatalogRequest> getRequests() throws RequestException {
        return null;
    }

    public CatalogRequest getRequest(int requestId) throws RequestException {
        // For testing purpose

        CatalogRequest cr = new CatalogRequest();
        cr.setId(requestId);
        return cr;
    }

    public boolean acceptRequest(int requestId) throws RequestException {
        return false;
    }

    public boolean rejectRequest(int requestId) throws RequestException {
        return false;
    }

    public boolean deleteRequest(int requestId) throws RequestException {
        System.out.println(requestId);
        return false;
    }

    public ServiceResponse testUpload(DataHandler dataHandler) throws RequestException {
        try {
            boolean valid = FileUploader.validateImage(dataHandler);
            if (valid) {
                FileUploader.upload(dataHandler, FileType.IMAGE);
            } else {
                throw new RequestException(HTTPStatusCode.BAD_REQUEST.getCodeStr(), "Invalid data");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RequestException(HTTPStatusCode.INTERNAL_SERVER_ERROR.getCodeStr(), e.getMessage());
        }

        ServiceResponse response = new ServiceResponse();
        response.setStatus(HTTPStatusCode.OK.getCodeStr());
        response.setMessage("Success");

        return response;
    }
}
