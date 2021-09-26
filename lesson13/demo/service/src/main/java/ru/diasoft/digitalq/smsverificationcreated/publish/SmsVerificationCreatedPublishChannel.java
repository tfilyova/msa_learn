/*
 * Created by DQCodegen
 */
package ru.diasoft.digitalq.smsverificationcreated.publish;

import lombok.Generated;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/*
spring:
  cloud:
    stream:
      bindings:
        smsVerificationCreatedPublish:
          destination: sms-verification-created
*/
@Generated
public interface SmsVerificationCreatedPublishChannel {

    @Output(SmsVerificationCreatedPublishChannelConstants.SMS_VERIFICATION_CREATED)
    MessageChannel smsVerificationCreated();
    
}