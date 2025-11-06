package com.app.service;
import org.springframework.stereotype.Service;
//import com.app.config.TwilioConfig;
//import com.sendgrid.*;
//import com.sendgrid.helpers.mail.objects.Email;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import java.io.IOException;
//
@Service
public class EmailService {
//
//    private final SendGrid sendGrid;
//    private final TwilioConfig twilioConfig;
//    public EmailService(SendGrid sendGrid, TwilioConfig twilioConfig) {
//        this.sendGrid = sendGrid;
//        this.twilioConfig = twilioConfig;
//    }
//
//    public String sendEmail(String to, String subject, String body) {
//        Email from = new Email(twilioConfig.getTwiliorFromEmail());
//        Email toEmail = new Email(to);
//        Content content = new Content("text/plain", body);
//        Mail mail = new Mail(from, subject, toEmail, content);
//
//        Request request = new Request();
//        try {
//            request.setMethod(Method.POST);
//            request.setEndpoint("mail/send");
//            request.setBody(mail.build());
//
//            Response response = sendGrid.api(request);
//            return "Email sent! Status: " + response.getStatusCode();
//        } catch (IOException ex) {
//            return "Failed to send email: " + ex.getMessage();
        }
//    }
//}
