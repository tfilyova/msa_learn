package ru.diasoft.digitalq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import lombok.Generated;

@EnableDiscoveryClient
@EnableCaching
@ComponentScan({"ru.diasoft.micro", "ru.diasoft.digitalq"})
@SpringBootApplication
@Generated
@SuppressWarnings({"java:S4604"})
public class DemoApplication {

    @SuppressWarnings({"java:S1148", "Diasoft-Java-Custom:FindReflectionCheck"})
    public static void main(String[] args) {
        try {
            SpringApplication.run(DemoApplication.class, args);
        } catch (RuntimeException ex) {
            if (!ex.getClass().getSimpleName().contains("SilentExitException")) {
                ex.printStackTrace();
            }
            throw ex;
        }
    }

}
