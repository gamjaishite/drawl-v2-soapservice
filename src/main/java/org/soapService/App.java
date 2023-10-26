package org.soapService;

import org.soapService.Services.AccountVerificationRequestServiceImpl;
import org.soapService.Services.CatalogRequestServiceImpl;
import org.soapService.Services.ReportUserServiceImpl;

import javax.xml.ws.Endpoint;

public class App {
    public static void main(String[] args) {
        String baseUrl = "http://0.0.0.0:8083/";

        // Register services;
        Endpoint.publish(baseUrl + "catalog-request", new CatalogRequestServiceImpl());
        Endpoint.publish(baseUrl + "report-user", new ReportUserServiceImpl());
        Endpoint.publish(baseUrl + "account-verification-request", new AccountVerificationRequestServiceImpl());
    }
}