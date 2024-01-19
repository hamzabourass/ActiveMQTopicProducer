package ma.brokers.springactivemqtopicproducer.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import jakarta.jms.ConnectionFactory;
//Enable JMS listener annotated endpoints that are created underthe cover by a JmsListenerContainerFactory.
//To be used on @Configuration classes
@Configuration
@EnableJms
public class ConnectionFactoryConfig {
    @Value("${jsa.activemq.broker.url}")
    String brokerUrl;
    @Value("${jsa.activemq.borker.username}")
    String userName;
    @Value("${jsa.activemq.borker.password}")
    String password;
    /*
     * Initial ConnectionFactory
     */
    @Bean
    public ConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory connectionFactory = new
                ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(brokerUrl);
        connectionFactory.setUserName(userName);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

    @Bean // Serialize message content to json using TextMessage
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new
                MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    /*
     * Used for sending Messages.
     */
    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setMessageConverter(jacksonJmsMessageConverter());

//Configure the destination accessor with knowledge of the JMS domain used.Default is Point-to-Point (Queues).
//Parameters:pubSubDomain "true" for the Publish/Subscribe domain(Topics),"false" for the Point-to-Point domain (Queues)
        template.setPubSubDomain(true);

        return template;
    }
}

