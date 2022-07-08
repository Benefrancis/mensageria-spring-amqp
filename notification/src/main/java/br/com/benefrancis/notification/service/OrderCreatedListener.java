package br.com.benefrancis.notification.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import br.com.benefrancis.notification.request.OrderCreatedEvent;

@Component
public class OrderCreatedListener {
	@RabbitListener(queues = "orders.v1.order-created.send-notification")
	public void onOrderCreated(OrderCreatedEvent event) {
		System.out.println("ORDER RECEBIDO: " + event);
	}
}
