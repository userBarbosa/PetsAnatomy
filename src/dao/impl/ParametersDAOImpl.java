package dao.impl;

import com.mongodb.client.MongoCollection;
import dao.interfaces.ParametersDAO;
import java.time.LocalDate;
import org.bson.Document;
import org.bson.types.ObjectId;
import utils.MongoConnect;

public class ParametersDAOImpl implements ParametersDAO {

  MongoCollection<Document> parameters;

  private void getCollection() {
    parameters = MongoConnect.database.getCollection("parameters");
  }

  @Override
  public String dailyPhrase() {
    getCollection();
    Document phrase = parameters
      .find(new Document("day", LocalDate.now().getDayOfMonth()))
      .first();
    return phrase.get("phrase").toString();
  }

  @Override
  public boolean insertPhrase(String phrase, int day) {
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
