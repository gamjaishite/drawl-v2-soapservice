package org.soapService.Services;

import org.soapService.Common.ServiceResponse;
import org.soapService.Domain.ReportUser;
import org.soapService.Exceptions.RequestException;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;

@WebService
@XmlSeeAlso({ServiceResponse.class})
@HandlerChain(file = "handler-chain.xml")
public interface ReportUserService {
    @WebMethod(operationName = "CreateReport")
    @RequestWrapper(className = "ReportUserService.CreateReport")
    public ServiceResponse createReport(@WebParam(name = "content") String content,
                                        @WebParam(name = "reportedId") String reportedId,
                                        @WebParam(name = "reporterId") String reporterId) throws RequestException;

    @WebMethod(operationName = "GetReportedUsers")
    @RequestWrapper(className = "ReportUserService.GetReportedUsers")
    public ReportUser getReportedUsers() throws RequestException;

    @WebMethod(operationName = "BlockUser")
    @RequestWrapper(className = "ReportUserService.BlockUser")
    public boolean blockUser(@WebParam(name = "userId") int userId) throws RequestException;

    @WebMethod(operationName = "DeleteReport")
    @RequestWrapper(className = "ReportUserService.DeleteReport")
    public boolean deleteReport(@WebParam(name = "reportId") int reportId) throws RequestException;
}
