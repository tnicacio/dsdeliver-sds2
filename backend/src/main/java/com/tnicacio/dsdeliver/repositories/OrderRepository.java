package com.tnicacio.dsdeliver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tnicacio.dsdeliver.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
