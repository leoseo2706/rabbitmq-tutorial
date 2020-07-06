package com.example.rabbitmq.tut3;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"tut3", "pub-sub", "publish-subscribe"})
@Configuration
public class QueueConfig3 {
	
	@Bean
	public FanoutExchange fanout() {
		return new FanoutExchange("tut.fanout");
	}
	
	@Profile("receiver3")
    private static class ReceiverConfig {
		
		@Bean
        public AnonymousQueue autoDeleteQueue1() {
            return new AnonymousQueue();
        }
		
		@Bean
        public AnonymousQueue autoDeleteQueue2() {
            return new AnonymousQueue();
        }
		
		@Bean(name="binding3_1")
		public Binding binding1 (FanoutExchange fanout,
				AnonymousQueue autoDeleteQueue1) {
			return BindingBuilder.bind(autoDeleteQueue1).to(fanout);
		}
		
		@Bean(name="binding3_2")
		public Binding binding2 (FanoutExchange fanout,
				AnonymousQueue autoDeleteQueue2) {
			return BindingBuilder.bind(autoDeleteQueue2).to(fanout);
		}
		
	    @Bean
	    public Tut3Receiver receiver() {
	        return new Tut3Receiver();
	    }
		
	}
	
	@Profile("sender3")
    @Bean
    public Tut3Sender sender() {
        return new Tut3Sender();
    }

}
