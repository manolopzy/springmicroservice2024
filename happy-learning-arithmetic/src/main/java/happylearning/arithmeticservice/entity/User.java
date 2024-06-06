package happylearning.arithmeticservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
@NoArgsConstructor
@Data
public final class User {
	private String id;
	@NonNull
	private String alias;
	
}
