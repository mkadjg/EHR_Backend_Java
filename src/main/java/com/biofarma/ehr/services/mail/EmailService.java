package com.biofarma.ehr.services.mail;

import com.biofarma.ehr.payloads.EmailPayload;

import java.util.Map;

public interface EmailService {
    void sendEmail(EmailPayload emailPayload);
    String generateHtmlEmailBody(String htmlFilenamePath, Map<String, Object> variables);
}
