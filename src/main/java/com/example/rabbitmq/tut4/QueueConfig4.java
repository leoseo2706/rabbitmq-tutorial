package com.example.rabbitmq.tut4;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"tut4","routing"})
public class QueueConfig4 {

	@Bean
	public DirectExchange direct() {
		return new DirectExchange("tut.direct");
	}

	@Profile("receiver4")
	private static class ReceiverConfig {

		@Bean
		public AnonymousQueue autoDeleteQueue1() {
			return new AnonymousQueue();
		}

		@Bean
		public AnonymousQueue autoDeleteQueue2() {
			return new AnonymousQueue();
		}

		@Bean(name = "binding4_1")
		public Binding binding1(DirectExchange direct, AnonymousQueue autoDeleteQueue1) {
			return BindingBuilder.bind(autoDeleteQueue1).to(direct).with("orange");
		}

		@Bean(name = "binding4_2")
		public Binding binding2(DirectExchange direct, AnonymousQueue autoDeleteQueue1) {
			return BindingBuilder.bind(autoDeleteQueue1).to(direct).with("black");
		}

		@Bean(name = "binding4_3")
		public Binding binding3(DirectExchange direct, AnonymousQueue autoDeleteQueue2) {
			return BindingBuilder.bind(autoDeleteQueue2).to(direct).with("green");
		}
		
		@Bean
        public Tut4Receiver receiver() {
            return new Tut4Receiver();
        }
	}

	@Profile("sender4")
	@Bean
	public Tut4Sender sender() {
		return new Tut4Sender();
	}

}
