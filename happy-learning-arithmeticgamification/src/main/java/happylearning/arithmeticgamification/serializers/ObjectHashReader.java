package happylearning.arithmeticgamification.serializers;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;

import happylearning.arithmeticgamification.entity.GameStats;

@ReadingConverter
public class ObjectHashReader implements Converter<byte[], GameStats> {
	private final Jackson2JsonRedisSerializer<GameStats> serializer;

	  public ObjectHashReader() {

	    serializer = new Jackson2JsonRedisSerializer<GameStats>(new ObjectMapper(), GameStats.class);
	  }

	  @Override
	  public GameStats convert(byte[] value) {
	    return serializer.deserialize(value);
	  }
}
