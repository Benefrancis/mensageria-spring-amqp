package br.com.benefrancis.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.benefrancis.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}