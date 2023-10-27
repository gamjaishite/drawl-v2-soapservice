package org.soapService.Handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.xml.internal.ws.developer.JAXWSProperties;
import org.soapService.Domain.Log;
import org.soapService.Repository.LogRepository;
import org.w3c.dom.NodeList;

import javax.xml.namespace.QName;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPBody;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class LogHandler implements SOAPHandler<SOAPMessageContext> {
    //    public static ThreadLocal<Log> threadLocal = new ThreadLocal<>();
    private static LogRepository logRepository = new LogRepository();

    public Set<QName> getHeaders() {
        return null;
    }

    public boolean handleMessage(SOAPMessageContext context) {
        Boolean outboundProperty = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        HttpExchange httpExchange = (HttpExchange) context.get(JAXWSProperties.HTTP_EXCHANGE);

        if (!outboundProperty) {
            String ip = httpExchange.getRemoteAddress().getAddress().toString().replace("/", "");
            String endpoint = httpExchange.getRequestURI().toString();
            String operation = "";
            Map<String, String> args = new HashMap<>();

            try {
                SOAPBody soapBody = context.getMessage().getSOAPBody();

                // Get operation
                Iterator bodyElements = soapBody.getChildElements();
                while (bodyElements.hasNext()) {
                    Object bodyElement = bodyElements.next();
                    if (bodyElement instanceof Node) {
                        Node node = (Node) bodyElement;
                        if (node.getLocalName() != null) {
                            operation = node.getLocalName();
                            NodeList nl = node.getChildNodes();
                            for (int i = 0; i < nl.getLength(); i++) {
                                if (nl.item(i).getLocalName() != null) {
                                    args.put(nl.item(i).getLocalName(), nl.item(i).getTextContent().substring(0, Math.min(nl.item(i).getTextContent().length(), 200)));
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Format
            // [ClientName] -> [arg0]: <arg0_value>; [arg1]: <arg1_value>
            String requestDescription = "[" + context.get("client") + "] ->" + (!args.isEmpty() ? " " : " No Args");
            for (Map.Entry<String, String> entry : args.entrySet()) {
                requestDescription += "[" + entry.getKey() + "]:";
                requestDescription += entry.getValue() + "; ";
            }

            // Write log
            Log log = new Log();
            log.setIp(ip);
            log.setEndpointDest(endpoint + "." + operation);
            log.setReqDesc(requestDescription);

            try {
                logRepository.add(log);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            System.out.println("\nINFORMATION: ");
            System.out.println("IP: " + ip);
            System.out.println("Endpoint: " + endpoint + "." + operation);
            System.out.println("Request Description: " + requestDescription);
        }

        return true;
    }

    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    public void close(MessageContext context) {

    }
}
