package com.example.rabbitmq.tut1;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class Tut1Receiver {

	@RabbitHandler
	@RabbitListener(queues = {"hello"})
	public void receive(String in) {
		System.out.println("Received: " + in);
	}
}
