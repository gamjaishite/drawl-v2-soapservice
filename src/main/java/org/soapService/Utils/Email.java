package org.soapService.Utils;

import io.github.cdimascio.dotenv.Dotenv;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public class Email {
    public static void send(Session session, String senderName, String to, String subject, String body) throws Exception {
        Dotenv dotenv = Dotenv.load();
        try {
            MimeMessage message = new MimeMessage(session);

            message.addHeader("Content-Type", "text/HTML; charset=UTF-8");
            message.addHeader("format", "flowed");
            message.addHeader("Content-Transfer-Encoding", "8bit");

            message.setFrom(new InternetAddress(dotenv.get("EMAIL_ADDRESS"), senderName));
            message.setReplyTo(InternetAddress.parse(dotenv.get("EMAIL_ADDRESS"), false));
            message.setSubject(subject, "UTF-8");
            message.setText(body, "UTF-8");
            message.setSentDate(new Date());
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));

            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
