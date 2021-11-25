package boundary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class ImageBoundary implements StrategyBoundary {

  private Label lblTitle = new Label("PetsAnatomy");
  Font fontTitle = Font.loadFont("file:resources/fonts/Poppins-Bold.ttf", 96);

  @Override
  public Pane generateBoundaryStrategy() {
    AnchorPane designPane = new AnchorPane();

    designPane.setPrefHeight(768.0);
    designPane.setPrefWidth(1066.0);
    designPane.setStyle("-fx-background-color: #000E44;");
    designPane.setLayoutX(300.0);

    FileInputStream inputEstetoscopio = null;
    FileInputStream inputPata = null;

    try {
      inputEstetoscopio =
        new FileInputStream(
          "@../../../PetsAnatomy/src/assets/estetoscopio.png"
        );
      inputPata =
        new FileInputStream("@../../../PetsAnatomy/src/assets/pata.png");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    Image imageEstetoscopio = new Image(inputEstetoscopio);
    ImageView imageViewEstetoscopio = new ImageView(imageEstetoscopio);
    imageViewEstetoscopio.setLayoutX(367.0);
    imageViewEstetoscopio.setFitHeight(284.0);
    imageViewEstetoscopio.setFitWidth(333.0);
    imageViewEstetoscopio.setPreserveRatio(true);

    Image imagePata = new Image(inputPata);
    ImageView imageViewPata = new ImageView(imagePata);
    imageViewPata.setLayoutX(409.0);
    imageViewPata.setLayoutY(547.0);
    imageViewPata.setFitHeight(221.0);
    imageViewPata.setFitWidth(247.0);
    imageViewPata.setPreserveRatio(true);

    lblTitle.setLayoutX(221.0);
    lblTitle.setLayoutY(337.0);
    lblTitle.setMinSize(624.0, 119.0);
    lblTitle.setFont(fontTitle);
    lblTitle.setStyle("-fx-text-fill: white;");

    designPane
      .getChildren()
      .addAll(imageViewEstetoscopio, imageViewPata, lblTitle);
    return designPane;
  }
}
