package com.example.rabbitmq.tut1;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
public class QueueConfig1 {
	
	@Bean(name="queue1")
	public Queue hello() {
		return new Queue("hello");
	}
	
	@Profile("receiver")
	@Bean
	public Tut1Receiver receiver() {
		return new Tut1Receiver();
	}
	
	@Profile("sender")
	@Bean
	public Tut1Sender sender() {
		return new Tut1Sender();
	}
	
}
