package happylearning.arithmeticservice.entity;


import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * var data = { user: { alias: userAlias }, multiplication: { factorA: a,
 * factorB: b }, resultAttempt: attempt };
 * 
 * @author tanku
 *
 */
@Document//(collection = "arithmeticAttempts")
@Data
public class ArithmeticAttempt {
	private String id;
	private User user;
	private ArithmeticOperation arithmetic;
	// this is the user's calculation result, could be either wrong or right
	private int result;
	private Date createdAt;
	private boolean correct;

	// Empty constructor for JSON (de)serialization
	ArithmeticAttempt() {
		user = null;
		arithmetic = null;
		result = -1;
		correct = false;
		this.createdAt = new Date();
	}

	public ArithmeticAttempt(User user, ArithmeticOperation arithmetic, int resultAttempt, boolean correct) {
		this.user = user;
		this.arithmetic = arithmetic;
		this.result = resultAttempt;
		this.correct = correct;
		this.createdAt = new Date();
	}
}
