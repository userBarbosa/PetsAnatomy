package boundary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class DashboardBoundary extends Application {
	
	private Button btnHome = new Button("PetsAnatomy");	
	private Button btnAgenda = new Button("Agenda");
	private Button btnPacientes = new Button("Pacientes");
	private Button btnClientes = new Button("Clientes");
	private Button btnAdocoes = new Button("Adoções");
	private Button btnFuncionarios = new Button("Funcionários");
	private Button btnConfiguracoes = new Button("Configurações");
	private Button btnSair = new Button("Sair");

	@Override
	public void start(Stage stage) throws Exception {
		HBox mainPane = new HBox(); 
		AnchorPane menuPane = new AnchorPane();
		AnchorPane imagePane = new AnchorPane(); 
		
		Font fontBtnHome = Font.loadFont("file:resources/fonts/Poppins-Bold.ttf", 15);
		Font fontBtns = Font.loadFont("file:resources/fonts/Poppins-Regular.ttf", 14);
		
		menuPane.prefHeight(768.0);
		menuPane.prefWidth(300.0);
		menuPane.setStyle("-fx-background-color: #000E44;");
		
		btnHome.setMinSize(300.0, 55.0);
		btnHome.setFont(fontBtnHome);
	    btnHome.setStyle("-fx-background-color: #000C3A; -fx-text-fill: white;");
	    
	    btnAgenda.setLayoutY(110.0);
	    btnAgenda.setMinSize(300.0, 50.0);
	    btnAgenda.setFont(fontBtns);
	    btnAgenda.setStyle("-fx-background-color: #000C3A; -fx-text-fill: white;");
	    
	    btnPacientes.setLayoutY(180.0);
	    btnPacientes.setMinSize(300.0, 50.0);
	    btnPacientes.setFont(fontBtns);
	    btnPacientes.setStyle("-fx-background-color: #000C3A; -fx-text-fill: white;");
	    
	    btnClientes.setLayoutY(250.0);
	    btnClientes.setMinSize(300.0, 50.0);
	    btnClientes.setFont(fontBtns);
	    btnClientes.setStyle("-fx-background-color: #000C3A; -fx-text-fill: white;");
	    
	    btnAdocoes.setLayoutY(320.0);
	    btnAdocoes.setMinSize(300.0, 50.0);
	    btnAdocoes.setFont(fontBtns);
	    btnAdocoes.setStyle("-fx-background-color: #000C3A; -fx-text-fill: white;");
	    
	    btnFuncionarios.setLayoutY(390.0);
	    btnFuncionarios.setMinSize(300.0, 50.0);
	    btnFuncionarios.setFont(fontBtns);
	    btnFuncionarios.setStyle("-fx-background-color: #000C3A; -fx-text-fill: white;");
	    
	    btnConfiguracoes.setLayoutY(460.0);
	    btnConfiguracoes.setMinSize(300.0, 50.0);
	    btnConfiguracoes.setFont(fontBtns);
	    btnConfiguracoes.setStyle("-fx-background-color: #000C3A; -fx-text-fill: white;");
	    
	    btnSair.setLayoutY(718.0);
	    btnSair.setMinSize(300.0, 50.0);
	    btnSair.setFont(fontBtns);
	    btnSair.setStyle("-fx-background-color: #000C3A; -fx-text-fill: white;");
		
	    menuPane.getChildren().addAll(btnHome, btnAgenda, btnPacientes, btnClientes, btnAdocoes, btnFuncionarios, btnConfiguracoes, btnSair);
	    	
	    imagePane.prefHeight(768.0);
	    imagePane.prefWidth(1066.0);
	    
		FileInputStream inputDash = new FileInputStream("@../../../PetsAnatomy/src/assets/pet-dashboard.jpg");
		Image imageDash = new Image(inputDash);
		ImageView imageViewDash = new ImageView(imageDash);
		imageViewDash.setFitHeight(768.0);
		imageViewDash.setFitWidth(1066.0);
		imageViewDash.setPreserveRatio(true);
		
		imagePane.getChildren().add(imageViewDash);
		
		HBox.setHgrow(menuPane, Priority.ALWAYS);
		HBox.setHgrow(imagePane, Priority.ALWAYS);
		mainPane.getChildren().addAll(menuPane, imagePane);
		Scene scene = new Scene(mainPane, 1366, 768);
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
		stage.setTitle("Clínica Veterinária PetsAnatomy");	
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}