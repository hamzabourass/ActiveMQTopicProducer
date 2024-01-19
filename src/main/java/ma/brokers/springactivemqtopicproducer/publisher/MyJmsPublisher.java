package ma.brokers.springactivemqtopicproducer.publisher;

import ma.brokers.springactivemqtopicproducer.models.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
@Component
public class MyJmsPublisher {
    private static final Logger logger =
            LoggerFactory.getLogger(MyJmsPublisher.class);
    //Default settings for JMS Sessions are "not transacted" and "autoacknowledge".As defined by the Java EE specification,
    @Autowired
    JmsTemplate jmsTemplate;
    @Value("${jsa.activemq.topic}")
    String topic;
    public void send(Company apple){
        jmsTemplate.convertAndSend(topic, apple);
        logger.info("Message : {} published to topic: {} successfully.", apple.toString(), topic);
    }
}

