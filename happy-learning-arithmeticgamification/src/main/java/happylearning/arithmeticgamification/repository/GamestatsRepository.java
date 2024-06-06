package happylearning.arithmeticgamification.repository;

import org.springframework.data.repository.CrudRepository;

import happylearning.arithmeticgamification.entity.GameStats;

public interface GamestatsRepository extends CrudRepository<GameStats, String>{
}
