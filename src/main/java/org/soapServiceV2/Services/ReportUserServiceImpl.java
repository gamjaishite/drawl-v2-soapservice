package org.soapServiceV2.Services;

import org.soapServiceV2.Domain.ReportUser;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "org.soapServiceV2.Services.ReportUserService")
public class ReportUserServiceImpl extends BaseService implements ReportUserService {
    @WebMethod
    public void createReport(String content, int reportedId) {

    }

    @WebMethod
    public List<ReportUser> getReportedUsers() {
        return null;
    }

    @WebMethod
    public boolean blockUser(int userId) {
        return false;
    }

    @WebMethod
    public boolean deleteReport(int reportId) {
        return false;
    }
}
