package happylearning.arithmeticservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import happylearning.arithmeticservice.entity.ArithmeticOperation;
import happylearning.arithmeticservice.entity.ArithmeticAttempt;
import happylearning.arithmeticservice.service.ArithmeticService;
import lombok.extern.slf4j.Slf4j;
/**
 * One can set up different levels of cross origin
 * @author tanku
 *
 */
@Slf4j
@RestController
@RequestMapping("/arithmetic")
public class ArithmeticController {
	
	private final ArithmeticService arithmeticService;

	public ArithmeticController(final ArithmeticService arithmeticService) {
		this.arithmeticService = arithmeticService;
	}

	//https://www.baeldung.com/spring-request-param
	//http://localhost:8080/arithmetic/random?operator=1
	//@CrossOrigin(origins = "http://localhost:9090")
	@GetMapping("/random")
	ArithmeticOperation getRandomExpression(@RequestParam(name = "operator") String operator) {
		log.info("request a random arithmetic expression = " + operator);
		return arithmeticService.createRandomExpression(operator);
	}
	@PostMapping("/attempt")
	ResponseEntity<ArithmeticAttempt> postResult(@RequestBody ArithmeticAttempt attempt) {
		log.info("user attempt = " + attempt.toString());
		if(attempt.getUser() == null || attempt.getUser().getAlias().isEmpty()) {
			return ResponseEntity.badRequest().body(null);
		}
		
			
		boolean isCorrect = arithmeticService.checkAttempt(attempt);
        ArithmeticAttempt attemptCopy = new ArithmeticAttempt(
        		attempt.getUser(),
        		attempt.getArithmetic(),
        		attempt.getResult(),
        		isCorrect
        );
        return ResponseEntity.ok(attemptCopy);
	}
    @GetMapping("/attempts")
    ResponseEntity<Iterable<ArithmeticAttempt>> getStatistics(@RequestParam("alias") String alias) {
        return ResponseEntity.ok(
        		arithmeticService.getArithmeticAttempts(alias)
        );
    }
    @GetMapping("/{id}")
    ResponseEntity<ArithmeticAttempt> getAttemptResultById(final @PathVariable("id") String id) {
    	log.info("ask information with attempt result id = " + id);
        return ResponseEntity.ok(arithmeticService.getArithmeticAttemptById(id));
    }
	
}
