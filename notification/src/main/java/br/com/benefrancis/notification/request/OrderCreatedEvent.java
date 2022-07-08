package br.com.benefrancis.notification.request;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreatedEvent {
	private Long id;
	private BigDecimal value = BigDecimal.ZERO;
}
