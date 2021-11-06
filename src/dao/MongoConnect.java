package dao;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import utils.AuthMDB;

public class MongoConnect { 
	AuthMDB auth = new AuthMDB();

	ConnectionString connectionString = new ConnectionString(
			"mongodb+srv://" + 
					auth.getAuth() +
					"@clusterpetsanatomy.pxsfd.mongodb.net/?retryWrites=true&w=majority"
			);

	MongoClientSettings settings = MongoClientSettings
			.builder()
			.applyConnectionString(connectionString)
			.build();

	MongoClient mongoClient = MongoClients.create(settings);
	MongoDatabase database = mongoClient.getDatabase("PetsAnatomy");
	
}