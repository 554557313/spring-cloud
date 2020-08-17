package com.woasis.consulproducer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MQConfig {
//    @Bean
//    public ConnectionFactory connectionFactory(){
//        return new CachingConnectionFactory();
//    }
//
//    @Bean
//    public RabbitAdmin rabbitAdmin(){
//        return new RabbitAdmin(connectionFactory());
//    }
    @Bean
    public Exchange bootExchange(){
        return new TopicExchange("BOOT-EXCHANGE-1", true, false);
    }

    @Bean
    public Queue bootQueue(){
        return new Queue("boot.queue1", true);
    }
    
    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange("direct-exchange");
    }
    
    @Bean
    public Binding binding() {
 
        return BindingBuilder.bind(bootQueue()).to(defaultExchange()).with("boot.queue1");
    }
}