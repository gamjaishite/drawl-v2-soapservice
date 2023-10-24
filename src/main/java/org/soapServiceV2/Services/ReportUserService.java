package org.soapServiceV2.Services;

import org.soapServiceV2.Domain.ReportUser;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface ReportUserService {
    @WebMethod
    public void createReport(String content, int reportedId);

    @WebMethod
    public List<ReportUser> getReportedUsers();

    @WebMethod
    public boolean blockUser(int userId);

    @WebMethod
    public boolean deleteReport(int reportId);
}
