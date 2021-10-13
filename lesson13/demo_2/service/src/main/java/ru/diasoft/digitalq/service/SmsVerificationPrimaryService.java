package ru.diasoft.digitalq.service;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import ru.diasoft.digitalq.controller.dto.SmsVerificationCheckRequest;
import ru.diasoft.digitalq.controller.dto.SmsVerificationCheckResponse;
import ru.diasoft.digitalq.controller.dto.SmsVerificationPostRequest;
import ru.diasoft.digitalq.controller.dto.SmsVerificationPostResponse;
import ru.diasoft.digitalq.domain.SmsVerification;
import ru.diasoft.digitalq.repository.SmsVerificationRepository;
import ru.diasoft.digitalq.smsverificationcreated.publish.SmsVerificationCreatedPublishGateway;
import ru.diasoft.digitalq.model.SmsVerificationMessage;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Primary
public class SmsVerificationPrimaryService implements SmsVerificationService {
    private final SmsVerificationRepository repository;
    private final SmsVerificationCreatedPublishGateway gateway;

    @Override
    public SmsVerificationCheckResponse dsSmsVerificationCheck(SmsVerificationCheckRequest smsVerificationCheckRequest) {
        Optional<SmsVerification> smsVerification = repository.findBySecretCodeAndProcessGuidAndStatus(
                smsVerificationCheckRequest.getCode(),
                smsVerificationCheckRequest.getProcessGUID(),
                ServiceConstants.STATUS_OK);

        return new SmsVerificationCheckResponse(smsVerification.isPresent());
    }

    @Override
    public SmsVerificationPostResponse dsSmsVerificationCreate(SmsVerificationPostRequest smsVerificationPostRequest) {
        String guid = UUID.randomUUID().toString();
        String secretCode = String.format("%04d", new Random().nextInt(100000));

        SmsVerification smsVerification = SmsVerification.builder()
                .phoneNumber(smsVerificationPostRequest.getPhoneNumber())
                .processGuid(guid)
                .secretCode(secretCode)
                .status("WAITING").build();

        repository.save(smsVerification);

        gateway.smsVerificationCreated(MessageBuilder.withPayload(
                        SmsVerificationMessage
                                .builder()
                                .guid(guid)
                                .code(secretCode)
                                .phoneNumber(smsVerificationPostRequest.getPhoneNumber()).build())
                .build());

        return new SmsVerificationPostResponse(guid);
    }
}
