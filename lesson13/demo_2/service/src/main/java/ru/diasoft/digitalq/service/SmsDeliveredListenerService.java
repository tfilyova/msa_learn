package ru.diasoft.digitalq.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.diasoft.digitalq.model.SmsDeliveredMessage;
import ru.diasoft.digitalq.repository.SmsVerificationRepository;
import ru.diasoft.digitalq.smsverificationdelivered.subscribe.SmsVerificationDeliveredSubscribeListenerService;

@Service
@Primary
@RequiredArgsConstructor
public class SmsDeliveredListenerService implements SmsVerificationDeliveredSubscribeListenerService {
    private final SmsVerificationRepository repository;

    @Override
    public void smsVerificationDelivered(SmsDeliveredMessage msg) {
        repository.updateStatusByProcessGuid(ServiceConstants.STATUS_OK, msg.getGuid());
    }
}
