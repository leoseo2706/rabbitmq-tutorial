package com.example.rabbitmq.tut2;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"tut2", "work-queues"})
@Configuration
public class QueueConfig2 {
	
	
	@Bean(name="queue2")
	public Queue hello() {
		return new Queue("hello");
	}
	
	
	@Profile("receiver2")
	private static class ReceiverConfig {
		
		@Bean
        public Tut2Receiver receiver1() {
            return new Tut2Receiver(1);
        }
		
		@Bean
        public Tut2Receiver receiver2() {
            return new Tut2Receiver(2);
        }
	}
	
	@Profile("sender2")
    @Bean
    public Tut2Sender sender() {
        return new Tut2Sender();
    }
	
	

}
