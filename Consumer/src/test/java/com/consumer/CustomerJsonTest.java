package com.consumer;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.model.Address;
import com.model.Customer;
import com.model.CustomerStatus;
import com.utils.Utils;

@SpringBootTest
public class CustomerJsonTest {

	@Autowired
	private Utils utils;
	
	private final Logger LOG = LoggerFactory.getLogger(CustomerJsonTest.class);

	@Test
	public void getJsonStringTest() {
		
		LOG.info("getJsonStringTest() started");
		
		Customer customer = new Customer("C000000002", "Mahesh", "Manchala", "05-07-1982", "INDIA", "IN", "9493971459");
		customer.setEmail("maheshmanchala92@gmail.com");
		customer.setCustomerStatus(CustomerStatus.RESTORED);
		
		Address address = new Address();
		address.setAddressLine1("1-25/2 SHEKALLA");
		address.setAddressLine2("GOLLAPELLI");
		address.setStreet("TELANGANA");
		address.setPostalCode("50553");
		customer.setAddress(address);

		String stringJson = utils.getJsonString(customer);
		assertEquals("{\"customerNumber\":\"C000000002\",\"firstName\":\"Mahesh\",\"lastName\":\"Manchala\","
				+ "\"birthdate\":\"05-07-1982\",\"country\":\"INDIA\",\"countryCode\":\"IN\","
				+ "\"mobileNumber\":\"9493971459\",\"email\":\"maheshmanchala92@gmail.com\","
				+ "\"customerStatus\":\"RESTORED\",\"address\":{\"addressLine1\":\"1-25/2 SHEKALLA\","
				+ "\"addressLine2\":\"GOLLAPELLI\",\"street\":\"TELANGANA\",\"postalCode\":\"50553\"}}",stringJson);
		LOG.info(stringJson);
		LOG.info("getJsonStringTest() completed");
		
		
	}

}
