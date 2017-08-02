package be.solidsyntax.reactive.server;

import com.mongodb.MongoClient;
import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.PostConstruct;
import java.io.IOException;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class SpringReactiveExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringReactiveExampleApplication.class, args);
	}


	@Bean
	public MongoTemplate mongoTemplate() throws IOException {
		EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
		mongo.setBindIp("localhost");
		MongoClient mongoClient = mongo.getObject();
		return new MongoTemplate(mongoClient, "restaurants");
	}

}
