package com.foodcourt.squaremallmanagment.infrastructure.out.notification;

import com.foodcourt.squaremallmanagment.domain.spi.ISendNotificationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
@RequiredArgsConstructor
public class SendNotificationService implements ISendNotificationPort {

    @Value("${twilio.accountSid}")
    private String accountSid;

    @Value("${twilio.authToken}")
    private String authToken;

    @Value("${twilio.messagingServiceSid}")
    private String messagingServiceSid;

    public void sendMessage(String toPhoneNumber, String messageBody) {

        Twilio.init(accountSid, authToken);

        Message.creator(
                new PhoneNumber(toPhoneNumber),
                messagingServiceSid,
                messageBody
        ).create();
    }
}
