package com.cwt;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.cwt.entities.Customer;
import com.cwt.persistence.CustomerRepository;
import com.cwt.service.CustomerService;
import com.cwt.service.CustomerServiceImpl;

@Configuration
@SpringBootTest
class CwtRestApiDemoApplicationTests {

	@InjectMocks
	CustomerService service = new CustomerServiceImpl();
	@Mock
	CustomerRepository customerRepository;

	@Test
	@DisplayName("Save Customer record test")
	void saveCustomer() {
		Customer customer = new Customer((long) 2, "Juan", "Pablo", "Juanpablo@gmail.com", "Makati", null);
		Mockito.when(customerRepository.save(customer)).thenReturn(customer);
		Customer expectedCustomer = customerRepository.save(customer);
		assertEquals(expectedCustomer, customer);
	}
	
	
	@Test
	@DisplayName("Find Customer by location")
	void getCustomerByLocation() {
		String address = "manila";
		Customer customer = new Customer((long)3,"Pablo","Escobar","drugmedown@gmail.com","manila",null);
		Mockito.when(customerRepository.findAllByLocation(address)).thenReturn(
				Stream.of(
						new Customer
						((long)3,"Pablo","Escobar","drugmedown@gmail.com","manila",null)
						).collect(Collectors.toList()));
		assertEquals(1,customerRepository.findAllByLocation(address).size());

	}
	
	@Test
	@DisplayName("Update Customer record test")
	void updateCustomer() {
		Customer customer = new Customer((long) 2, "Juan", "Pablo", "Juanpablo@gmail.com", "Makati", null);
		Mockito.when(customerRepository.save(customer)).thenReturn(customer);
		Customer updateCustomer = new Customer();
		updateCustomer.setCustId(customer.getCustId());
		updateCustomer.setFirstName("John");
		updateCustomer.setLastName("Pablo");
		updateCustomer.setEmail("johnpablo@gmail.com");
		updateCustomer.setLocation("Makati");
		Customer updated = customerRepository.save(updateCustomer);
		assertNotEquals(updateCustomer, updated);
	}
	@Test
	@DisplayName("Partial update Customer record test")
	void updatePartialCustomer() {
		Customer customer = new Customer((long) 2, "Juan", "Pablo", "Juanpablo@gmail.com", "Makati", null);
		Mockito.when(customerRepository.save(customer)).thenReturn(customer);
		Customer toUpdateCustomer = customer;
		toUpdateCustomer.setCustId(customer.getCustId());
		toUpdateCustomer.setFirstName("New name");
		toUpdateCustomer.setLastName(customer.getLastName());
		toUpdateCustomer.setEmail(customer.getEmail());
		toUpdateCustomer.setLocation(customer.getLocation());
		Customer updated = customerRepository.save(toUpdateCustomer);
		assertEquals(toUpdateCustomer, updated);
	}
	@Test
	@DisplayName("Delete Customer record test")
	void deleteCustomer() {
		Customer customer = new Customer((long) 2, "Juan", "Pablo", "Juanpablo@gmail.com", "Makati", null);
		customerRepository.delete(customer);
		verify(customerRepository, times(1)).delete(customer);
	}

}
