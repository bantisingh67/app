package com.app.service;

import com.app.config.TwilioConfig;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WhatsAppService {

    private final TwilioConfig twilioConfig;

    @Autowired
    public WhatsAppService(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }

    public String sendWhatsAppMessage(String to, String body) {
        try {
            Message message = Message.creator(
                    new PhoneNumber("whatsapp:" + to),  // Recipient WhatsApp number
                    new PhoneNumber(twilioConfig.getTwilioWhatsappNumber()),  // Twilio WhatsApp number
                    body
            ).create();

            return "WhatsApp message sent successfully! SID: " + message.getSid();
        } catch (Exception e) {
            return "Failed to send WhatsApp message: " + e.getMessage();
        }
    }
}

