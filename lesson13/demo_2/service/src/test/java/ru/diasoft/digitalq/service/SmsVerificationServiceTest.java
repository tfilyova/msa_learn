package ru.diasoft.digitalq.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.diasoft.digitalq.controller.dto.SmsVerificationCheckRequest;
import ru.diasoft.digitalq.controller.dto.SmsVerificationCheckResponse;
import ru.diasoft.digitalq.controller.dto.SmsVerificationPostRequest;
import ru.diasoft.digitalq.controller.dto.SmsVerificationPostResponse;
import ru.diasoft.digitalq.domain.SmsVerification;
import ru.diasoft.digitalq.repository.SmsVerificationRepository;
import ru.diasoft.digitalq.smsverificationcreated.publish.SmsVerificationCreatedPublishGateway;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class SmsVerificationServiceTest {
    @InjectMocks
    private SmsVerificationPrimaryService service;

    @Mock
    private SmsVerificationRepository repository;

    @Mock
    private SmsVerificationCreatedPublishGateway gateway;

    private final String PHONE_NUMBER = "123456";
    private final String VALID_SECRET_CODE = "111";
    private final String INVALID_SECRET_CODE = "000";
    private final String GUID = UUID.randomUUID().toString();

    @Before
    public void init() {

        String STATUS = ServiceConstants.STATUS_OK;

        service = new SmsVerificationPrimaryService(repository, gateway);
        SmsVerification smsVerification =
                SmsVerification.builder()
                        .phoneNumber(PHONE_NUMBER)
                        .processGuid(GUID)
                        .secretCode(VALID_SECRET_CODE)
                        .status(STATUS)
                        .build();
        when(repository.findBySecretCodeAndProcessGuidAndStatus(VALID_SECRET_CODE, GUID, STATUS))
                .thenReturn(Optional.of(smsVerification));
        when(repository.findBySecretCodeAndProcessGuidAndStatus(INVALID_SECRET_CODE, GUID, STATUS))
                .thenReturn(Optional.empty());
    }

    @Test
    public void dsSmsVerificationCheck() {
        SmsVerificationCheckResponse response = service.dsSmsVerificationCheck(new SmsVerificationCheckRequest(GUID,VALID_SECRET_CODE));
        Assert.assertEquals(response.getCheckResult(), true);
    }

    @Test
    public void dsSmsVerificationCheckInvalidCode() {
        SmsVerificationCheckResponse response = service.dsSmsVerificationCheck(new SmsVerificationCheckRequest(GUID, INVALID_SECRET_CODE));
        Assert.assertEquals(response.getCheckResult(), false);
    }

    @Test
    public void dsSmsVerificationCreate() {
        SmsVerificationPostResponse response = service.dsSmsVerificationCreate(new SmsVerificationPostRequest(PHONE_NUMBER));
        Assert.assertNotNull(response.getProcessGUID());
    }
}