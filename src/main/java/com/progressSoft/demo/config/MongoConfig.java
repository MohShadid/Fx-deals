package com.progressSoft.demo.config;


import java.util.Arrays;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;




@Configuration
@EnableMongoRepositories("com.progressSoft.demo.model.mongo")
public class MongoConfig extends AbstractMongoClientConfiguration {

	private MongoProperties appSetup;

	public MongoConfig(@Autowired MongoProperties appSetup) {
		this.appSetup = appSetup;
		
	}

	@Override
	protected String getDatabaseName() {
		return appSetup.getMongoDb();
	}

	@Override
	public MongoClient mongoClient() {

		MongoClient client = MongoClients.create( //
				MongoClientSettings.builder() //
						.applyToClusterSettings(builder -> builder.hosts(Arrays.asList(//
								new ServerAddress(appSetup.getMongoUri(), appSetup.getMongoPort())))) //					
						.build());

		return client;
	}

	@Override
	protected String getMappingBasePackage() {
		return "com.progressSoft.demo";
	}

	
}
