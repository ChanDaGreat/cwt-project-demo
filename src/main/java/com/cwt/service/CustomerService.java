package com.cwt.service;

import java.util.List;

import javax.validation.Valid;

import com.cwt.entities.Customer;
import com.cwt.entities.Order;

public interface CustomerService {
	public Customer findCustomerById(Long custId);
	public List<Customer> findAllCustomers();
	public List<Customer> findAllByCustomers(String location);
	public Customer createCustomerRecord(Customer customer);
	public void createCustomerOrder(Long id, Order order);
	public void deleteCustomerRecord(Long id);
	public Customer updateCustoerRecord(Long custId, Customer newCustomer);
	public void deleteByCustomer(@Valid Customer customer);
}
