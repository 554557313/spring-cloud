package com.woasis.consulconsumer.mq;

import java.io.IOException;
import java.util.Map;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.woasis.consulconsumer.model.UserDto;


@Component
public class MQReceiver {

	@RabbitListener(
	        bindings = @QueueBinding(
	                value = @Queue(value = "boot.queue1", durable = "true"),
	                exchange = @Exchange(value = "direct-exchange", type = "direct", durable = "true", ignoreDeclarationExceptions = "true"),
	                key = "boot.queue1"
	        )
	)
    @RabbitHandler
    public void onMessage(Message message, Channel channel) throws IOException {
		long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
        	//int i = 1/0;//模拟异常，消息重新消费
			//手工ack
			channel.basicAck(deliveryTag,true);
			System.out.println("receive--1: " + new String(message.getBody()));
		} catch (Exception e) {
			e.printStackTrace();
			//异常，nack，消息不删除
			channel.basicNack(deliveryTag, false, true);
		}
    }

	@RabbitListener(
	        bindings = @QueueBinding(
	                value = @Queue(value = "boot.queue1", durable = "true"),
	                exchange = @Exchange(value = "direct-exchange", type = "direct", durable = "true", ignoreDeclarationExceptions = "true"),
	                key = "boot.queue1"
	        )
	)
   @RabbitHandler
    public void onUserMessage(@Payload UserDto user, Channel channel, @Headers Map<String,Object> headers) throws IOException {

        long deliveryTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
        //手工ack
        channel.basicAck(deliveryTag,true);
        System.out.println("receive--2: " + user.toString());
    }
}