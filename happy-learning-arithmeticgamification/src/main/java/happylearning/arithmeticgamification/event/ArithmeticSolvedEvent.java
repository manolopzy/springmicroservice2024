package happylearning.arithmeticgamification.event;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Evento recibido del micro servicio de arithmetic cuando 
 * una expresion aritmetic ha sedo resuelto
 */
@SuppressWarnings("serial")
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
class ArithmeticSolvedEvent implements Serializable {

	private final String arithmeticAttemptId;
	private final String userId;
	private final boolean correct;

	public ArithmeticSolvedEvent() {
		this.arithmeticAttemptId = null;
		this.userId = null;
		this.correct = false;
	}
}
