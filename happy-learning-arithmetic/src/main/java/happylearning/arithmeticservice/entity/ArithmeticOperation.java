package happylearning.arithmeticservice.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import lombok.Data;

/**
 * Alt+Shift+S 
 * for getters and setters generation
 * 
 * Alt+Shift+R
 * Place your cursor on the field name then
 * press Alt+Shift+R to rename not only the field name
 * but also its corresponding getters 
 * and setters.
 * 
 * The other way of generating getters and setters:
 * Right click the mouse button, choose "Source/Generate ..."
 * 
 * @author tanku
 *
 */
@Data
public final class ArithmeticOperation {

	private int factorA;
	private  int factorB;
	private String operator;
	private byte level;
	private List<String> options;
	public ArithmeticOperation() {
	}
	public ArithmeticOperation(int factorA, int factorB, String operator) {
		this.operator = operator;
		this.factorA = factorA;
		this.factorB = factorB;
		this.level = 3;
		Random random = new Random();
		int result = factorA + factorB;
		this.options = new ArrayList<String>(4);
		options.add((result - random.nextInt(-5, 5)) + "");
		options.add((result - random.nextInt(-5, 5)) + "");
		options.add((result - random.nextInt(-5, 5)) + "");
		options.add(result + "");
		Collections.shuffle(options);
	}
	
	@Override
	public String toString() {
		return "arithmetic [factorA=" + factorA + ", operator=" +  operator + ", factorB=" + factorB  + "]";
	}
	
	public boolean isCorrect(int attemptResult) {
		switch (operator) {
		case "+": {
			if(attemptResult == factorA + factorB) {
				return true;
			}
			else {
				return false;
			}
		}
		case "-": {
			if(attemptResult == factorA - factorB) {
				return true;
			}
			else {
				return false;
			}
		}
		case "*": {
			if(attemptResult == factorA * factorB) {
				return true;
			}
			else {
				return false;
			}
		}
		case "/": {
			if(attemptResult == factorA / factorB) {
				return true;
			}
			else {
				return false;
			}
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + operator);
		}
	}
	
}
