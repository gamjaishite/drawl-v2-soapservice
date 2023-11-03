package org.soapService.Services;

import org.soapService.Common.HTTPStatusCode;
import org.soapService.Common.ServiceResponse;
import org.soapService.Domain.ReportUser;
import org.soapService.Exceptions.RequestException;
import org.soapService.Exceptions.ValidationException;
import org.soapService.Repository.ReportUserRepository;
import org.soapService.Validations.ReportUserServiceValidation;

import javax.jws.WebService;

@WebService(endpointInterface = "org.soapService.Services.ReportUserService")
public class ReportUserServiceImpl extends BaseService implements ReportUserService {
    private static ReportUserRepository reportUserRepository = new ReportUserRepository();
    private static ReportUserServiceValidation reportUserServiceValidation = new ReportUserServiceValidation();

    public ServiceResponse createReport(String content, String reportedId, String reporterId) throws RequestException {
        try {
            reportUserServiceValidation.validateCreateReport(content, reportedId, reporterId);

            ReportUser reportUser = new ReportUser();
            reportUser.setContent(content);
            reportUser.setReportedId(reportedId);
            reportUser.setReporterId(reporterId);

            reportUserRepository.add(reportUser);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            throw new RequestException(e.getStatus(), e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RequestException(HTTPStatusCode.INTERNAL_SERVER_ERROR.getCodeStr(), "Something went wrong, please try again later");
        }

        ServiceResponse<ReportUser> response = new ServiceResponse<>();
        response.setStatus(HTTPStatusCode.OK.getCodeStr());
        response.setMessage("Report successfully created");

        return response;
    }


    public ReportUser getReportedUsers() throws RequestException {
        return null;
    }


    public boolean blockUser(int userId) throws RequestException {
        return false;
    }


    public boolean deleteReport(int reportId) throws RequestException {
        return false;
    }
}
