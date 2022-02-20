package com.biofarma.ehr.services;

import com.biofarma.ehr.payloads.EmailPayload;
import com.biofarma.ehr.services.mail.EmailService;
import com.biofarma.ehr.utilities.constants.EmailSubjectConstant;
import com.biofarma.ehr.utilities.constants.EmailTemplatePathConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserManagementServiceImpl implements UserManagementService{

    @Autowired
    EmailService emailService;

    @Override
    public void sendEmailForgotPassword(String to) {
        try {
            String emailBody = emailService.generateHtmlEmailBody(EmailTemplatePathConstant.SAMPLE_EMAIL_PATH, new HashMap<>());
            EmailPayload emailPayload = new EmailPayload();
            emailPayload.setSubject(EmailSubjectConstant.FORGOT_PASSWORD_SUBJECT);
            emailPayload.setReceiver(to);
            emailPayload.setEmailBody(emailBody);
            emailService.sendEmail(emailPayload);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
