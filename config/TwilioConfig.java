package com.app.config;

import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioConfig {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;
    @Value("${twilio.whatsapp.number}")
    private String twilioWhatsappNumber;

//    @Value("${sendgrid.api.key}")
//    private String sendGridApiKey;
//    @Value("${sendgrid.from.email}")
//    private String twiliorFromEmail;

    @PostConstruct
    public void init() {
        Twilio.init(accountSid, authToken);
    }

//    @Bean
//    public SendGrid getSendGridClient() {
//        return new SendGrid(sendGridApiKey);
//    }

    public String getTwilioPhoneNumber() {
        return twilioPhoneNumber;
    }

    public String getTwilioWhatsappNumber() {
        return twilioWhatsappNumber;
    }

//    public String getTwiliorFromEmail() {
//        return twiliorFromEmail;
//    }
}
