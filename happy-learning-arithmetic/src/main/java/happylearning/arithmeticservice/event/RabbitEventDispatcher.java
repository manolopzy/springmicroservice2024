package happylearning.arithmeticservice.event;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
/**
 * This is the dispatcher for sending events to the event bus
 * 
 * @author tanku
 *
 */
@Component
public class RabbitEventDispatcher {
	
	private RabbitTemplate rabbitTemplate;
	// The exchange used to send anything related to our Arithmetic topic
	private String arithmeticExchange;
	// The routing key to use to send this particular event
	private String arithmeticAttemptRoutingKey;

	
	public RabbitEventDispatcher(final RabbitTemplate rabbitTemplate,
			@Value("${arithmetic.exchange}") final String arithmeticExchange,
			@Value("${arithmetic.attempt.key}") final String arithmeticAttemptRoutingKey) {
		this.rabbitTemplate = rabbitTemplate;
		this.arithmeticExchange = arithmeticExchange;
		this.arithmeticAttemptRoutingKey = arithmeticAttemptRoutingKey;
	}

	public void send(final ArithmeticAttemptEvent arithmeticSolvedEvent) {
		rabbitTemplate.convertAndSend(arithmeticExchange, arithmeticAttemptRoutingKey,
				arithmeticSolvedEvent);
	}
}
