package com.cwt.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cwt.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
