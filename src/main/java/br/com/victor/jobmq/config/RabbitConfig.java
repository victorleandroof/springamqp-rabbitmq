package br.com.victor.jobmq.config;

import br.com.victor.jobmq.listener.Receiver;
import br.com.victor.jobmq.properties.JmsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

@Configuration
public class RabbitConfig {

    private JmsProperties jmsProperties;

    @Autowired
    public RabbitConfig(JmsProperties jmsProperties) {
        this.jmsProperties = jmsProperties;
    }

    @Bean
    public Queue queue() {
        return new Queue(jmsProperties.getQueue(), false);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("gateway-exchange");
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(jmsProperties.getQueue());
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(jmsProperties.getHost());
        connectionFactory.setPort(jmsProperties.getPort());
        connectionFactory.setUsername(jmsProperties.getUsername());
        connectionFactory.setPassword(jmsProperties.getPassword());
        connectionFactory.setVirtualHost(jmsProperties.getVirtualHost());
        connectionFactory.setConnectionTimeout(30000);
        connectionFactory.setRequestedHeartBeat(30);
        return connectionFactory;
    }


    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(jmsProperties.getQueue());
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

}
