package com.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.model.Customer;
import com.service.ConsumerService;

@Component
public class ConsumerKafka {

	private final Logger LOG = LoggerFactory.getLogger(ConsumerKafka.class);

	@Autowired
	private ConsumerService consumerService;

	@KafkaListener(topics = { "${kafka.topic}" })
	public void onMessage(ConsumerRecord<String, Customer> consumerRecord) {

		LOG.info("onMessage methods started");
		Customer customer = consumerRecord.value();
		
		consumerService.save(customer);
		LOG.info("onMessage methods completed");
	}
}