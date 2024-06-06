package happylearning.arithmeticservice.service;

import happylearning.arithmeticservice.entity.ArithmeticOperation;
import happylearning.arithmeticservice.entity.ArithmeticAttempt;

public interface ArithmeticService {

	/**
	 * Create an arithmetic expression object with 
	 * specified operator 
	 * @return
	 */
	ArithmeticOperation createRandomExpression(String operator);
	
	/**
	 * this is the service deals with user's attempt requests
	* @return true if the attempt matches the result of the
	* arithmetic expression, false otherwise.
	 * @throws Exception 
	*/
	boolean checkAttempt(final ArithmeticAttempt attempt);
	/**
	 * get the arithmetic attempt data with its id
	 * @param id
	 * @return
	 */
	ArithmeticAttempt getArithmeticAttemptById(String id);
	/**
	 * Return a user's attempts by name
	 * @param alias
	 * @return
	 */
	Iterable<ArithmeticAttempt> getArithmeticAttempts(String alias);
}
