package dao;

import java.time.LocalDate;

import com.mongodb.client.MongoCollection;

import org.bson.Document;
import org.bson.types.ObjectId;

import utils.MongoConnect;

public class ParametersDAOImpl implements ParametersDAO {

  MongoCollection<Document> parameters;

  public ParametersDAOImpl() {
		connection();
	}

  void connection() {
    MongoConnect mc = new MongoConnect();
    mc.connection();
    parameters = mc.database.getCollection("parameters");
  }

  public String dailyPhrase() {
    Document phrase = parameters
      .find(new Document("day", LocalDate.now().getDayOfMonth()))
      .first();
    return phrase.get("phrase").toString();
  }

  public boolean insertDailyPhrase(String phrase, int day) {
		try {
			parameters.insertOne(
				new Document("_id", new ObjectId())
					.append("day", day)
					.append("phrase", phrase)
			);
		} catch (Exception e) {
			return false;
		}
		return true;
  }
}
