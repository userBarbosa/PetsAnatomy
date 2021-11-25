package boundary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class DashboardBoundary implements StrategyBoundary {

  private Label lblDailyPhrase = new Label("Onde cuidar significa mais!");

  @Override
  public Pane generateBoundaryStrategy() {
    AnchorPane imagePane = new AnchorPane();

    Font fontLblPhrase = Font.loadFont(
      "file:resources/fonts/Poppins-Bold.ttf",
      70
    );

    imagePane.setPrefHeight(768.0);
    imagePane.setPrefWidth(1066.0);
    imagePane.setLayoutX(300);

    FileInputStream inputDash;
    Image imageDash;
    ImageView imageViewDash = null;
    try {
      inputDash =
        new FileInputStream(
          "@../../../PetsAnatomy/src/assets/pet-dashboard.jpg"
        );
      imageDash = new Image(inputDash);
      imageViewDash = new ImageView(imageDash);
      imageViewDash.setFitHeight(768.0);
      imageViewDash.setFitWidth(1066.0);
      imageViewDash.setPreserveRatio(true);
      imageViewDash.setOpacity(0.80);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    lblDailyPhrase.setLayoutX(175);
    lblDailyPhrase.setLayoutY(275);
    lblDailyPhrase.setFont(fontLblPhrase);
    lblDailyPhrase.setMaxWidth(700);
    lblDailyPhrase.setWrapText(true);
    lblDailyPhrase.setStyle(
      "-fx-text-alignment: center; -fx-text-fill: white;"
    );

    imagePane.getChildren().add(imageViewDash);
    imagePane.getChildren().add(lblDailyPhrase);

    return imagePane;
  }
}
