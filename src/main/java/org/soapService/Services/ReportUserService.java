package org.soapService.Services;

import org.soapService.Domain.ReportUser;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import java.util.List;

@WebService
@HandlerChain(file = "handler-chain.xml")
public interface ReportUserService {
    @WebMethod(operationName = "CreateReport")
    @RequestWrapper(className = "ReportUserService.CreateReport")
    public void createReport(@WebParam(name = "content") String content,
                             @WebParam(name = "reportedId") int reportedId);

    @WebMethod(operationName = "GetReportedUsers")
    @RequestWrapper(className = "ReportUserService.GetReportedUsers")
    public List<ReportUser> getReportedUsers();

    @WebMethod(operationName = "BlockUser")
    @RequestWrapper(className = "ReportUserService.BlockUser")
    public boolean blockUser(@WebParam(name = "userId") int userId);

    @WebMethod(operationName = "DeleteReport")
    @RequestWrapper(className = "ReportUserService.DeleteReport")
    public boolean deleteReport(@WebParam(name = "reportId") int reportId);
}
