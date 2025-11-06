package com.app.service;

import com.app.config.TwilioConfig;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.content.v1.Content;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsService {
    private  TwilioConfig twilioConfig;

    @Autowired
    public SmsService(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }
    public String sendSms(String to, String body) {
        Message message = Message.creator(
                new PhoneNumber(to),
                new PhoneNumber(twilioConfig.getTwilioPhoneNumber()),
                body
        ).create();

        return message.getSid(); // Returns the SMS SID for tracking
    }
}
