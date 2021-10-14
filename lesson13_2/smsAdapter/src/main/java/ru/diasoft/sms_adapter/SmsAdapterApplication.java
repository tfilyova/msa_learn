package ru.diasoft.sms_adapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(MsgGateway.class)
public class SmsAdapterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmsAdapterApplication.class, args);
    }

}
