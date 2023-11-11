package org.soapService.Services;

import com.google.gson.Gson;
import org.soapService.Common.HTTPStatusCode;
import org.soapService.Common.ServiceResponse;
import org.soapService.Domain.CatalogRequest;
import org.soapService.Exceptions.RequestException;
import org.soapService.Models.CatalogRequest.AcceptRequest;
import org.soapService.Utils.FileType;
import org.soapService.Utils.FileUploader;
import org.soapService.Utils.HTTPRequest;
import org.soapService.Utils.HTTPRequestMethod;

import javax.activation.DataHandler;
import javax.jws.WebService;
import javax.xml.ws.soap.SOAPFaultException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;

@WebService(endpointInterface = "org.soapService.Services.CatalogRequestService")
public class CatalogRequestServiceImpl extends BaseService implements CatalogRequestService {
    public ServiceResponse createCatalogRequest(String title, String description, String poster, String trailer, String category) throws SOAPFaultException {
        return null;
    }

    public ServiceResponse<CatalogRequest> getCatalogRequests() throws SOAPFaultException {
        return null;
    }

    public ServiceResponse<CatalogRequest> getCatalogRequest(int requestId) throws SOAPFaultException {
        // For testing purpose

        CatalogRequest cr = new CatalogRequest();
        cr.setId(requestId);
        return null;
    }

    public ServiceResponse acceptCatalogRequest(int requestId) throws SOAPFaultException {
        // Example of sending http request to php
        try {
            HttpURLConnection conn = HTTPRequest.getConnection("/catalog", HTTPRequestMethod.POST);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);

            Gson gson = new Gson();
            String requestBody = gson.toJson(new AcceptRequest("title", "description"));

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = requestBody.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();

                System.out.println(response.toString());
            } else {
                System.out.println(responseCode);
            }
        } catch (Exception e) {
            new RequestException(HTTPStatusCode.INTERNAL_SERVER_ERROR.getCodeStr(), e.getMessage());
        }
        return null;
    }

    public ServiceResponse rejectCatalogRequest(int requestId) throws SOAPFaultException {
        return null;
    }

    public ServiceResponse deleteCatalogRequest(int requestId) throws SOAPFaultException {
        System.out.println(requestId);
        return null;
    }

    public ServiceResponse testUpload(DataHandler dataHandler) throws SOAPFaultException {
        try {
            boolean valid = FileUploader.validateImage(dataHandler);
            if (valid) {
                FileUploader.upload(dataHandler, FileType.IMAGE);
            } else {
                new RequestException(HTTPStatusCode.BAD_REQUEST.getCodeStr(), "Invalid data");
            }
        } catch (Exception e) {
            e.printStackTrace();
            new RequestException(HTTPStatusCode.INTERNAL_SERVER_ERROR.getCodeStr(), e.getMessage());
        }

        ServiceResponse response = new ServiceResponse();
        response.setStatus(HTTPStatusCode.OK.getCodeStr());
        response.setMessage("Success");

        return response;
    }
}
