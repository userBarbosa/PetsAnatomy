package dao.impl;

import java.util.Random;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import org.bson.types.ObjectId;

import dao.interfaces.ParametersDAO;
import utils.MongoConnect;

public class ParametersDAOImpl implements ParametersDAO {

  MongoCollection<Document> parameters;

  private void getCollection() {
    parameters = MongoConnect.database.getCollection("parameters");
  }

  @Override
  public String getRandomPhrase() {
    getCollection();
    int value = new Random().nextInt(getLastIndex());
    Document phrase = parameters
    .find(new Document("index", value))
    .first();
    if (phrase != null) {
      return phrase.getString("phrase");
    }
    return "Onde cuidar significa mais!";
  }

  @Override
  public boolean insertPhrase(String phrase) {
    getCollection();
    try {
      parameters.insertOne(
        new Document("_id", new ObjectId())
          .append("index", getLastIndex() + 1)
          .append("phrase", phrase)
      );
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  private int getLastIndex() {
    return parameters
    .find()
    .sort(new BasicDBObject("index", -1))
      .limit(1)
      .first()
      .getInteger("index");
  }
}
