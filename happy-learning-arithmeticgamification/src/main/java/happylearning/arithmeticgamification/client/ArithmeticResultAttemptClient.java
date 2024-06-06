package happylearning.arithmeticgamification.client;

import happylearning.arithmeticgamification.client.dto.ArithmeticResultAttempt;

/**
 * Esta interfaz define metodos para comunicar con otros micro servicios
 * 
 */
public interface ArithmeticResultAttemptClient {

    ArithmeticResultAttempt retrieveArithmeticResultAttemptbyId(final String arithmeticId);

}
