package com.consumer;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.model.Address;
import com.model.Customer;
import com.model.CustomerStatus;
import com.repository.CustomerRepository;
import com.service.ConsumerServiceImpl;
import com.utils.Utils;

@SpringBootTest
public class ConsumerServiceImplTest {

	@InjectMocks
	private ConsumerServiceImpl customerServiceImpl;

	@Mock
	private Utils utils;

	@Mock
	private CustomerRepository customerRepository;
	
	@Test
	public void saveTest() {
		Customer customer = new Customer("C000000002", "Mahesh", "Manchala", "05-07-1982", "INDIA", "IN", "9493971459");
		customer.setEmail("maheshmanchala92@gmail.com");
		customer.setCustomerStatus(CustomerStatus.RESTORED);
		
		Address address = new Address();
		address.setAddressLine1("1-25/2 SHEKALLA");
		address.setAddressLine2("GOLLAPELLI");
		address.setStreet("TELANGANA");
		address.setPostalCode("50553");
		customer.setAddress(address);
		
		doNothing().when(customerRepository).save("1-25/2 SHEKALLA", "GOLLAPELLI", "50553", "TELANGANA",
				"05-07-1982", "INDIA", "IN", "C000000002",
				CustomerStatus.RESTORED, "maheshmanchala92@gmail.com", 
				"Mahesh", "Manchala", "9493971459");
		when(utils.maskCustomerDetailsWithStars(customer)).thenReturn(customer);
		customerServiceImpl.save(customer);
		
		
	}

}
