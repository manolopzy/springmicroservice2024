package happylearning.arithmeticservice.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import happylearning.arithmeticservice.entity.ArithmeticAttempt;
import happylearning.arithmeticservice.entity.ArithmeticOperation;
import happylearning.arithmeticservice.entity.QArithmeticAttempt;
import happylearning.arithmeticservice.event.ArithmeticAttemptEvent;
import happylearning.arithmeticservice.event.RabbitEventDispatcher;
import happylearning.arithmeticservice.repository.ArithmeticAttemptRepository;
import happylearning.arithmeticservice.service.ArithmeticService;
import happylearning.arithmeticservice.service.RandomGeneratorService;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class ArithmeticServiceImpl implements ArithmeticService {
	
	@Autowired
	private ArithmeticAttemptRepository arithmeticAttemptRepository;
	
	@Autowired
	private RabbitEventDispatcher eventDispatcher;
	
	private RandomGeneratorService randomGeneratorService;

//	private static final Map<String, String> operators = new HashMap<>();
//	static {
//		operators.put("1", "+");
//		operators.put("2", "-");
//		operators.put("3", "×");
//		operators.put("4", "÷");
//	}
	public ArithmeticServiceImpl(RandomGeneratorService randomGeneratorService) {
		this.randomGeneratorService = randomGeneratorService;
	}

	@Override
	public ArithmeticOperation createRandomExpression(String operator) {
		int factorA = randomGeneratorService.randomMultiplicationFactor();
		int factorB = randomGeneratorService.randomMultiplicationFactor();
		return new ArithmeticOperation(factorA, factorB, operator);
	}

	@Override
	public boolean checkAttempt(final ArithmeticAttempt attempt) {
//		ArithmeticAttempt dbAttempt = repository.save(attempt);
//		log.info("dbObj = " + dbAttempt);
		// Check if the user already exists for that alias

		// Check if the attempt is correct
		boolean isCorrect = attempt.getArithmetic().isCorrect(attempt.getResult());
		
		ArithmeticAttempt checkedAttempt = new ArithmeticAttempt(attempt.getUser() ,attempt.getArithmetic(), attempt.getResult(), isCorrect);
		
		checkedAttempt = arithmeticAttemptRepository.save(checkedAttempt);
		log.info("checkedAttempt = " + checkedAttempt.toString());
		ArithmeticAttemptEvent objToSend = new ArithmeticAttemptEvent(checkedAttempt.getId(), attempt.getUser().getAlias(), checkedAttempt.isCorrect());
		log.info("objToSend = " + objToSend.toString());
		eventDispatcher.send(objToSend);
		
		return isCorrect;
	}

	@Override
	public ArithmeticAttempt getArithmeticAttemptById(String id) {
		Optional<ArithmeticAttempt> result = arithmeticAttemptRepository.findById(id);
		return result.isEmpty() ? null : result.get();
	}

	@Override
	public Iterable<ArithmeticAttempt> getArithmeticAttempts(String alias) {
		QArithmeticAttempt qQuery = new QArithmeticAttempt("arithmeticAttempt");
		Iterable<ArithmeticAttempt> result = arithmeticAttemptRepository.findAll(qQuery.user.alias.eq(alias));
		return result;
	}
}
