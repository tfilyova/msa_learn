package ru.diasoft.sms_adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import ru.diasoft.sms_adapter.model.SmsDeliveredMessage;
import ru.diasoft.sms_adapter.model.SmsVerificationMessage;

@Configuration
@RequiredArgsConstructor
public class SmsCreatedListener {

    private final MsgGateway msgGateway;

    @StreamListener(MsgGateway.SMS_CREATED)
    void smsVerificationDelivered(Message<SmsVerificationMessage> msg) {
        System.out.println(msg.getPayload());
        msgGateway.smsVerificationDelivered(SmsDeliveredMessage
                .builder()
                .guid(msg.getPayload()
                .getGuid()).build());
    }


}
