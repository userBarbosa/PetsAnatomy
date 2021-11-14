package dao.impl;

import com.mongodb.client.MongoCollection;
import dao.interfaces.ParametersDAO;
import java.time.LocalDate;
import org.bson.Document;
import org.bson.types.ObjectId;
import utils.MongoConnect;

public class ParametersDAOImpl implements ParametersDAO {

  MongoCollection<Document> parameters;

  void getCollection() {
    parameters = MongoConnect.database.getCollection("parameters");
  }

  public String dailyPhrase() {
    getCollection();
    Document phrase = parameters
      .find(new Document("day", LocalDate.now().getDayOfMonth()))
      .first();
    return phrase.get("phrase").toString();
  }

  public boolean insertDailyPhrase(String phrase, int day) {
    getCollection();
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
