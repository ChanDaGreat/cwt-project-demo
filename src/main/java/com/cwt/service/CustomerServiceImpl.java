package com.cwt.service;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cwt.entities.Customer;
import com.cwt.entities.Order;
import com.cwt.persistence.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer findCustomerById(@Valid Long custId) {
		Customer customer = null;
		Optional<Customer> optionalCustomer = customerRepository.findById(custId);
		 if(optionalCustomer.isPresent()) {
			 customer = optionalCustomer.get();
		 }else {
			 throw new RuntimeErrorException(new Error("Something is wrong with your build. Cannot find resource "));
		 }
		return customer;
	}

	@Override
	public List<Customer> findAllCustomers() {
		// TODO Auto-generated method stub
		return (List<Customer>) customerRepository.findAll();
	}

	@Override
	public Customer createCustomerRecord(@Valid Customer customer) {
		// TODO Auto-generated method stub
		
		return customerRepository.save(customer);

	}

	@Override
	public void deleteCustomerRecord(@Valid Long custId) {
		// TODO Auto-generated method stub
		Customer customer = customerRepository.findById(custId).orElse(new Customer());
		customerRepository.delete(customer);
	}

	@Override
	public Customer updateCustoerRecord(@Valid Long custId, @Valid Customer newCustomer){
		// TODO Auto-generated method stub
		Customer customer = customerRepository.findById(custId).orElse(new Customer());
		customer.setCustId(custId);
		customer.setFirstName(newCustomer.getFirstName());
		customer.setLastName(newCustomer.getLastName());
		customer.setEmail(newCustomer.getEmail());
		customer.setLocation(newCustomer.getLocation());
		Customer updated = customerRepository.save(customer);
		return updated;
	}

	@Override
	public void createCustomerOrder(Long id, Order order) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByCustomer(@Valid Customer customer) {
		// TODO Auto-generated method stub
//		Customer customerDel = customerRepository.findById(customer.getCustId()).orElse(new Customer());
		customerRepository.delete(customer);
	}

	@Override
	public List<Customer> findAllByCustomers(String location) {
		// TODO Auto-generated method stub
		
		return customerRepository.findAllByLocation(location);
	}
}
