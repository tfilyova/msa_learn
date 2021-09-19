package msa.lesson10;

import msa.lesson10.subscriber.ConsumerChannels;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(ConsumerChannels.class)
public class Lesson10Application {

	public static void main(String[] args) {
		SpringApplication.run(Lesson10Application.class, args);
	}

}
