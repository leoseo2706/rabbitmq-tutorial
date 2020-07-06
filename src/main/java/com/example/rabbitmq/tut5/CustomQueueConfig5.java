package com.example.rabbitmq.tut5;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"custom-tut5"})
public class CustomQueueConfig5 {
	
	@Bean
	public TopicExchange topic() {
		return new TopicExchange("custom.tut.topic");
	}
	
	@Profile("custom-receiver5")
    private static class ReceiverConfig {
		
		@Bean
	    public CustomTut5Receiver receiver() {
	        return new CustomTut5Receiver();
	    }
		
		@Bean
        public Queue autoDeleteQueue1() {
            return new AnonymousQueue();
        }

        @Bean
        public Queue autoDeleteQueue2() {
            return new AnonymousQueue();
        }
        
        @Bean
        public Binding binding1 (TopicExchange topic, Queue autoDeleteQueue1) {
        		return BindingBuilder.bind(autoDeleteQueue1).to(topic).with("*.orange.*");
        }
        
        @Bean
        public Binding binding2 (TopicExchange topic, Queue autoDeleteQueue1) {
        		return BindingBuilder.bind(autoDeleteQueue1).to(topic).with("*.*.rabbit");
        }
        
        @Bean
        public Binding binding3 (TopicExchange topic, Queue autoDeleteQueue2) {
        		return BindingBuilder.bind(autoDeleteQueue2).to(topic).with("lazy.#");
        }
	}
	
	@Profile("custom-sender5")
    @Bean
    public CustomTut5Sender sender() {
        return new CustomTut5Sender();
    }

}
