/*
 * Created by DQCodegen
 */
package ru.diasoft.digitalq.smsverificationcreated.publish;

import lombok.Generated;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;
import ru.diasoft.digitalq.model.SmsVerificationMessage;
@MessagingGateway
@Generated
public interface SmsVerificationCreatedPublishGateway {

    @Gateway(requestChannel = SmsVerificationCreatedPublishChannelConstants.SMS_VERIFICATION_CREATED)
    void smsVerificationCreated(Message<SmsVerificationMessage> msg);

}

