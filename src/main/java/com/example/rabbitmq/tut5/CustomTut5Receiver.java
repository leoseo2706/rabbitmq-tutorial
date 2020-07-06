package com.example.rabbitmq.tut5;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.rabbitmq.payload.Test;
import com.example.rabbitmq.utils.CommonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RabbitListener(queues = {"#{autoDeleteQueue1.name}"})
@Slf4j
public class CustomTut5Receiver {
	
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	CommonUtils utils;

//	@RabbitHandler
//	public void receive1(Test test) {
//		log.info("Processed {}", test.toString());
//	}
	
	@RabbitHandler
	public String receive2(Test test) {
		log.info("Processed {}", test.toString());
		return test.toString();
	}
	
	@RabbitHandler(isDefault = true)
	public void receiveDefault(Object obj) {
		log.info("Received obj {}", utils.parseObj(obj));
	}
	
	
}
