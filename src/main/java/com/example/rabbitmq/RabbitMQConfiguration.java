package com.example.rabbitmq;

import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfiguration {
	
	@Bean
    public ConnectionFactory connectionFactory() {
		CachingConnectionFactory cFactory = new CachingConnectionFactory("localhost");
		cFactory.setUsername("guest");
		cFactory.setPassword("guest");
		return cFactory;
	}
	
	@Bean
	public RabbitTemplate rabbitTemplate() {
		RabbitTemplate template = new RabbitTemplate(connectionFactory());
		template.setMessageConverter(jsonMessageConverter());
		template.setReplyTimeout(120000);
		return template;
	}
	
	@Bean
	public AsyncRabbitTemplate asyncTemplate() {
		return new AsyncRabbitTemplate(rabbitTemplate());
	}
	
	@Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
