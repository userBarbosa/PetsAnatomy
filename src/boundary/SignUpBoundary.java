package boundary;

import control.SignUpControl;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class SignUpBoundary implements StrategyBoundary {

  private TextField tfFullname = new TextField();
  private TextField tfUsername = new TextField();
  private TextField tfEmail = new TextField();
  private PasswordField pfPassword = new PasswordField();
  private TextField tfPassword = new TextField();

  private Button btnSignUp = new Button("Cadastrar");
  private Button btnLogin = new Button("Já é cadastrado? Login");
  private static SignUpControl control = new SignUpControl();
  static MainBoundary main = new MainBoundary();
  static StrategyBoundary login = new LoginBoundary();
  static StrategyBoundary image = new ImageBoundary();

  @Override
  public Pane generateBoundaryStrategy() {
    AnchorPane formPane = new AnchorPane();

    binding();

    Font fontTextField = Font.loadFont(
      "file:resources/fonts/Poppins-Regular.ttf",
      14
    );
    Font fontBtn = Font.loadFont("file:resources/fonts/Poppins-Bold.ttf", 14);
    Font fontSmallText = Font.loadFont(
      "file:resources/fonts/Poppins-Regular.ttf",
      12
    );

    formPane.setPrefHeight(768.0);
    formPane.setPrefWidth(300.0);
    formPane.setStyle("-fx-background-color: #ffffff;");

    FileInputStream inputLogo = null;
    FileInputStream inputFullName = null;
    FileInputStream inputEmail = null;
    FileInputStream inputPassword = null;
    FileInputStream inputUsername = null;
    try {
      inputEmail =
        new FileInputStream("@../../../PetsAnatomy/src/assets/email.png");
      inputPassword =
        new FileInputStream("@../../../PetsAnatomy/src/assets/password.png");
      inputLogo =
        new FileInputStream("@../../../PetsAnatomy/src/assets/logo.png");
      inputFullName =
        new FileInputStream("@../../../PetsAnatomy/src/assets/user.png");
      inputUsername =
        new FileInputStream("@../../../PetsAnatomy/src/assets/user.png");
    } catch (FileNotFoundException e1) {
      e1.printStackTrace();
    }

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
        main.setPaneLeftRightAnchor(
          login.generateBoundaryStrategy(),
          image.generateBoundaryStrategy()
        );
      }
    );
    btnSignUp.setLayoutX(10.0);
    btnSignUp.setLayoutY(606.0);
    btnSignUp.setMinSize(280.0, 40.0);
    btnSignUp.setFont(fontBtn);
    btnSignUp.setStyle(
      "-fx-background-color: #000E44; -fx-text-fill: white; -fx-cursor: hand;"
    );

    btnLogin.setOnAction(
      e -> {
        main.setPaneLeftRightAnchor(
          login.generateBoundaryStrategy(),
          image.generateBoundaryStrategy()
        );
      }
    );
    btnLogin.setLayoutX(67.0);
    btnLogin.setLayoutY(661.0);
    btnLogin.setMinSize(173.0, 30.0);
    btnLogin.setUnderline(true);
    btnLogin.setFont(fontSmallText);
    btnLogin.setStyle(
      "-fx-background-color: none; -fx-border-color: none; -fx-cursor: hand;"
    );

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

    return formPane;
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
}
