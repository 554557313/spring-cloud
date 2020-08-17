package com.woasis.consulproducer.mq;

import java.util.Date;
import java.util.Map;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.woasis.consulproducer.model.UserDto;

@Component
public class MQSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * ConfirmCallback
     * rabbitmq的confirm机制，即mq收到消息后会发送ack标识给生产者，
     * ack为true表示收到了，ack为false表示没收到或丢了。
     * rabbitTemplate中有confirmCallback，在这个callback重新处理发送失败的消息，比如重发。
     */
    final RabbitTemplate.ConfirmCallback confirmCallback= new RabbitTemplate.ConfirmCallback() {

        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            System.out.println("correlationData: " + correlationData);
            System.out.println("ack: " + ack);
            if(!ack){
                System.out.println("异常处理....");
            }
        }

    };

    /**
     * 消息到了交换机，但是找不到queue的消息都会进入到returnCallback，在这个callback重新处理发送是失败的消息，比如重发。
     */
    final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {

        public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
            System.out.println("return exchange: " + exchange + ", routingKey: "
                    + routingKey + ", replyCode: " + replyCode + ", replyText: " + replyText);
        }
    };

    //发送消息方法调用: 构建Message消息
    public void send(Object message, Map<String, Object> properties) throws Exception {
        MessageProperties mp = new MessageProperties();
        //在生产环境中这里不用Message，而是使用 fastJson 等工具将对象转换为 json 格式发送
        Message msg = new Message(message.toString().getBytes(),mp);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        //id + 时间戳 全局唯一
        CorrelationData correlationData = new CorrelationData("1234567890"+new Date());
        rabbitTemplate.convertAndSend("direct-exchange", "boot.queue1", msg, correlationData);
    }
    //发送消息方法调用: 构建Message消息
    public void sendUser(UserDto user) throws Exception {
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        //id + 时间戳 全局唯一
        CorrelationData correlationData = new CorrelationData("1234567890"+new Date());
        rabbitTemplate.convertAndSend("BOOT-EXCHANGE-1", "boot.save", user, correlationData);
    }
}