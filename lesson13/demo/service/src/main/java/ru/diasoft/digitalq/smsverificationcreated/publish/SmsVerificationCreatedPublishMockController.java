/*
 * Created by DQCodegen
 */
package ru.diasoft.digitalq.smsverificationcreated.publish;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.diasoft.digitalq.model.SmsVerificationMessage;
import ru.diasoft.digitalq.model.SmsVerificationMessageMock;
@RestController("mock-ctlr-smsVerificationCreated")
@Api(tags = {"mock-ctlr-demo"})
@Generated
public class SmsVerificationCreatedPublishMockController  {

    @Autowired
    private SmsVerificationCreatedPublishGateway gateway;

    @GetMapping (value = "/mock/smsVerificationCreated")
    @ApiOperation(value = "smsVerificationCreated", tags = {"mock-ctlr-demo"})
    public ResponseEntity<SmsVerificationMessage> smsVerificationCreated() {
         SmsVerificationMessage mock = new SmsVerificationMessageMock();
         gateway.smsVerificationCreated(MessageBuilder.withPayload(mock).build());
         return ResponseEntity.status(HttpStatus.OK).body(mock);
     }

}

