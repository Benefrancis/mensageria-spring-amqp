package br.com.benefrancis.cashback.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import br.com.benefrancis.cashback.request.OrderCreatedEvent;

@Component
public class OrderCreatedListener {
	@RabbitListener(queues = "orders.v1.order-created.generate-cashback")
	public void onOrderCreated(OrderCreatedEvent event) {
		System.out.println("ORDER RECEBIDO: " + event);
	}
}
