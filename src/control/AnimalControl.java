package control;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;

import dao.AnimalDao;
import entity.*;
import entity.Animal;
import org.bson.Document;

public class AnimalControl {

  private Animal animal;
  private Client client;
  private AnimalDao aDao;

  public AnimalControl() {
    // Animal animal, Client client
    // this.animal = animal;
    // this.client = client;
    // this.aDao = aDao;
    aDao = new AnimalDao();
  }

  public static void main(String[] args) {
    AnimalControl ac = new AnimalControl();
    ac.read();
  }

  void create(Animal animal) {
    aDao.insert(animal);
  }

  void read() {
      
    /* MongoCursor<Document> result = aDao.search("name", "Keanu");
    if (result == null) {
        System.out.println("Não encontrado.");
    }
    else {
        System.out.println(result);
    } */
  }

  void update() {}

  void delete() {}
}
