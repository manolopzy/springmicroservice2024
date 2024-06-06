package happylearning.arithmeticservice.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

/**
 * Spring boot will load the mongo database configuration from this class
 * automatically Alternatively, one can use xml based configuration, then import
 * it with annotation //@ImportResource("classpath:mongodbConfig.xml")
 * 
 * @author tanku
 *
 */
@Configuration
public class MongodbConfig extends AbstractMongoClientConfiguration {

//	@Value("${db.name}")
//	public String DB_NAME;// = "test";
//	@Value("${db.url}")
//	private String DB_URL;// = "mongodb://localhost:27017/" + DB_NAME;
	@Value("${spring.data.mongodb.uri}")
	private String uri;

	@Value("${spring.data.mongodb.database}")
	private String db;

	@Override
	protected String getDatabaseName() {
		return db;
	}

	@Override
	public MongoClient mongoClient() {
		final ConnectionString connectionString = new ConnectionString(uri);
		final MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
				.applyConnectionString(connectionString).build();
		return MongoClients.create(mongoClientSettings);
	}

	@Override
	public Collection<String> getMappingBasePackages() {
		return Collections.singleton("com.worldexplorer.arithmetic.entity");
	}

}
