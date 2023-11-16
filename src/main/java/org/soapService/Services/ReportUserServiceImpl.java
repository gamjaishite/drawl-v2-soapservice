package org.soapService.Services;

import org.soapService.Common.HTTPStatusCode;
import org.soapService.Common.ServiceResponse;
import org.soapService.Domain.GetAllResponse;
import org.soapService.Domain.ReportUser;
import org.soapService.Exceptions.RequestException;
import org.soapService.Exceptions.ValidationException;
import org.soapService.Repository.ReportUserRepository;
import org.soapService.Validations.ReportUserServiceValidation;

import javax.jws.WebService;
import javax.xml.ws.soap.SOAPFaultException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface = "org.soapService.Services.ReportUserService")
public class ReportUserServiceImpl implements ReportUserService {
    private static ReportUserRepository reportUserRepository = new ReportUserRepository();
    private static ReportUserServiceValidation reportUserServiceValidation = new ReportUserServiceValidation();

    public ServiceResponse createReportUser(String content, String reportedId, String reporterId) throws SOAPFaultException {
        try {
            reportUserServiceValidation.validateCreateReport(content, reportedId, reporterId);

            ReportUser reportUser = new ReportUser();
            reportUser.setContent(content);
            reportUser.setReportedId(reportedId);
            reportUser.setReporterId(reporterId);

            reportUserRepository.add(reportUser);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            new RequestException(HTTPStatusCode.BAD_REQUEST.getCodeStr(), e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            new RequestException(HTTPStatusCode.INTERNAL_SERVER_ERROR.getCodeStr(), "Something went wrong, please try again later");
        }

        ServiceResponse<ReportUser> response = new ServiceResponse<>();
        response.setStatus(HTTPStatusCode.OK.getCodeStr());
        response.setMessage("Report successfully created");

        return response;
    }


    public ServiceResponse<GetAllResponse<ReportUser>> getReportedUsers(Integer page, Integer pageSize) throws SOAPFaultException {
        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = 20;
        }

        List<GetAllResponse<ReportUser>> lru = new ArrayList<>();
        try {
            lru.add(reportUserRepository.getAll(page, pageSize));
        } catch (Exception e) {
            e.printStackTrace();
            new RequestException(HTTPStatusCode.INTERNAL_SERVER_ERROR.getCodeStr(), "Something went wrong, please try again later");
        }

        ServiceResponse<GetAllResponse<ReportUser>> response = new ServiceResponse<>();
        response.setStatus(HTTPStatusCode.OK.getCodeStr());
        response.setMessage("Success");
        response.setData(lru);

        return response;
    }


    public ServiceResponse blockUser(String reportedId) throws SOAPFaultException {
        try {
            reportUserServiceValidation.validateBlockUser(reportedId);

            // delete all report to the user
            int res = reportUserRepository.deleteByReportedId(reportedId);

            if (res == 0) {
                new RequestException(HTTPStatusCode.BAD_REQUEST.getCodeStr(), "The report with following User ID doesn't exist");
            }
        } catch (ValidationException e) {
            e.printStackTrace();
            new RequestException(HTTPStatusCode.BAD_REQUEST.getCodeStr(), e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            new RequestException(HTTPStatusCode.INTERNAL_SERVER_ERROR.getCodeStr(), "Something weneg wrong, please try again later");
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


        ServiceResponse response = new ServiceResponse();
        response.setStatus(HTTPStatusCode.OK.getCodeStr());
        response.setMessage("Success");

        return response;
    }


    public ServiceResponse deleteReportUser(String reportId) throws SOAPFaultException {
        try {
            reportUserServiceValidation.validateDeleteReportUser(reportId);

            int res = reportUserRepository.deleteByUuid(reportId);
            if (res == 0) {
                new RequestException(HTTPStatusCode.BAD_REQUEST.getCodeStr(), "This report doesn't exist");
            }
        } catch (ValidationException e) {
            e.printStackTrace();
            new RequestException(HTTPStatusCode.BAD_REQUEST.getCodeStr(), e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            new RequestException(HTTPStatusCode.INTERNAL_SERVER_ERROR.getCodeStr(), "Something went wrong, please try again later");
        } catch (SOAPFaultException e) {
            e.printStackTrace();
            if (e.getFault().getFaultCode().equals(HTTPStatusCode.BAD_REQUEST.getCodeStr())) {
                throw e;
            }
            new RequestException(HTTPStatusCode.INTERNAL_SERVER_ERROR.getCodeStr(),
                    "Something went wrong, please try again later");
        } catch (Exception e) {
            e.printStackTrace();
            new RequestException(HTTPStatusCode.INTERNAL_SERVER_ERROR.getCodeStr(), "Something went wrong, please try again later");
        }

        ServiceResponse response = new ServiceResponse();
        response.setStatus(HTTPStatusCode.OK.getCodeStr());
        response.setMessage("Success");


        return response;
    }
}
