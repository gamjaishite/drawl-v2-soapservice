package org.soapService.Handlers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import io.github.cdimascio.dotenv.Dotenv;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ClientHandler implements SOAPHandler<SOAPMessageContext> {
    public Set<QName> getHeaders() {
        return null;
    }

    public boolean handleMessage(SOAPMessageContext context) {
        Boolean outboundProperty = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        Integer httpExchange = (Integer) context.get(MessageContext.HTTP_RESPONSE_CODE);

        if (!outboundProperty) {
            Map<String, List<String>> headers = (Map<String, List<String>>) context.get(MessageContext.HTTP_REQUEST_HEADERS);


            if (headers.get("token") == null) {
                return false;
            }

            Dotenv dotenv = Dotenv.load();

            BCrypt.Result resultREST = BCrypt.verifyer().verify(headers.get("token").get(0).toCharArray(), dotenv.get("REST_API_KEY"));
            System.out.println("REST Client: " + resultREST.verified);

            BCrypt.Result resultPHP = BCrypt.verifyer().verify(headers.get("token").get(0).toCharArray(), dotenv.get("PHP_API_KEY"));
            System.out.println("PHP Client: " + resultPHP.verified);

            if (!resultREST.verified && !resultPHP.verified) {
                return false;
            }

            context.put("client", resultREST.verified ? "REST" : "PHP");
        }
        return true;
    }

    public boolean handleFault(SOAPMessageContext context) {
        return false;
    }

    public void close(MessageContext context) {

    }
}
