package org.soapService.Services;

import org.soapService.Common.HTTPStatusCode;
import org.soapService.Common.ServiceResponse;
import org.soapService.Domain.CatalogRequest;
import org.soapService.Domain.GetAllResponse;
import org.soapService.Exceptions.RequestException;
import org.soapService.Exceptions.ValidationException;
import org.soapService.Repository.CatalogReqeustRepository;
import org.soapService.Utils.FileType;
import org.soapService.Utils.FileUploader;
import org.soapService.Validations.CatalogValidation;

import javax.activation.DataHandler;
import javax.jws.WebService;
import javax.xml.ws.soap.SOAPFaultException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface = "org.soapService.Services.CatalogRequestService")
public class CatalogRequestServiceImpl extends BaseService implements CatalogRequestService {

    private static CatalogReqeustRepository catalogRepository = new CatalogReqeustRepository();
    private static CatalogValidation catalogValidation = new CatalogValidation();

    public ServiceResponse<GetAllResponse<CatalogRequest>> getCatalogRequests(Integer page, Integer pageSize) throws SOAPFaultException {
        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = 20;
        }

        List<GetAllResponse<CatalogRequest>> lcr = new ArrayList<>();
        try {
            lcr.add(catalogRepository.getAll(page, pageSize));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            new RequestException(HTTPStatusCode.INTERNAL_SERVER_ERROR.getCodeStr(),
                    "Something went wrong, please try again later");
        }

        ServiceResponse<GetAllResponse<CatalogRequest>> response = new ServiceResponse<>();
        response.setStatus(HTTPStatusCode.OK.getCodeStr());
        response.setMessage("Successfully get all catalog requests");
        response.setData(lcr);

        lcr.forEach((item) -> {
            System.out.print(item.getPage());
            item.getData().forEach((data) -> {
                System.out.println(data.getId());
            });
        });

        return response;
    }

    public ServiceResponse<CatalogRequest> getCatalogRequest(int requestId) throws SOAPFaultException {
        // For testing purpose

        CatalogRequest cr = new CatalogRequest();
        cr.setId(requestId);
        return null;
    }


    public ServiceResponse<CatalogRequest> createCatalogRequest(String uuid, String title, String description, String poster, String trailer, String category)
            throws SOAPFaultException {
        List<CatalogRequest> lcr = new ArrayList<>();
        try {
            catalogValidation.validateCreateCatalogRequest(uuid, title, description, poster, trailer, category);

            CatalogRequest catalogRequest = new CatalogRequest();
            catalogRequest.setUuid(uuid);
            catalogRequest.setTitle(title);
            catalogRequest.setDescription(description);
            catalogRequest.setPoster(poster);
            catalogRequest.setTrailer(trailer);
            catalogRequest.setCategory(category);

            lcr.add(catalogRequest);

            catalogRepository.add(catalogRequest);


        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            new RequestException(HTTPStatusCode.BAD_REQUEST.getCodeStr(), e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            System.out.println(e.getMessage());
            if (e.getSQLState().equals("23000")) {
                new RequestException(HTTPStatusCode.BAD_REQUEST.getCodeStr(),
                        "This user already have a request");
            } else {
                new RequestException(HTTPStatusCode.INTERNAL_SERVER_ERROR.getCodeStr(),
                        "Something went wrong, please try again later");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            new RequestException(HTTPStatusCode.INTERNAL_SERVER_ERROR.getCodeStr(),
                    "Something went wrong, please try again later");
        }

        ServiceResponse<CatalogRequest> response = new ServiceResponse<>();
        response.setStatus(HTTPStatusCode.OK.getCodeStr());
        response.setMessage("Report successfully created");
        response.setData(lcr);

        return response;

    }


    public ServiceResponse acceptCatalogRequest(String uuid) throws SOAPFaultException {
        List<CatalogRequest> lcr = new ArrayList<>();
        try {
            catalogValidation.validateAcceptCatalogRequest(uuid);
            CatalogRequest catalogRequest = new CatalogRequest();
            catalogRequest.setUuid(uuid);

//            int res = catalogRepository.update(catalogRequest);
//            if (res == 0) {
//                new RequestException(HTTPStatusCode.BAD_REQUEST.getCodeStr(),
//                        "This catalog doesn't have a request");
//            }

        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            new RequestException(HTTPStatusCode.BAD_REQUEST.getCodeStr(), e.getMessage());

//        } catch (SQLException e) {
//            System.out.println(e.getSQLState());
//            System.out.println(e.getMessage());
//            if (e.getSQLState().equals("23000")) {
//                new RequestException(HTTPStatusCode.BAD_REQUEST.getCodeStr(),
//                        "This catalog already have a request");
//            } else {
//                new RequestException(HTTPStatusCode.INTERNAL_SERVER_ERROR.getCodeStr(),
//                        "Something went wrong, please try again later");
//            }
        } catch (SOAPFaultException e) {
            if (e.getFault().getFaultCode().equals(HTTPStatusCode.BAD_REQUEST.getCodeStr())) {
                throw e;
            }
            new RequestException(HTTPStatusCode.INTERNAL_SERVER_ERROR.getCodeStr(),
                    "Something went wrong, please try again later");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            new RequestException(HTTPStatusCode.INTERNAL_SERVER_ERROR.getCodeStr(),
                    "Something went wrong, please try again later");
        }
        ServiceResponse<CatalogRequest> response = new ServiceResponse<>();
        response.setStatus(HTTPStatusCode.OK.getCodeStr());
        response.setMessage("Catalog request with UUID " + uuid + " successfully accepted");
        response.setData(lcr);

        return response;
    }

    public ServiceResponse rejectCatalogRequest(String uuid) throws SOAPFaultException {
        List<CatalogRequest> lcr = new ArrayList<>();
        try {
            catalogValidation.validateRejectCatalogRequest(uuid);
            CatalogRequest catalogRequest = new CatalogRequest();
            catalogRequest.setUuid(uuid);

//            int res = catalogRepository.update(catalogRequest);
//            if (res == 0) {
//                new RequestException(HTTPStatusCode.BAD_REQUEST.getCodeStr(),
//                        "This catalog doesn't have a request");
//            }

        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            new RequestException(HTTPStatusCode.BAD_REQUEST.getCodeStr(), e.getMessage());

//        } catch (SQLException e) {
//            System.out.println(e.getSQLState());
//            System.out.println(e.getMessage());
//            if (e.getSQLState().equals("23000")) {
//                new RequestException(HTTPStatusCode.BAD_REQUEST.getCodeStr(),
//                        "This catalog already have a request");
//            } else {
//                new RequestException(HTTPStatusCode.INTERNAL_SERVER_ERROR.getCodeStr(),
//                        "Something went wrong, please try again later");
//            }
        } catch (SOAPFaultException e) {
            if (e.getFault().getFaultCode().equals(HTTPStatusCode.BAD_REQUEST.getCodeStr())) {
                throw e;
            }
            new RequestException(HTTPStatusCode.INTERNAL_SERVER_ERROR.getCodeStr(),
                    "Something went wrong, please try again later");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            new RequestException(HTTPStatusCode.INTERNAL_SERVER_ERROR.getCodeStr(),
                    "Something went wrong, please try again later");
        }
        ServiceResponse<CatalogRequest> response = new ServiceResponse<>();
        response.setStatus(HTTPStatusCode.OK.getCodeStr());
        response.setMessage("Catalog request with UUID " + uuid + " successfully rejected");
        response.setData(lcr);

        return response;
    }

    public ServiceResponse deleteCatalogRequest(int requestId) throws SOAPFaultException {
        List<CatalogRequest> lcr = new ArrayList<>();
        try {
            catalogValidation.validateDeleteCatalogRequest(requestId);

            int res = catalogRepository.delete(requestId);

            if (res == 0) {
                new RequestException(HTTPStatusCode.BAD_REQUEST.getCodeStr(),
                        "This request doesn't exist");
            }
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            new RequestException(HTTPStatusCode.BAD_REQUEST.getCodeStr(), e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            System.out.println(e.getMessage());
            if (e.getSQLState().equals("23000")) {
                new RequestException(HTTPStatusCode.BAD_REQUEST.getCodeStr(),
                        "This catalog already has a request");
            } else {
                new RequestException(HTTPStatusCode.INTERNAL_SERVER_ERROR.getCodeStr(),
                        "Something went wrong, please try again later");
            }
        } catch (SOAPFaultException e) {
            if (e.getFault().getFaultCode().equals(HTTPStatusCode.BAD_REQUEST.getCodeStr())) {
                throw e;
            }
            new RequestException(HTTPStatusCode.INTERNAL_SERVER_ERROR.getCodeStr(),
                    "Something went wrong, please try again later");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            new RequestException(HTTPStatusCode.INTERNAL_SERVER_ERROR.getCodeStr(),
                    "Something went wrong, please try again later");
        }

        ServiceResponse<CatalogRequest> response = new ServiceResponse<>();
        response.setStatus(HTTPStatusCode.OK.getCodeStr());
        response.setMessage("Catalog request with ID " + requestId + " successfully deleted");
        response.setData(lcr);

        return response;
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
