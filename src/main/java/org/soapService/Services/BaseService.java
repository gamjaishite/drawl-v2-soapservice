package org.soapService.Services;


import com.sun.net.httpserver.HttpExchange;
import com.sun.xml.internal.ws.developer.JAXWSProperties;
import org.soapService.Domain.Log;
import org.soapService.Repository.LogRepository;

import javax.annotation.Resource;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.sql.SQLException;

public abstract class BaseService {
    @Resource
    WebServiceContext wsContext;

    protected void addLog() {
        MessageContext messageContext = wsContext.getMessageContext();
        HttpExchange exchange = (HttpExchange) messageContext.get(JAXWSProperties.HTTP_EXCHANGE);

        Log log = new Log();
        log.setIp(exchange.getRemoteAddress().getAddress().toString().replace("/", ""));
        log.setReqDesc("desc");
        log.setEndpointDest("/");

        LogRepository logRepository = new LogRepository();


        try {
            logRepository.add(log);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void printContext() {
//        System.out.println("IP From WebService: " + (String) Handler1.threadLocal.get());
    }
}
