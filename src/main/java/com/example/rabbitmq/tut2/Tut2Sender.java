package com.example.rabbitmq.tut2;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;

public class Tut2Sender {
	
	@Autowired
	private RabbitTemplate template;
	
	@Autowired
	@Qualifier(value="queue2")
	private Queue queue;
	
	AtomicInteger dots = new AtomicInteger(0);

    AtomicInteger count = new AtomicInteger(0);
	
	@Scheduled(fixedDelay = 1000, initialDelay = 500)
	public void send() {
		
		StringBuilder sb = new StringBuilder("hello");
		if (dots.incrementAndGet() == 4) {
			dots.set(1);
		}
		
		for (int i = 0; i < dots.get(); i++) {
			sb.append('.');
		}
		
		sb.append(count.incrementAndGet());
		String msg = sb.toString();
		
		
		template.convertAndSend(queue.getName(), msg);
		
		System.out.println("Sent ["  + msg  +" ]");
		
	}

}
