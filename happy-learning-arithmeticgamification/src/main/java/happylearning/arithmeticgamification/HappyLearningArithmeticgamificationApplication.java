package happylearning.arithmeticgamification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class HappyLearningArithmeticgamificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(HappyLearningArithmeticgamificationApplication.class, args);
	}

	@PostConstruct
	public void init() {
		
	}
}
