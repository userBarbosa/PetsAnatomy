package boundary;

import control.LoginControl;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class LoginBoundary implements StrategyBoundary {

  private TextField tfUsername = new TextField();
  private PasswordField pfPassword = new PasswordField();
  private TextField tfPassword = new TextField();

  private Button btnLogin = new Button("Login");
  private Button btnSignUp = new Button("Cadastrar-se");
  private Button btnForgotPassword = new Button("Esqueci minha senha");
  Font fontTf = Font.loadFont("file:resources/fonts/Poppins-Regular.ttf", 14);
  Font fontTitle = Font.loadFont("file:resources/fonts/Poppins-Bold.ttf", 96);
  Font fontBtn = Font.loadFont("file:resources/fonts/Poppins-Bold.ttf", 14);
  Font fontTxt = Font.loadFont("file:resources/fonts/Poppins-Regular.ttf", 12);

  TextField tfResetUsername = new TextField();
  PasswordField pfResetPassword = new PasswordField();
  TextField tfResetPassword = new TextField();
  CheckBox cbResetPassword = new CheckBox("Mostrar/Esconder Senha");
  Button btnDismiss = new Button("Confirmar");

  private static LoginControl control = new LoginControl();

  static MainBoundary main = new MainBoundary();
  static StrategyBoundary signUp = new SignUpBoundary();
  static StrategyBoundary image = new ImageBoundary();
  static SideMenuBoundary sideMenu = new SideMenuBoundary();
  static StrategyBoundary dash = new DashboardBoundary();

  @Override
  public Pane generateBoundaryStrategy() {
    AnchorPane formPane = new AnchorPane();

    binding();

    formPane.setPrefHeight(768.0);
    formPane.setPrefWidth(300.0);
    formPane.setStyle("-fx-background-color: #ffffff;");

    FileInputStream inputLogo = null;
    FileInputStream inputUser = null;
    FileInputStream inputPassword = null;

    try {
      inputLogo =
        new FileInputStream("@../../../PetsAnatomy/src/assets/logo.png");
      inputUser =
        new FileInputStream("@../../../PetsAnatomy/src/assets/user.png");
      inputPassword =
        new FileInputStream("@../../../PetsAnatomy/src/assets/lock.png");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

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
        identification(control.login());
      }
    );
    btnLogin.setLayoutX(10.0);
    btnLogin.setLayoutY(550.0);
    btnLogin.setMinSize(280.0, 40.0);
    btnLogin.setFont(fontBtn);
    btnLogin.setStyle(
      "-fx-background-color: #000E44; -fx-text-fill: white; -fx-cursor: hand;"
    );

    btnSignUp.setOnAction(
      e -> {
        main.setPaneLeftRightAnchor(
          signUp.generateBoundaryStrategy(),
          image.generateBoundaryStrategy()
        );
      }
    );
    btnSignUp.setLayoutX(97.0);
    btnSignUp.setLayoutY(590.0);
    btnSignUp.setMinSize(100.0, 30.0);
    btnSignUp.setUnderline(true);
    btnSignUp.setFont(fontTxt);
    btnSignUp.setStyle(
      "-fx-background-color: none; -fx-border-color: none; -fx-cursor: hand;"
    );

    btnForgotPassword.setOnAction(
      e -> {
        this.popupForgotPassword();
      }
    );
    btnForgotPassword.setLayoutX(50.0);
    btnForgotPassword.setLayoutY(620.0);
    btnForgotPassword.setMinSize(200.0, 30.0);
    btnForgotPassword.setFont(fontTxt);
    btnForgotPassword.setUnderline(true);
    btnForgotPassword.setStyle(
      "-fx-background-color: none; -fx-border-color: none; -fx-cursor: hand;"
    );

    formPane
      .getChildren()
      .addAll(
        imageViewlogo,
        tfUsername,
        imageViewUser,
        pfPassword,
        tfPassword,
        cbPassword,
        imageViewPassword,
        btnLogin,
        btnSignUp,
        btnForgotPassword
      );

    return formPane;
  }

  private void identification(String role) {
    tfUsername.setText("");
    tfPassword.setText("");
    pfPassword.setText("");

    switch (role) {
      case "admin":
        JOptionPane.showMessageDialog(
          null,
          "Seja bem vindo Administrador!",
          "Login",
          JOptionPane.INFORMATION_MESSAGE
        );
        main.setPaneLeftRightAnchor(
          sideMenu.generateSideMenuStrategy(role),
          dash.generateBoundaryStrategy()
        );
        break;
      case "receptionist":
        JOptionPane.showMessageDialog(
          null,
          "Seja bem vindo colaborador(a)!",
          "Login",
          JOptionPane.INFORMATION_MESSAGE
        );
        main.setPaneLeftRightAnchor(
          sideMenu.generateSideMenuStrategy(role),
          dash.generateBoundaryStrategy()
        );
        break;
      case "doctor":
        JOptionPane.showMessageDialog(
          null,
          "Seja bem vindo Doutor(a)!",
          "Login",
          JOptionPane.INFORMATION_MESSAGE
        );
        main.setPaneLeftRightAnchor(
          sideMenu.generateSideMenuStrategy(role),
          dash.generateBoundaryStrategy()
        );
        break;
      case "":
        JOptionPane.showMessageDialog(
          null,
          "Entre em contato com o Administrador!",
          "Login",
          JOptionPane.INFORMATION_MESSAGE
        );
        main.setPaneLeftRightAnchor(
          sideMenu.generateSideMenuStrategy(role),
          dash.generateBoundaryStrategy()
        );
        break;
      case "400 - Bad Request":
      case "401 - Unauthorized":
        JOptionPane.showMessageDialog(
          null,
          "Your data was not found, contact your system administrator",
          role,
          JOptionPane.INFORMATION_MESSAGE
        );
        break;
      default:
        JOptionPane.showMessageDialog(
          null,
          "Erro ao entrar",
          "Erro no Login",
          JOptionPane.INFORMATION_MESSAGE
        );
        break;
    }
  }

  public void popupForgotPassword() {
    Stage popup = new Stage();
    popup.initModality(Modality.WINDOW_MODAL);

    AnchorPane pane = new AnchorPane();
    Scene scene = new Scene(pane, 420, 160);
    popup.setScene(scene);

    tfResetUsername.setLayoutX(25.0);
    tfResetUsername.setLayoutY(10.0);
    tfResetUsername.setPrefHeight(25.0);
    tfResetUsername.setPrefWidth(370.0);
    tfResetUsername.setFont(fontTf);
    tfResetUsername.setPromptText("Digite o usuário");

    tfResetPassword.setLayoutX(25.0);
    tfResetPassword.setLayoutY(50.0);
    tfResetPassword.setPrefHeight(25.0);
    tfResetPassword.setPrefWidth(370.0);
    tfResetPassword.setFont(fontTf);
    tfResetPassword.managedProperty().bind(cbResetPassword.selectedProperty());
    tfResetPassword.visibleProperty().bind(cbResetPassword.selectedProperty());
    tfResetPassword
      .textProperty()
      .bindBidirectional(pfResetPassword.textProperty());

    pfResetPassword.setLayoutX(25.0);
    pfResetPassword.setLayoutY(50.0);
    pfResetPassword.setPrefHeight(25.0);
    pfResetPassword.setPrefWidth(370.0);
    pfResetPassword.setStyle("-fx-border-color: #000E44;");
    pfResetPassword
      .managedProperty()
      .bind(cbResetPassword.selectedProperty().not());
    pfResetPassword
      .visibleProperty()
      .bind(cbResetPassword.selectedProperty().not());
    pfResetPassword.setPromptText("Digite a senha");

    cbResetPassword.setFont(fontTxt);
    cbResetPassword.setLayoutX(25.0);
    cbResetPassword.setLayoutY(90.0);

    btnDismiss.setOnAction(
      e -> {
        identification(
          control.forgotPassword(
            tfResetUsername.getText(),
            pfResetPassword.getText()
          )
        );
        popup.close();
        tfResetUsername.setText("");
        pfResetPassword.setText("");
      }
    );
    btnDismiss.setLayoutX(25.0);
    btnDismiss.setLayoutY(120.0);
    btnDismiss.setFont(fontTf);

    pane
      .getChildren()
      .addAll(
        tfResetUsername,
        pfResetPassword,
        cbResetPassword,
        tfResetPassword,
        btnDismiss
      );

    popup.setTitle("Esqueci minha senha");
    popup.setResizable(false);
    popup.showAndWait();
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
}
