package org.soapService.Services;

import org.soapService.Domain.ReportUser;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "org.soapService.Services.ReportUserService")
public class ReportUserServiceImpl extends BaseService implements ReportUserService {

    public void createReport(String content, int reportedId) {
    }


    public List<ReportUser> getReportedUsers() {
        return null;
    }


    public boolean blockUser(int userId) {
        return false;
    }


    public boolean deleteReport(int reportId) {
        return false;
    }
}
