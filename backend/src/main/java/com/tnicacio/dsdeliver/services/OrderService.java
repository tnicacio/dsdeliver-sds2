package com.tnicacio.dsdeliver.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tnicacio.dsdeliver.dto.OrderDTO;
import com.tnicacio.dsdeliver.dto.ProductDTO;
import com.tnicacio.dsdeliver.entities.Order;
import com.tnicacio.dsdeliver.entities.OrderStatus;
import com.tnicacio.dsdeliver.entities.Product;
import com.tnicacio.dsdeliver.repositories.OrderRepository;
import com.tnicacio.dsdeliver.repositories.ProductRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Transactional(readOnly = true)
	public List<OrderDTO> findAll(){
		List<Order> list = repository.findOrdersWithProducts();
		return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional
	public OrderDTO insert(OrderDTO orderDTO){
		Order order = new Order(null, 
								orderDTO.getAddress(), 
								orderDTO.getLatitude(),
								orderDTO.getLongitude(),
								Instant.now(),
								OrderStatus.PENDING);
		
		for (ProductDTO p : orderDTO.getProducts()) {
			Product product = productRepository.getOne(p.getId());
			order.getProducts().add(product);
		}
		
		order = repository.save(order);
		return new OrderDTO(order);
	}
}