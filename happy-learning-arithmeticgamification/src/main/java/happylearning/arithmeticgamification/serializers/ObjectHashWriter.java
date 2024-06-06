package happylearning.arithmeticgamification.serializers;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;

import happylearning.arithmeticgamification.entity.GameStats;

@WritingConverter
public class ObjectHashWriter implements Converter<GameStats, byte[]> {

	private final Jackson2JsonRedisSerializer<GameStats> serializer;

	public ObjectHashWriter() {
		serializer = new Jackson2JsonRedisSerializer<GameStats>(new ObjectMapper(), GameStats.class);
	}

	@Override
	public byte[] convert(GameStats value) {
		return serializer.serialize(value);
	}

}
