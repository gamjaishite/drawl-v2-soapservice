package org.soapService.Services;

import org.soapService.Domain.CatalogRequest;
import org.soapService.Utils.FileType;
import org.soapService.Utils.FileUploader;

import javax.activation.DataHandler;
import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "org.soapService.Services.CatalogRequestService")
public class CatalogRequestServiceImpl extends BaseService implements CatalogRequestService {
    public boolean createRequest(String title, String description, String poster, String trailer, String category) {
        return false;
    }

    public List<CatalogRequest> getRequests() {
        return null;
    }

    public CatalogRequest getRequest(int requestId) {
        // For testing purpose

        CatalogRequest cr = new CatalogRequest();
        cr.setId(requestId);
        return cr;
    }

    public boolean acceptRequest(int requestId) {
        return false;
    }

    public boolean rejectRequest(int requestId) {
        return false;
    }

    public boolean deleteRequest(int requestId) {
        System.out.println(requestId);
        return false;
    }

    public String testUpload(DataHandler dataHandler) {
        try {
            boolean valid = FileUploader.validateImage(dataHandler);
            if (valid) {
                FileUploader.upload(dataHandler, FileType.IMAGE);
            } else {
                return "Failed";
            }
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed";
        }
    }
}
