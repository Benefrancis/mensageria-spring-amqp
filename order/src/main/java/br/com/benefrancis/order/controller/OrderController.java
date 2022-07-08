package br.com.benefrancis.order.controller;

import java.util.Collection;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.benefrancis.order.entity.Order;
import br.com.benefrancis.order.repository.OrderRepository;
import br.com.benefrancis.order.response.OrderCreatedEvent;

@RestController
@RequestMapping(value = "/v1/orders")
public class OrderController {

	@Autowired
	private OrderRepository orders;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@PostMapping
	public ResponseEntity<Order> create(@RequestBody Order order) {
		orders.save(order);
		OrderCreatedEvent event = new OrderCreatedEvent(order.getId(), order.getValue());
		String exchange = "orders.v1.order-created";
		String routingKey = "";
		rabbitTemplate.convertAndSend(exchange, routingKey, event);

		// @formatter:off
 		return ResponseEntity.created(
				ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(order.getId()).toUri())
 				.body(order);
 		// @formatter:on

	}

	@GetMapping
	public Collection<Order> list() {
		return orders.findAll();
	}

	@GetMapping("{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id) {

		Order resp = orders.findById(id).orElse(null);

		if (resp == null) {
			return ResponseEntity.notFound().build();

		} else {
			return ResponseEntity.ok(resp);
		}

	}

	@PutMapping("{id}/pay")
	public Order pay(@PathVariable Long id) {
		Order order = orders.findById(id).orElseThrow();
		order.markAsPaid();
		return orders.save(order);
	}

}