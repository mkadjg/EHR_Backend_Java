package com.biofarma.ehr.services.mail;

import com.biofarma.ehr.payloads.EmailPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private String port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String auth;

    @Value("${spring.mail.properties.mail.smtp.connection-timeout}")
    private String connectionTimeout;

    @Value("${spring.mail.properties.mail.smtp.timeout}")
    private String timeout;

    @Value("${spring.mail.properties.mail.smtp.write-timeout}")
    private String writeTimeout;

    @Autowired
    private SpringTemplateEngine thymeleafTemplateEngine;

    @Override
    public void sendEmail(EmailPayload emailPayload) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", auth);
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", port);
            props.put("mail.smtp.connectiontimeout", connectionTimeout);
            props.put("mail.smtp.timeout", timeout);
            props.put("mail.smtp.writetimeout", writeTimeout);
            props.put("mail.smtp.starttls.enable", true);

            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(username, false));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailPayload.getReceiver()));
            msg.setSubject(emailPayload.getSubject());
            msg.setContent(emailPayload.getEmailBody(), "text/html");
            msg.setSentDate(new Date());
            Transport.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String generateHtmlEmailBody(String htmlFilenamePath, Map<String, Object> variables) {
        Context thymeleafContext = new Context();
        thymeleafContext.setVariables(variables);
        return thymeleafTemplateEngine.process(htmlFilenamePath, thymeleafContext);
    }


}
