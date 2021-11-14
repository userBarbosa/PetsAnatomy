package utils;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnect {

  AuthMDB auth = new AuthMDB();
  DBLogger lgr = new DBLogger();
  public static MongoDatabase database;

  public void connection() {
    lgr.setupLogger();
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
    database = mongoClient.getDatabase("PetsAnatomy");
  }
}
