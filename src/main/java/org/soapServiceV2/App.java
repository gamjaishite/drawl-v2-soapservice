package org.soapServiceV2;

import org.soapServiceV2.Services.AccountVerificationRequestServiceImpl;
import org.soapServiceV2.Services.CatalogRequestServiceImpl;
import org.soapServiceV2.Services.ReportUserServiceImpl;

import javax.xml.ws.Endpoint;

public class App {
    public static void main(String[] args) {
        String baseUrl = "http://0.0.0.0:8082/";

        // Register services;
        Endpoint.publish(baseUrl + "catalog-request", new CatalogRequestServiceImpl());
        Endpoint.publish(baseUrl + "report-user", new ReportUserServiceImpl());
        Endpoint.publish(baseUrl + "account-verification-request", new AccountVerificationRequestServiceImpl());
    }
}