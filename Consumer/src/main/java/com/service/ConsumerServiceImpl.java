package com.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Address;
import com.model.Customer;
import com.repository.CustomerRepository;
import com.utils.Utils;

@Transactional
@Service
public class ConsumerServiceImpl implements ConsumerService {

	private final Logger LOG = LoggerFactory.getLogger(ConsumerServiceImpl.class);

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private Utils utils;

	@Override
	public void save(Customer customer) {
		try {

			String logMessage = String.format("customer details after masking %s", utils.maskCustomerDetailsWithStars(customer));
			LOG.info(logMessage);

			Address address = customer.getAddress();

			customerRepository.save(address.getAddressLine1(), address.getAddressLine2(), address.getPostalCode(),
					address.getStreet(), customer.getBirthdate(), customer.getCountry(), customer.getCountryCode(),
					customer.getCustomerNumber(), customer.getCustomerStatus(), customer.getEmail(),
					customer.getFirstName(), customer.getLastName(), customer.getMobileNumber());

			LOG.info("customer details saved successfully");
			
			customerRepository.insertAuditLog(customer.getCustomerNumber(), utils.getJsonString(customer));
			
			LOG.info("insert AuditLog");
		} catch (Exception e) {
			LOG.error(e.toString());
			customerRepository.insertErrorLog(e.getClass().toString(), e.getMessage(), utils.getJsonString(customer));
			LOG.error("insert ErrorLog");
		}
	}
}
