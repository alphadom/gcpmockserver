package com.example.gcpmockserver.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.gcpmockserver.domain.Customer;
import com.example.gcpmockserver.exception.CustomerNotFoundException;

@RestController
public class CustomerController {
	
	List<Customer> customerList = new LinkedList<>();

	// POST/customer
	@PostMapping("/customer")
	public ResponseEntity<Void> addPet(@RequestBody Customer body) 
	{
		customerList.add(body);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	// GET/customer/{customerId}
	@CrossOrigin(origins = "http://self-service-web-287019.el.r.appspot.com")
	@GetMapping(value = "/customer/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") Long customerId
) {
		if(customerList.size() > 0) {
			for(int i = 0; i < customerList.size(); i++)
			{
				Customer cust = customerList.get(i);
	        	if(cust!=null && cust.getId() == customerId.intValue())
	        	{
	        		return new ResponseEntity<Customer>(cust, HttpStatus.OK);
	        	}
			}
			throw new CustomerNotFoundException(customerId);
		}
		return null;
    }

}
