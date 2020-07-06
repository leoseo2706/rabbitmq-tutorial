package com.example.rabbitmq.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CommonUtils {
	
	@Autowired
	ObjectMapper mapper;
	
	public String parseObj (Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			log.error("Error logging mapper {}", e.getMessage());
		}
		
		return "";
	}

}
