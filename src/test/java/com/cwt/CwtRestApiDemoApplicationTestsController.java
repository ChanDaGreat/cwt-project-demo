package com.cwt;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.validation.BindingResult;

import com.cwt.controller.CustomerController;
import com.cwt.entities.Customer;
import com.cwt.entities.Order;
import com.cwt.persistence.CustomerRepository;
import com.cwt.service.CustomerService;
import com.cwt.service.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class CwtRestApiDemoApplicationTestsController {

	@InjectMocks
	CustomerController customerController;
	@Mock
	CustomerRepository customerRepository;
	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	@DisplayName("Save Customer record test Controller")
	void saveCustomerController() throws Exception {
		Set<Order> set = new HashSet<Order>();
		mockMvc.perform(MockMvcRequestBuilders.post("/customers/createCustomerController")
				.content(
						asJsonString(new Customer((long) 2, "Juan", "Pablo", "newEMailvalid@gmail.com", "Makati", set)))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(MockMvcResultMatchers.jsonPath("$.custId").exists());

	}

	public static String asJsonString(final Object obj) {
		System.err.println(obj);
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	@DisplayName("Update Customer record test Controller")
	void updateCustomerRecord() throws Exception {
		Set<Order> set = new HashSet<Order>();
		mockMvc.perform(MockMvcRequestBuilders.put("/customers/update/{id}", 2)
				.content(
						asJsonString(new Customer((long) 2, "Juan", "Pablo", "newEMailvalid@gmail.com", "Makati", set)))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Juan"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Pablo"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.email").value("newEMailvalid@gmail.com"));

	}

	@Test
	void deleteCustomerRecord() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/customers/deleteCustomerController/{id}", 2))
				.andExpect(status().isAccepted());

	}

}
