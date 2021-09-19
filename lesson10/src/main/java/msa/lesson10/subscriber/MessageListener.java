package msa.lesson10.subscriber;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
public class MessageListener {

    private Logger logger = Logger.getLogger(MessageListener.class.getName());


    @StreamListener(ConsumerChannels.BROADCASTS)
    public void broadcasted(String message) {
        logger.log(Level.INFO,message);
    }

}