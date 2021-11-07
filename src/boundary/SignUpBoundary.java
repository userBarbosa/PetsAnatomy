package boundary;

import control.SignUpControl;
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

public class SignUpBoundary extends Application {

  private TextField tfFullname = new TextField();
  private TextField tfUsername = new TextField();
  private TextField tfEmail = new TextField();
  private PasswordField pfPassword = new PasswordField();
  private TextField tfPassword = new TextField();

  private Button btnSignUp = new Button("Cadastrar");
  private Button btnLogin = new Button("Já é cadastrado? Login");
  private Label lblTitle = new Label("PetsAnatomy");

  private static SignUpControl control = new SignUpControl();

  @Override
  public void start(Stage stage) throws Exception {
    AnchorPane mainPane = new AnchorPane();
    AnchorPane formPane = new AnchorPane();
    AnchorPane designPane = new AnchorPane();

    binding();

    Font fontTextField = Font.loadFont(
      "file:resources/fonts/Poppins-Regular.ttf",
      14
    );
    Font fontTitle = Font.loadFont("file:resources/fonts/Poppins-Bold.ttf", 96);
    Font fontBtn = Font.loadFont("file:resources/fonts/Poppins-Bold.ttf", 14);
    Font fontSmallText = Font.loadFont(
      "file:resources/fonts/Poppins-Regular.ttf",
      12
    );

    formPane.setPrefHeight(768.0);
    formPane.setPrefWidth(300.0);
    formPane.setStyle("-fx-background-color: #ffffff;");
    formPane.setLayoutX(300.0);

    FileInputStream inputLogo = new FileInputStream(
      "@../../../PetsAnatomy/src/assets/logo.png"
    );
    Image imageLogo = new Image(inputLogo);
    ImageView imageViewlogo = new ImageView(imageLogo);
    imageViewlogo.setLayoutX(40.0);
    imageViewlogo.setLayoutY(70.0);
    imageViewlogo.setFitHeight(221.0);
    imageViewlogo.setFitWidth(241.0);
    imageViewlogo.setPreserveRatio(true);

    tfFullname.setLayoutX(50.0);
    tfFullname.setLayoutY(300.0);
    tfFullname.setMinSize(240.0, 30.0);
    tfFullname.setStyle("-fx-border-color: #000E44;");
    tfFullname.setPromptText("Nome Completo");
    tfFullname.setFont(fontTextField);

    FileInputStream inputFullName = new FileInputStream(
      "@../../../PetsAnatomy/src/assets/user.png"
    );
    Image imageFullName = new Image(inputFullName);
    ImageView imageViewFullName = new ImageView(imageFullName);
    imageViewFullName.setLayoutX(10.0);
    imageViewFullName.setLayoutY(300.0);
    imageViewFullName.setFitHeight(43.0);
    imageViewFullName.setFitWidth(32.0);
    imageViewFullName.setPreserveRatio(true);

    tfUsername.setLayoutX(50.0);
    tfUsername.setLayoutY(363.0);
    tfUsername.setMinSize(240.0, 30.0);
    tfUsername.setStyle("-fx-border-color: #000E44;");
    tfUsername.setPromptText("Username");
    tfUsername.setFont(fontTextField);

    FileInputStream inputUsername = new FileInputStream(
      "@../../../PetsAnatomy/src/assets/user.png"
    );
    Image imageUsername = new Image(inputUsername);
    ImageView imageViewUsername = new ImageView(imageUsername);
    imageViewUsername.setLayoutX(10.0);
    imageViewUsername.setLayoutY(363.0);
    imageViewUsername.setFitHeight(43.0);
    imageViewUsername.setFitWidth(32.0);
    imageViewUsername.setPreserveRatio(true);

    tfEmail.setLayoutX(50.0);
    tfEmail.setLayoutY(426.0);
    tfEmail.setMinSize(240.0, 30.0);
    tfEmail.setStyle("-fx-border-color: #000E44;");
    tfEmail.setPromptText("Email");
    tfEmail.setFont(fontTextField);

    FileInputStream inputEmail = new FileInputStream(
      "@../../../PetsAnatomy/src/assets/email.png"
    );
    Image imageEmail = new Image(inputEmail);
    ImageView imageViewEmail = new ImageView(imageEmail);
    imageViewEmail.setLayoutX(10.0);
    imageViewEmail.setLayoutY(426.0);
    imageViewEmail.setFitHeight(43.0);
    imageViewEmail.setFitWidth(32.0);
    imageViewEmail.setPreserveRatio(true);

    pfPassword.setLayoutX(50.0);
    pfPassword.setLayoutY(493.0);
    pfPassword.setMinSize(240.0, 30.0);
    pfPassword.setPromptText("Senha");
    pfPassword.setStyle("-fx-border-color: #000E44;");

    FileInputStream inputPassword = new FileInputStream(
      "@../../../PetsAnatomy/src/assets/password.png"
    );
    Image imagePassword = new Image(inputPassword);
    ImageView imageViewPassword = new ImageView(imagePassword);
    imageViewPassword.setLayoutX(10.0);
    imageViewPassword.setLayoutY(493.0);
    imageViewPassword.setFitHeight(43.0);
    imageViewPassword.setFitWidth(32.0);
    imageViewPassword.setPreserveRatio(true);

    CheckBox cbPassword = new CheckBox("Mostrar/Esconder Senha");
    cbPassword.setFont(fontSmallText);
    cbPassword.setLayoutX(50.0);
    cbPassword.setLayoutY(537.0);

    tfPassword.setLayoutX(50.0);
    tfPassword.setLayoutY(493.0);
    tfPassword.setMinSize(240.0, 30.0);
    tfPassword.setFont(fontTextField);
    tfPassword.managedProperty().bind(cbPassword.selectedProperty());
    tfPassword.visibleProperty().bind(cbPassword.selectedProperty());

    pfPassword.managedProperty().bind(cbPassword.selectedProperty().not());
    pfPassword.visibleProperty().bind(cbPassword.selectedProperty().not());

    tfPassword.textProperty().bindBidirectional(pfPassword.textProperty());

    btnSignUp.setOnAction(
      e -> {
        control.signUp();
        tfPassword.setText("");
      }
    );
    btnSignUp.setLayoutX(10.0);
    btnSignUp.setLayoutY(606.0);
    btnSignUp.setMinSize(280.0, 40.0);
    btnSignUp.setFont(fontBtn);
    btnSignUp.setStyle("-fx-background-color: #000E44; -fx-text-fill: white; -fx-cursor: hand;");

    btnLogin.setOnAction(
      e -> {
        control.login();
      }
    );
    btnLogin.setLayoutX(67.0);
    btnLogin.setLayoutY(661.0);
    btnLogin.setMinSize(173.0, 30.0);
    btnLogin.setUnderline(true);
    btnLogin.setFont(fontSmallText);
    btnLogin.setStyle("-fx-background-color: none; -fx-border-color: none; -fx-cursor: hand;");

    formPane
      .getChildren()
      .addAll(
        imageViewlogo,
        tfFullname,
        imageViewFullName,
        tfUsername,
        imageViewUsername,
        tfEmail,
        imageViewEmail,
        tfPassword,
        pfPassword,
        cbPassword,
        imageViewPassword,
        btnLogin,
        btnSignUp
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
      tfFullname.textProperty(),
      control.fullnameProperty()
    );
    Bindings.bindBidirectional(tfEmail.textProperty(), control.emailProperty());
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
