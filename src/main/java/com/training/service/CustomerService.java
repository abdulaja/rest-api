package com.training.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.model.Customer;
import com.training.repository.CustomerJpaRepository;

@Component
public class CustomerService {
	
	@Autowired
	private CustomerJpaRepository customerJpaRepository;
	private Logger log = LoggerFactory.getLogger(getClass());

	public List<Customer> getfirstName(String firstName) throws JsonProcessingException {
		log.info("\"{}\" | ".concat("input").concat(" ({}) : {}"), "getfirstName", "String", firstName);
		List<Customer> customer = customerJpaRepository.findByFirstname(firstName);
		log.info("\"{}\" | ".concat("output").concat(" ({}) : {}"), "getfirstName", "List<customer>",
				new ObjectMapper().writeValueAsString(customer));
		return customer;
	}
	
	public List<Customer> getLastName(String lastname) throws JsonProcessingException {
		log.info("\"{}\" | ".concat("input").concat(" ({}) : {}"), "getLastName", "String", lastname);
		List<Customer> customer = customerJpaRepository.findByLastname(lastname);
		log.info("\"{}\" | ".concat("output").concat(" ({}) : {}"), "getLastName", "List<customer>",
				new ObjectMapper().writeValueAsString(customer));
		return customer;
	}

	public List<Customer> getByParam(String firstName) throws JsonProcessingException {
		log.info("\"{}\" | ".concat("input").concat(" ({}) : {}"), "getByParam", "String", firstName);
		List<Customer> customer = customerJpaRepository.findByParam(firstName.toLowerCase());
		log.info("\"{}\" | ".concat("output").concat(" ({}) : {}"), "getByParam", "List<customer>",
				new ObjectMapper().writeValueAsString(customer));
		return customer;
	}


	public void saveDataCustomer(Customer p_ObjectData) {

		customerJpaRepository.save(p_ObjectData);
		log.info("\"{}\" | ".concat("input").concat(" ({}) : {}"), "saveDataCustomer", "String", p_ObjectData);
	}

	public boolean isIDExist(Long long1) {
		List<Customer> exist = customerJpaRepository.findById((long) long1);
		return exist.size() != 0 ? false : true;
	}
	
	public String createDataByHash(HashMap<String, Object> param) throws ParseException {
		Customer data = new Customer();
		data.setFirstname(param.get("firstName").toString());
		data.setLastname(param.get("lastName").toString());
		customerJpaRepository.save(data);

		return "Success Created";
	}

}
