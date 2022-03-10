package com.cwt.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.aspectj.util.Reflection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils.FieldFilter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwt.entities.Customer;
import com.cwt.entities.Order;
import com.cwt.persistence.CustomerRepository;
import com.cwt.service.CustomerService;
import com.cwt.service.ValidatorService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ValidatorService validatorService;

	@GetMapping("/{custId}")
	public Customer getCustomer(@Valid @PathVariable Long custId) {
		return customerService.findCustomerById(custId);
	}

	@GetMapping("/all")
	public List<Customer> getCustomers() {
		return customerService.findAllCustomers();
	}

	@PostMapping("/createCustomer")
	public Customer createCustomer(@Valid @RequestBody Customer customer) {

		return customerRepository.save(customer);
	}
	@PostMapping("/createCustomerController")
	public ResponseEntity<Customer> createCustomerController(@Valid @RequestBody Customer customer) {

		 return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
	}
	@PostMapping("/create")
	public ResponseEntity<?> create(@Valid @RequestBody Customer customer, BindingResult bindingResult) {
		Map<String, String> validationMap = validatorService.validate(bindingResult);
		System.err.println(validationMap);
		if (validationMap.isEmpty()) {
			customerService.createCustomerRecord(customer);
			return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Map>(validationMap, HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/createOrder")
	public ResponseEntity<?> createOrder(@PathVariable Long id, @Valid @RequestBody Order order,
			BindingResult bindingResult) {
		Map<String, String> validationMap = validatorService.validate(bindingResult);
		System.err.println(validationMap);
		if (validationMap.isEmpty()) {
			customerService.createCustomerOrder(id, order);
			return new ResponseEntity<Order>(order, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Map>(validationMap, HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> deletePost(@Valid @PathVariable Long id) {
		customerService.deleteCustomerRecord(id);
		return ResponseEntity.ok("Customer record is deleted.");

	}
	@DeleteMapping(value = "/deleteCustomerController/{id}")
	public ResponseEntity<HttpStatus> removeEmployee (@PathVariable("id") Long id)
	{
	    //code
	    return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
	}
	@DeleteMapping(value = "/deleteCustomer/{customer}")
	public ResponseEntity<String> deleteByCustomer(@RequestBody @Valid Customer customer) {
		customerService.deleteByCustomer(customer);
		return ResponseEntity.ok("Customer record is deleted.");

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Customer> updateCustomer(@Valid @PathVariable(value = "id") Long id,
			@Valid @RequestBody Customer customer) {
		final Customer updatedCustomer = customerService.updateCustoerRecord(id, customer);
		return ResponseEntity.ok(updatedCustomer);

	}

	@GetMapping("/getCustomers")
	public List<Customer> findAllCustomersByLocation(@PathVariable String location) {
		return customerService.findAllByCustomers(location);
	}

	@PatchMapping("/updatePatch/{id}")
	public ResponseEntity<String> updateManager(@Valid @PathVariable(value = "id") Long id,
			@Valid @RequestBody Map<Object, Object> fields) {
		Customer customer = customerService.findCustomerById(id);
		fields.forEach((k, v) -> {
			Field field = ReflectionUtils.findRequiredField(Customer.class, (String) k);
			field.setAccessible(true);
			ReflectionUtils.setField(field, customer, v);
		});

		customerService.updateCustoerRecord(id, customer);
		return ResponseEntity.ok("Customer record is updated using patch method.");
	}
}
