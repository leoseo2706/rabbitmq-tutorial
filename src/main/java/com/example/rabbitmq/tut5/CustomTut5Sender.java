package com.example.rabbitmq.tut5;

import java.util.concurrent.ExecutionException;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.concurrent.ListenableFuture;

import com.example.rabbitmq.payload.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomTut5Sender {

	@Autowired
	private RabbitTemplate template;

	@Autowired
	private AsyncRabbitTemplate asyncTemplate;

	@Autowired
	private TopicExchange topic;

//	@Scheduled(fixedDelay = 5000, initialDelay = 500)
	public void asyncCustomSend() {
		for (int i = 0; i < 10; i++) {
			Test test = new Test("name" + i, i);
			// this.template.convertAndSend(topic.getName(), "quick.orange.rabbit", test);
			ListenableFuture<String> result = this.asyncTemplate.convertSendAndReceive(topic.getName(),
					"quick.orange.rabbit", test);

			log.info("do something else !!!");

			try {
				log.info("Getting {}", result.get());
			} catch (InterruptedException | ExecutionException e) {
				log.error("error {}", e);
			}
		}
	}
	
	@Scheduled(fixedDelay = 5000, initialDelay = 500)
	public void asyncDeadLetterSend() {
		
	}

}
