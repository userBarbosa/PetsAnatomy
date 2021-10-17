package boundary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SignUpBoundary extends Application {
    private TextField tfUsername = new TextField();
    private TextField tfUseremail = new TextField();
    private TextField tfPassword = new TextField();
    private TextField tfConfirmPassword = new TextField();
    private Button btnSignUp = new Button("Cadastrar");
    private Button btnLogin = new Button("Já é cadastrado? Login");
    private Label lblTitle = new Label("PetsAnatomy");
    
	@Override
	public void start(Stage primaryStage) throws FileNotFoundException {
		HBox mainPane = new HBox(); 
		AnchorPane formPane = new AnchorPane();
		AnchorPane designPane = new AnchorPane(); 
		
		Font fontTextField = Font.loadFont("file:resources/fonts/Poppins-Regular.ttf", 14);
		Font fontTitle = Font.loadFont("file:resources/fonts/Poppins-Bold.ttf", 96);
		Font fontBtnLogin = Font.loadFont("file:resources/fonts/Poppins-Bold.ttf", 14);
		Font fontLinkSignUp = Font.loadFont("file:resources/fonts/Poppins-Regular.ttf", 12);
		
		formPane.prefHeight(768.0);
		formPane.prefWidth(498.0);
		formPane.setStyle("-fx-background-color: #ffffff;");
		
	    FileInputStream inputLogo = new FileInputStream("@../../../PetsAnatomy/src/assets/logo.png");
	    Image imageLogo = new Image(inputLogo);
	    ImageView imageViewlogo = new ImageView(imageLogo);
	    imageViewlogo.setLayoutX(110.0);
	    imageViewlogo.setLayoutY(52.0);
	    imageViewlogo.setFitHeight(221.0);
	    imageViewlogo.setFitWidth(241.0);
	    imageViewlogo.setPreserveRatio(true);
	    
	    tfUsername.setLayoutX(100.0);
	    tfUsername.setLayoutY(300.0);
	    tfUsername.setMinSize(301.0, 43.0);
	    tfUsername.setStyle("-fx-border-color: #000E44;");
	    tfUsername.setFont(fontTextField);
	    
	    FileInputStream inputUser = new FileInputStream("@../../../PetsAnatomy/src/assets/user.png");
	    Image imageUser = new Image(inputUser);
	    ImageView imageViewUser = new ImageView(imageUser);
	    imageViewUser.setLayoutX(61.0);
	    imageViewUser.setLayoutY(311.0);
	    imageViewUser.setFitHeight(43.0);
	    imageViewUser.setFitWidth(32.0);
	    imageViewUser.setPreserveRatio(true);
	    
	    tfUseremail.setLayoutX(100.0);
	    tfUseremail.setLayoutY(363.0);
	    tfUseremail.setMinSize(301.0, 43.0);
	    tfUseremail.setStyle("-fx-border-color: #000E44;");
	    tfUseremail.setFont(fontTextField);
	    
	    FileInputStream inputEmail = new FileInputStream("@../../../PetsAnatomy/src/assets/email.png");
	    Image imageEmail = new Image(inputEmail);
	    ImageView imageViewEmail = new ImageView(imageEmail);
	    imageViewEmail.setLayoutX(61.0);
	    imageViewEmail.setLayoutY(369.0);
	    imageViewEmail.setFitHeight(43.0);
	    imageViewEmail.setFitWidth(32.0);
	    imageViewEmail.setPreserveRatio(true);
	    
	    tfPassword.setLayoutX(100.0);
	    tfPassword.setLayoutY(426.0);
	    tfPassword.setMinSize(301.0, 43.0);
	    tfPassword.setStyle("-fx-border-color: #000E44;");
	    tfPassword.setFont(fontTextField);
	    
	    FileInputStream inputPassword = new FileInputStream("@../../../PetsAnatomy/src/assets/password.png");
	    Image imagePassword = new Image(inputPassword);
	    ImageView imageViewPassword = new ImageView(imagePassword);
	    imageViewPassword.setLayoutX(61.0);
	    imageViewPassword.setLayoutY(432.0);
	    imageViewPassword.setFitHeight(43.0);
	    imageViewPassword.setFitWidth(32.0);
	    imageViewPassword.setPreserveRatio(true);
	    
	    tfConfirmPassword.setLayoutX(100.0);
	    tfConfirmPassword.setLayoutY(493.0);
	    tfConfirmPassword.setMinSize(301.0, 43.0);
	    tfConfirmPassword.setStyle("-fx-border-color: #000E44;");
	    tfConfirmPassword.setFont(fontTextField);
	    
	    FileInputStream inputConfirmPassword = new FileInputStream("@../../../PetsAnatomy/src/assets/password-lock.png");
	    Image imageConfirmPassword = new Image(inputConfirmPassword);
	    ImageView imageViewConfirmPassword = new ImageView(imageConfirmPassword);
	    imageViewConfirmPassword.setLayoutX(61.0);
	    imageViewConfirmPassword.setLayoutY(499.0);
	    imageViewConfirmPassword.setFitHeight(43.0);
	    imageViewConfirmPassword.setFitWidth(32.0);
	    imageViewConfirmPassword.setPreserveRatio(true);
	    
	    btnSignUp.setLayoutX(55.0);
	    btnSignUp.setLayoutY(606.0);
	    btnSignUp.setMinSize(330.0, 43.0);
	    btnSignUp.setFont(fontBtnLogin);
	    btnSignUp.setStyle("-fx-background-color: #000E44; -fx-text-fill: white;");

	    btnLogin.setLayoutX(137.0);
	    btnLogin.setLayoutY(661.0);
	    btnLogin.setMinSize(173.0, 30.0);
	    btnLogin.setUnderline(true);
	    btnLogin.setFont(fontLinkSignUp);
	    btnLogin.setStyle("-fx-background-color: none; -fx-border-color: none;");
	    
        formPane.getChildren().addAll(imageViewlogo, tfUsername, imageViewUser, tfUseremail, imageViewEmail, tfPassword, imageViewPassword, tfConfirmPassword, imageViewConfirmPassword, btnLogin, btnSignUp);
		
        designPane.setMinSize(883.0, 768.0);
		designPane.setStyle("-fx-background-color: #000E44;");	
		
	    FileInputStream inputEstetoscopio = new FileInputStream("@../../../PetsAnatomy/src/assets/estetoscopio.png");
	    Image imageEstetoscopio = new Image(inputEstetoscopio);
	    ImageView imageViewEstetoscopio = new ImageView(imageEstetoscopio);
	    imageViewEstetoscopio.setLayoutX(287.0);
	    imageViewEstetoscopio.setFitHeight(284.0);
	    imageViewEstetoscopio.setFitWidth(333.0);
	    imageViewEstetoscopio.setPreserveRatio(true);
	    
	    FileInputStream inputPata = new FileInputStream("@../../../PetsAnatomy/src/assets/pata.png");
	    Image imagePata = new Image(inputPata);
	    ImageView imageViewPata = new ImageView(imagePata);
	    imageViewPata.setLayoutX(344.0);
	    imageViewPata.setLayoutY(547.0);
	    imageViewPata.setFitHeight(221.0);
	    imageViewPata.setFitWidth(247.0);
	    imageViewPata.setPreserveRatio(true);
	    
	    lblTitle.setLayoutX(126.0);
	    lblTitle.setLayoutY(337.0);
	    lblTitle.setMinSize(624.0, 119.0);
	    lblTitle.setFont(fontTitle);
	    lblTitle.setStyle("-fx-text-fill: white;");
		
	    designPane.getChildren().addAll(imageViewEstetoscopio, imageViewPata, lblTitle);
		
		HBox.setHgrow(formPane, Priority.ALWAYS);
     	HBox.setHgrow(designPane, Priority.ALWAYS);
     	mainPane.getChildren().addAll(designPane, formPane);
		
		Scene scene = new Scene(mainPane, 1366, 768);
		primaryStage.setResizable(false);
	    primaryStage.setScene(scene);
	    primaryStage.show();
	    primaryStage.setTitle("Login");
	}

	public static void main(String[] args) {
		launch(args);
	}
}
