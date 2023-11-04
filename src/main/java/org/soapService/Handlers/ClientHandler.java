package org.soapService.Handlers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import io.github.cdimascio.dotenv.Dotenv;
import org.soapService.Common.HTTPStatusCode;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;
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

        try {
            if (!outboundProperty) {
                Map<String, List<String>> headers = (Map<String, List<String>>) context.get(MessageContext.HTTP_REQUEST_HEADERS);

                SOAPFactory soapFactory = SOAPFactory.newInstance();
                SOAPFault soapFault = soapFactory.createFault();
                soapFault.setFaultCode(HTTPStatusCode.UNAUTHORIZED.getCodeStr());
                soapFault.setFaultString("Unauthorized");


                if (headers.get("token") == null) {
                    throw new SOAPFaultException(soapFault);
                }

                Dotenv dotenv = Dotenv.load();

                BCrypt.Result resultREST = BCrypt.verifyer().verify(headers.get("token").get(0).toCharArray(), dotenv.get("REST_API_KEY"));
                System.out.println("REST Client: " + resultREST.verified);

                BCrypt.Result resultPHP = BCrypt.verifyer().verify(headers.get("token").get(0).toCharArray(), dotenv.get("PHP_API_KEY"));
                System.out.println("PHP Client: " + resultPHP.verified);

                if (!resultREST.verified && !resultPHP.verified) {
                    throw new SOAPFaultException(soapFault);
                }

                context.put("client", resultREST.verified ? "REST" : "PHP");
            }
        } catch (SOAPFaultException soapFaultException) {
            throw soapFaultException;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean handleFault(SOAPMessageContext context) {
        return false;
    }

    public void close(MessageContext context) {

    }
}
