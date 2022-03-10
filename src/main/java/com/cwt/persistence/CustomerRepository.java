package com.cwt.persistence;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cwt.entities.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	List<Customer> findAllByLocation(String location);
}
