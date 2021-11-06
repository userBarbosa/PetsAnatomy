package boundary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import control.DashboardControl;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DashboardBoundary extends Application {
	
	private static DashboardControl control = new DashboardControl();
	
	private Button btnHome = new Button("PetsAnatomy");	
	private Button btnAgenda = new Button("Agenda");
	private Button btnPacientes = new Button("Pacientes");
	private Button btnClientes = new Button("Clientes");
	private Button btnFuncionarios = new Button("Funcionários");
	private Button btnConfiguracoes = new Button("Configurações");
	private Button btnSair = new Button("Sair");
	
	private Label lblDailyPhrase = new Label(control.getDailyPhrase());
	
	@Override
	public void start(Stage stage) throws Exception {
		AnchorPane mainPane = new AnchorPane(); 
		AnchorPane menuPane = new AnchorPane();
		AnchorPane imagePane = new AnchorPane(); 
		
		Font fontBtnHome = Font.loadFont("file:resources/fonts/Poppins-Bold.ttf", 15);
		Font fontBtns = Font.loadFont("file:resources/fonts/Poppins-Regular.ttf", 14);
		Font fontLblPhrase = Font.loadFont("file:resources/fonts/Poppins-Bold.ttf", 70);
		
		menuPane.setPrefHeight(768.0);
		menuPane.setPrefWidth(300.0);
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
	    
	    btnFuncionarios.setLayoutY(320.0);
	    btnFuncionarios.setMinSize(300.0, 50.0);
	    btnFuncionarios.setFont(fontBtns);
	    btnFuncionarios.setStyle("-fx-background-color: #000C3A; -fx-text-fill: white;");
	    
	    btnConfiguracoes.setLayoutY(390.0);
	    btnConfiguracoes.setMinSize(300.0, 50.0);
	    btnConfiguracoes.setFont(fontBtns);
	    btnConfiguracoes.setStyle("-fx-background-color: #000C3A; -fx-text-fill: white;");
	    
	    btnSair.setLayoutY(718.0);
	    btnSair.setMinSize(300.0, 50.0);
	    btnSair.setFont(fontBtns);
	    btnSair.setStyle("-fx-background-color: #000C3A; -fx-text-fill: white;");
		
	    menuPane.getChildren().addAll(btnHome, btnAgenda, btnPacientes, btnClientes, btnFuncionarios, btnConfiguracoes, btnSair);

	    imagePane.setPrefHeight(768.0);
	    imagePane.setPrefWidth(1066.0);
	    imagePane.setLayoutX(300);
	    
		FileInputStream inputDash = new FileInputStream("@../../../PetsAnatomy/src/assets/pet-dashboard.jpg");
		Image imageDash = new Image(inputDash);
		ImageView imageViewDash = new ImageView(imageDash);
		imageViewDash.setFitHeight(768.0);
		imageViewDash.setFitWidth(1066.0);
		imageViewDash.setPreserveRatio(true);
		imageViewDash.setOpacity(0.80);
		
		lblDailyPhrase.setLayoutX(175);
		lblDailyPhrase.setLayoutY(275);
		lblDailyPhrase.setFont(fontLblPhrase);
		lblDailyPhrase.setMaxWidth(700);
		lblDailyPhrase.setWrapText(true);
		lblDailyPhrase.setStyle("-fx-text-alignment: center; -fx-text-fill: white;");
		
		imagePane.getChildren().add(imageViewDash);
		imagePane.getChildren().add(lblDailyPhrase);
		
		mainPane.setLeftAnchor(menuPane, 0.0);
		mainPane.setRightAnchor(imagePane, 0.0);
		mainPane.getChildren().addAll(menuPane, imagePane);
		mainPane.setPrefHeight(768.0);
		mainPane.setPrefWidth(1366.0);

		Scene scene = new Scene(mainPane, 1366, 768);
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
		stage.setTitle("Clinica Veterinaria PetsAnatomy");	
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}