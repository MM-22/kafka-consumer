package com.model;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomerDeserializer implements Deserializer<Customer> {

	private final Logger LOG = LoggerFactory.getLogger(CustomerDeserializer.class);

	@Override
	public void close() {
		LOG.info("inside close method");
	}

	@Override
	public void configure(Map<String, ?> arg0, boolean arg1) {
		LOG.info("inside configure method");
	}

	@Override
	public Customer deserialize(String arg0, byte[] arg1) {
		LOG.info("customer deserialize started");
		ObjectMapper mapper = new ObjectMapper();
		Customer customer = null;
		try {
			customer = mapper.readValue(arg1, Customer.class);
		} catch (Exception e) {
			LOG.error("customer deserialize Exception occured");
			LOG.error(e.getMessage());
		}
		LOG.info("customer deserialize completed");
		return customer;
	}
}
