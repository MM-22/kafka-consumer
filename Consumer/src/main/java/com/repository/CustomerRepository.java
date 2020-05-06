package com.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.model.CustomerStatus;

@Repository
public class CustomerRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void save(String addressLine1, String addressLine2, String postalCode,
			String street, String birthdate, String country, String countryCode,
			String customerNumber, CustomerStatus customerStatus, String email,
			String firstName, String lastName, String mobileNumber) {
		
		jdbcTemplate.update("insert into customer (address_line1, address_line2, postal_code,"
				+ " street, birthdate, country, country_code, customer_number,"
				+ " customer_status, email, first_name, last_name, mobile_number)"
				+ " values (?, ?, ?, ?, AES_ENCRYPT(?, 'secret'), ?, ?,"
				+ " AES_ENCRYPT(?, 'secret'), ?, AES_ENCRYPT(?, 'secret'), ?, ?, ?)",
				addressLine1, addressLine2, postalCode,	street, birthdate, 
				country, countryCode, customerNumber, customerStatus.getShortCode(), 
				email, firstName, lastName, mobileNumber);
	}

	public void insertErrorLog(String errorType, String errorDescription, String payload) {
		
		jdbcTemplate.update("insert into errorlog (error_type, error_description, payload) "
				+ "values (?, ?, ?)", errorType, errorDescription, payload);
	}

	public void insertAuditLog(String customerNumber, String payload) {

		jdbcTemplate.update("insert into auditlog (customer_number, payload) values (?, ?)",
				customerNumber, payload);

	}

}
