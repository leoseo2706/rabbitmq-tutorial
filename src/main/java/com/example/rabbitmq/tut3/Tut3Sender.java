package com.example.rabbitmq.tut3;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class Tut3Sender {

	@Autowired
	private RabbitTemplate template;

	@Autowired
	private FanoutExchange fanout;

	AtomicInteger dots = new AtomicInteger(0);

	AtomicInteger count = new AtomicInteger(0);

	@Scheduled(fixedDelay = 1000, initialDelay = 500)
	public void send() {

		StringBuilder sb = new StringBuilder("Hello");
		if (dots.getAndIncrement() == 3) {
			dots.set(1);
		}
		for (int i = 0; i < dots.get(); i++) {
			sb.append('.');
		}
		
		sb.append(count.incrementAndGet());
		
		String msg = sb.toString();
		
		template.convertAndSend(fanout.getName(), "", msg);
		
		System.out.println("Sent [" + msg + "]");
	}

}
