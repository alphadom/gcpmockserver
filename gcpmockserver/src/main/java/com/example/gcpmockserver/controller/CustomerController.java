package com.example.gcpmockserver.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	@GetMapping("/customer/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") Long customerId
) {
        //String accept = request.getHeader("Accept");
        //if (accept != null) {
//            try {
//                return new ResponseEntity<Customer>(objectMapper.readValue("{\n  \"photoUrls\" : [ \"photoUrls\", \"photoUrls\" ],\n  \"name\" : \"doggie\",\n  \"id\" : 0,\n  \"category\" : {\n    \"name\" : \"name\",\n    \"id\" : 6\n  },\n  \"tags\" : [ {\n    \"name\" : \"name\",\n    \"id\" : 1\n  }, {\n    \"name\" : \"name\",\n    \"id\" : 1\n  } ],\n  \"status\" : \"available\"\n}", Customer.class), HttpStatus.NOT_IMPLEMENTED);
//            } catch (IOException e) {
//                log.error("Couldn't serialize response for content type application/json", e);
//                return new ResponseEntity<Customer>(HttpStatus.INTERNAL_SERVER_ERROR);
//            }
		if(customerList.size() >= customerId.intValue()) {
			Customer cust = customerList.get(customerId.intValue() - 1);
        	if(cust!=null)
        	{
        		return new ResponseEntity<Customer>(cust, HttpStatus.OK);
        	}
		}
		else {
			throw new CustomerNotFoundException(customerId);
		}
		return null;
    }

}
