package com.training.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.training.model.Customer;
import com.training.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value = "/getFirstName", method = RequestMethod.POST)
	private List<Customer> getByfirstName(@RequestBody HashMap<String, String> param) throws JsonProcessingException {
		return customerService.getfirstName(param.get("firstName").toString());
	}
	
	@RequestMapping(value = "/getLastName", method = RequestMethod.POST)
	private List<Customer> getBylastName(@RequestBody HashMap<String, String> param) throws JsonProcessingException {
		return customerService.getLastName(param.get("lastName").toString());
	}
	
	@RequestMapping(value = "/getByParam", method = RequestMethod.POST)
	private List<Customer> getByParam(@RequestBody HashMap<String, String> param) throws JsonProcessingException {
		return customerService.getByParam(param.get("firstName").toString());
	}
	
	@RequestMapping(value = "/customerCreate", method = RequestMethod.POST)
	public String customerCreateByHash(@RequestBody HashMap<String, Object> param) throws ParseException
	{	
		/*String respon = "Failed";
		Boolean exist = personService.isIDExist((Integer)param.get("id"));
		if (exist){
			personService.createDataByHash(param);
			respon = "Success";
		}*/
		return customerService.createDataByHash(param);
	}
}
