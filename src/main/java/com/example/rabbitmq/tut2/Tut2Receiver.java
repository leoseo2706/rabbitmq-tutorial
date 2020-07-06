package com.example.rabbitmq.tut2;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

@RabbitListener(queues = "hello")
public class Tut2Receiver {

	private final int instance;

	public Tut2Receiver(int instance) {
		super();
		this.instance = instance;
	}

	@RabbitHandler
	public void receive(String in) {

		StopWatch watch = new StopWatch();
		watch.start();
		System.out.println("instance: " + instance + " Received " + in);
		try {
			doWork(in);
		} catch (InterruptedException e) {

		}
		watch.stop();
		System.out.println("instance : " + instance + ", done in " + watch.getTotalTimeSeconds() + " seconds");
	}

	private void doWork(String in) throws InterruptedException {
		for (char ch : in.toCharArray()) {
			if (ch == '.') {
				TimeUnit.SECONDS.sleep(1);
			}
		}
	}

}
