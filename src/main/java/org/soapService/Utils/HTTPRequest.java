package org.soapService.Utils;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.HttpURLConnection;
import java.net.URL;

@Getter
@Setter
@NoArgsConstructor
public class HTTPRequest {
    public static HttpURLConnection getConnection(String endpoint, HTTPRequestMethod method) throws Exception {
        Dotenv dotenv = Dotenv.load();
        URL url = new URL(dotenv.get("PHP_SERVICE_BASE_URL") + endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Authorization", "Bearer " + dotenv.get("PHP_OUTBOUND_API_KEY"));
        System.out.println(conn.getRequestProperty("Authorization"));
        conn.setRequestMethod(method.name());
        return conn;
    }
}
