package br.com.benefrancis.order.config;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	@Bean
	public FanoutExchange exchange() {
		return new FanoutExchange("orders.v1.order-created");
	}

	@Bean
	public RabbitAdmin rabbitAdmin(ConnectionFactory factory) {
		return new RabbitAdmin(factory);
	}

	@Bean
	public ApplicationListener<ApplicationReadyEvent> applicationReadEventApplicationListener(RabbitAdmin rabbitAdmin) {
		return event -> rabbitAdmin.initialize();
	}

	@Bean
	public Jackson2JsonMessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connFactory, Jackson2JsonMessageConverter messageConverter) {
		RabbitTemplate template = new RabbitTemplate(connFactory);
		template.setMessageConverter(messageConverter);
		return template;
	}
}
