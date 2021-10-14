package ru.diasoft.digitalq.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.diasoft.digitalq.domain.SmsVerification;
import ru.diasoft.digitalq.service.ServiceConstants;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class SmsVerificationRepositoryTest {

    @Autowired
    private SmsVerificationRepository repository;

    private final String PHONE_NUMBER = "123456";
    private final String VALID_SECRET_CODE = "111";
    private final String INVALID_SECRET_CODE = "000";

    @Test
    public void smsVerificationCreateTest() {
        SmsVerification smsVerification = SmsVerification.builder()
                .processGuid(UUID.randomUUID().toString())
                .phoneNumber(PHONE_NUMBER)
                .secretCode(VALID_SECRET_CODE)
                .status(ServiceConstants.STATUS_WAITING)
                .build();
        SmsVerification createdEntity = repository.save(smsVerification);
        assertThat(smsVerification).isEqualToComparingOnlyGivenFields(createdEntity, "verificationId");
        assertThat(createdEntity.getVerificationId()).isNotNull();
    }

    @Test
    public void findBySecretCodeAndProcessGuidAndStatus() {
        String processGuid = UUID.randomUUID().toString();

        SmsVerification smsVerification =
                SmsVerification.builder()
                        .processGuid(processGuid)
                        .phoneNumber(PHONE_NUMBER)
                        .secretCode(VALID_SECRET_CODE)
                        .status(ServiceConstants.STATUS_OK)
                        .build();

        SmsVerification createdEntity = repository.save(smsVerification);

        Optional<SmsVerification> foundEntity = repository.findBySecretCodeAndProcessGuidAndStatus(VALID_SECRET_CODE, processGuid, ServiceConstants.STATUS_OK);
        assertTrue(foundEntity.isPresent());
        assertEquals(createdEntity, foundEntity.orElse(SmsVerification.builder().build()));

        assertFalse(repository.findBySecretCodeAndProcessGuidAndStatus(INVALID_SECRET_CODE, processGuid, ServiceConstants.STATUS_OK).isPresent());

    }

    @Test
    public void updateStatusByProcessGuidTest() {
        String processGuid = UUID.randomUUID().toString();
        SmsVerification smsVerification = SmsVerification.builder()
                .processGuid(processGuid)
                .phoneNumber(PHONE_NUMBER)
                .secretCode(VALID_SECRET_CODE)
                .status(ServiceConstants.STATUS_WAITING)
                .build();

        SmsVerification createdEntity = repository.save(smsVerification);

        repository.updateStatusByProcessGuid(ServiceConstants.STATUS_OK, processGuid);

        assertThat(repository.findById(createdEntity.getVerificationId())
                .orElse(SmsVerification.builder().build()).getStatus()).isEqualTo(ServiceConstants.STATUS_OK);
    }

}