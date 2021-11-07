package boundary;

import control.LoginControl;
import java.io.FileInputStream;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LoginBoundary extends Application {

  private TextField tfUsername = new TextField();
  private PasswordField pfPassword = new PasswordField();
  private TextField tfPassword = new TextField();

  private Button btnLogin = new Button("Login");
  private Button btnSignUp = new Button("Cadastrar-se");
  private Label lblTitle = new Label("PetsAnatomy");

  private static LoginControl control = new LoginControl();

  @Override
  public void start(Stage stage) throws Exception {
    AnchorPane mainPane = new AnchorPane();
    AnchorPane formPane = new AnchorPane();
    AnchorPane designPane = new AnchorPane();

    binding();

    Font fontTf = Font.loadFont("file:resources/fonts/Poppins-Regular.ttf", 14);
    Font fontTitle = Font.loadFont("file:resources/fonts/Poppins-Bold.ttf", 96);
    Font fontBtn = Font.loadFont("file:resources/fonts/Poppins-Bold.ttf", 14);
    Font fontTxt = Font.loadFont(
      "file:resources/fonts/Poppins-Regular.ttf",
      12
    );

    formPane.setPrefHeight(768.0);
    formPane.setPrefWidth(300.0);
    formPane.setStyle("-fx-background-color: #ffffff;");

    FileInputStream inputLogo = new FileInputStream(
      "@../../../PetsAnatomy/src/assets/logo.png"
    );
    Image imageLogo = new Image(inputLogo);
    ImageView imageViewlogo = new ImageView(imageLogo);
    imageViewlogo.setLayoutX(40.0);
    imageViewlogo.setLayoutY(126.0);
    imageViewlogo.setFitHeight(221.0);
    imageViewlogo.setFitWidth(241.0);
    imageViewlogo.setPreserveRatio(true);

    tfUsername.setLayoutX(50.0);
    tfUsername.setLayoutY(396.0);
    tfUsername.setMinSize(240.0, 30.0);
    tfUsername.setStyle("-fx-border-color: #000E44;");
    tfUsername.setFont(fontTf);

    FileInputStream inputUser = new FileInputStream(
      "@../../../PetsAnatomy/src/assets/user.png"
    );
    Image imageUser = new Image(inputUser);
    ImageView imageViewUser = new ImageView(imageUser);
    imageViewUser.setLayoutX(10.0);
    imageViewUser.setLayoutY(396.0);
    imageViewUser.setFitHeight(43.0);
    imageViewUser.setFitWidth(32.0);
    imageViewUser.setPreserveRatio(true);

    pfPassword.setLayoutX(50.0);
    pfPassword.setLayoutY(454.0);
    pfPassword.setMinSize(240.0, 30.0);
    pfPassword.setStyle("-fx-border-color: #000E44;");

    FileInputStream inputPassword = new FileInputStream(
      "@../../../PetsAnatomy/src/assets/lock.png"
    );
    Image imagePassword = new Image(inputPassword);
    ImageView imageViewPassword = new ImageView(imagePassword);
    imageViewPassword.setLayoutX(10.0);
    imageViewPassword.setLayoutY(454.0);
    imageViewPassword.setFitHeight(43.0);
    imageViewPassword.setFitWidth(32.0);
    imageViewPassword.setPreserveRatio(true);

    CheckBox cbPassword = new CheckBox("Mostrar/Esconder Senha");
    cbPassword.setFont(fontTxt);
    cbPassword.setLayoutX(50.0);
    cbPassword.setLayoutY(494.0);

    tfPassword.setLayoutX(50.0);
    tfPassword.setLayoutY(454.0);
    tfPassword.setMinSize(240.0, 30.0);
    tfPassword.setFont(fontTf);
    tfPassword.managedProperty().bind(cbPassword.selectedProperty());
    tfPassword.visibleProperty().bind(cbPassword.selectedProperty());

    pfPassword.managedProperty().bind(cbPassword.selectedProperty().not());
    pfPassword.visibleProperty().bind(cbPassword.selectedProperty().not());

    tfPassword.textProperty().bindBidirectional(pfPassword.textProperty());

    btnLogin.setOnAction(
      e -> {
        control.login();
        tfPassword.setText("");
      }
    );
    btnLogin.setLayoutX(10.0);
    btnLogin.setLayoutY(550.0);
    btnLogin.setMinSize(280.0, 40.0);
    btnLogin.setFont(fontBtn);
    btnLogin.setStyle("-fx-background-color: #000E44; -fx-text-fill: white; -fx-cursor: hand;");

    btnSignUp.setOnAction(
      e -> {
        control.signUp();
      }
    );
    btnSignUp.setLayoutX(97.0);
    btnSignUp.setLayoutY(590.0);
    btnSignUp.setMinSize(100.0, 30.0);
    btnSignUp.setUnderline(true);
    btnSignUp.setFont(fontTxt);
    btnSignUp.setStyle("-fx-background-color: none; -fx-border-color: none; -fx-cursor: hand;");

    formPane
      .getChildren()
      .addAll(
        imageViewlogo,
        tfUsername,
        imageViewUser,
        pfPassword,
        tfPassword,
        imageViewPassword,
        btnLogin,
        btnSignUp,
        cbPassword
      );

    designPane.setPrefHeight(768.0);
    designPane.setPrefWidth(1066.0);
    designPane.setStyle("-fx-background-color: #000E44;");
    designPane.setLayoutX(300.0);

    FileInputStream inputEstetoscopio = new FileInputStream(
      "@../../../PetsAnatomy/src/assets/estetoscopio.png"
    );
    Image imageEstetoscopio = new Image(inputEstetoscopio);
    ImageView imageViewEstetoscopio = new ImageView(imageEstetoscopio);
    imageViewEstetoscopio.setLayoutX(367.0);
    imageViewEstetoscopio.setFitHeight(284.0);
    imageViewEstetoscopio.setFitWidth(333.0);
    imageViewEstetoscopio.setPreserveRatio(true);

    FileInputStream inputPata = new FileInputStream(
      "@../../../PetsAnatomy/src/assets/pata.png"
    );
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

    mainPane.setLeftAnchor(formPane, 0.0);
    mainPane.setRightAnchor(designPane, 0.0);
    mainPane.getChildren().addAll(formPane, designPane);
    mainPane.setPrefHeight(768.0);
    mainPane.setPrefWidth(1366.0);

    Scene scene = new Scene(mainPane, 1366, 768);
    stage.setResizable(false);
    stage.setScene(scene);
    stage.show();
    stage.setTitle("Clínica Veterinária PetsAnatomy");
  }

  private void binding() {
    Bindings.bindBidirectional(
      tfUsername.textProperty(),
      control.usernameProperty()
    );
    Bindings.bindBidirectional(
      pfPassword.textProperty(),
      control.passwordProperty()
    );
  }

  public static void main(String[] args) {
    launch(args);
  }
}
