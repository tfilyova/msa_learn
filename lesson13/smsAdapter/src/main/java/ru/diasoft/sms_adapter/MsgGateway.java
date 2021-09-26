package ru.diasoft.sms_adapter;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import ru.diasoft.sms_adapter.model.SmsDeliveredMessage;

@MessagingGateway
public interface MsgGateway {
    public static final String SMS_CREATED = "smsVerificationCreatedSubscribe";
    public static final String SMS_VERIFIED = "smsVerificationDeliveredpublish";

    @Input(SMS_CREATED)
    SubscribableChannel smsVerificationCreated();

    @Output(SMS_VERIFIED)
    MessageChannel smsVerificationDelivered();

    @Gateway(requestChannel = MsgGateway.SMS_VERIFIED)
    void smsVerificationDelivered(SmsDeliveredMessage msg);

}
