package org.soapService.Services;

import org.soapService.Common.ServiceResponse;
import org.soapService.Domain.ReportUser;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.soap.SOAPFaultException;

@WebService
@XmlSeeAlso({ServiceResponse.class})
@HandlerChain(file = "handler-chain.xml")
public interface ReportUserService {
    @WebMethod(operationName = "CreateReport")
    @RequestWrapper(className = "ReportUserService.CreateReport")
    public ServiceResponse<ReportUser> createReport(@WebParam(name = "content") String content,
                                                    @WebParam(name = "reportedId") String reportedId,
                                                    @WebParam(name = "reporterId") String reporterId) throws SOAPFaultException;

    @WebMethod(operationName = "GetReportedUsers")
    @RequestWrapper(className = "ReportUserService.GetReportedUsers")
    public ReportUser getReportedUsers() throws SOAPFaultException;

    @WebMethod(operationName = "BlockUser")
    @RequestWrapper(className = "ReportUserService.BlockUser")
    public boolean blockUser(@WebParam(name = "userId") int userId) throws SOAPFaultException;

    @WebMethod(operationName = "DeleteReport")
    @RequestWrapper(className = "ReportUserService.DeleteReport")
    public boolean deleteReport(@WebParam(name = "reportId") int reportId) throws SOAPFaultException;
}
